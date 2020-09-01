package com.weigh.verification.model.rbac;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RoleModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private Integer name;

    @JsonProperty(value = "remarks")
    private Integer remarks;

    @JsonProperty(value = "dataRange")
    private Integer dataRange;

    @JsonProperty(value = "sort")
    private Integer sort;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
