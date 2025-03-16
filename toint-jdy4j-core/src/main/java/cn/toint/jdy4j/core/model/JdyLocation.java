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

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * 定位
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public
class JdyLocation {
    /**
     * province
     */
    @JsonProperty("province")
    private String province;
    /**
     * city
     */
    @JsonProperty("city")
    private String city;
    /**
     * district
     */
    @JsonProperty("district")
    private String district;
    /**
     * detail
     */
    @JsonProperty("detail")
    private String detail;
    /**
     * lnglatXY
     */
    @JsonProperty("lnglatXY")
    private Collection<Number> lnglatXY;
}
