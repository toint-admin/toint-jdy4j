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
package cn.toint.jdy4j.core.model;

import cn.toint.tool.util.JacksonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取一条数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JdySelectOneRequest {
    /**
     * 应用id
     */
    @JsonProperty("app_id")
    @NotBlank
    private String appId;
    /**
     * 表单id
     */
    @JsonProperty("entry_id")
    @NotBlank
    private String entryId;
    /**
     * 数据id
     */
    @JsonProperty("data_id")
    @NotBlank
    private String dataId;

    /**
     * 获取一条数据
     */
    public static JdySelectOneRequest of(final String appId, final String entryId, final String dataId) {
        return new JdySelectOneRequest(appId, entryId, dataId);
    }

    /**
     * 获取一条数据
     */
    public static JdySelectOneRequest of(final Object data) {
        final BaseJdyTable jdyTable = JacksonUtil.convertValue(data, BaseJdyTable.class);
        return new JdySelectOneRequest(jdyTable.getAppId(), jdyTable.getEntryId(), jdyTable.getDataId());
    }
}
