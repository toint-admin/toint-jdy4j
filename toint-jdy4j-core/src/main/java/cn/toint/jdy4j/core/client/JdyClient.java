/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.toint.jdy4j.core.client;

import cn.toint.jdy4j.core.model.JdyConfigStorage;
import cn.toint.jdy4j.core.service.JdyAppService;
import cn.toint.jdy4j.core.service.JdyDataService;
import cn.toint.jdy4j.core.service.JdyFileService;
import org.dromara.hutool.extra.spring.SpringUtil;

import java.util.Optional;
import java.util.function.Function;

/**
 * 简道云客户端
 *
 * @author Toint
 * @date 2024/10/19
 */
public interface JdyClient extends AutoCloseable {

    /**
     * 获取简道云客户端
     *
     * @return 简道云客户端
     * @throws RuntimeException 无法获取到简道云客户端
     */
    static JdyClient get() {
        return Optional.ofNullable(SpringUtil.getBean(JdyClient.class))
                .orElseThrow(() -> new NullPointerException("无法获取到简道云客户端"));
    }

    /**
     * 获取简道云客户端, 并且切换企业上下文环境
     *
     * @param corpName 企业名称
     * @return 简道云客户端
     * @throws RuntimeException 无法获取到简道云客户端
     */
    static JdyClient get(final String corpName) {
        return JdyClient.get().switchoverTo(corpName);
    }

    /**
     * 执行方法, 默认关闭资源
     *
     * @param function 执行方法
     * @return result
     */
    default <R> R exec(final Function<JdyClient, R> function) {
        return this.exec(function, true);
    }

    /**
     * 执行方法
     *
     * @param function  执行方法
     * @param autoClose 是否自动关闭资源
     * @return result
     */
    default <R> R exec(final Function<JdyClient, R> function, final boolean autoClose) {
        JdyClient jdyClient = null;
        try {
            jdyClient = JdyClient.get();
            return function.apply(jdyClient);
        } finally {
            if (autoClose && jdyClient != null) {
                try {
                    jdyClient.close();
                } catch (Exception e) {
                    //noinspection ThrowFromFinallyBlock
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 执行方法后自动关闭资源
     *
     * @param function 执行方法
     * @return result
     */
    default <R> R execThenAutoClose(final Function<JdyClient, R> function) {
        return this.exec(function, true);
    }

    /**
     * 执行方法后不自动关闭资源
     *
     * @param function 执行方法
     * @return result
     */
    default <R> R execThenNotAutoClose(final Function<JdyClient, R> function) {
        return this.exec(function, false);
    }

    /**
     * 注册简道云配置
     *
     * @param jdyConfigStorage 简道云配置
     * @return 简道云配置
     */
    default JdyConfigStorage registerJdyConfigStorage(final JdyConfigStorage jdyConfigStorage) {
        return this.putJdyConfigStorage(jdyConfigStorage);
    }

    /**
     * 覆盖简道云配置
     *
     * @param jdyConfigStorage 简道云配置
     * @return 简道云配置
     */
    JdyConfigStorage putJdyConfigStorage(final JdyConfigStorage jdyConfigStorage);

    /**
     * 是否存在简道云配置
     *
     * @param corpName 企业名称
     * @return 是否已存在
     */
    boolean containsJdyConfigStorage(final String corpName);

    /**
     * 删除配置
     *
     * @param corpName 企业名称
     */
    void deleteJdyConfigStorage(final String corpName);

    /**
     * 切换简道云配置
     *
     * @param corpName 企业名称
     * @return 切换状态
     */
    boolean switchover(final String corpName);

    /**
     * 切换简道云配置
     *
     * @param corpName 企业名称
     * @return 简道云客户端
     * @throws RuntimeException 切换失败
     */
    JdyClient switchoverTo(final String corpName);

    /**
     * 简道云应用
     */
    JdyAppService getJdyAppService();

    /**
     * 简道云数据
     */
    JdyDataService getJdyDataService();

    /**
     * 简道云文件
     */
    JdyFileService getJdyFileService();
}