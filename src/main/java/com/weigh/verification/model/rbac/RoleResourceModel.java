package com.weigh.verification.model.rbac;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RoleResourceModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "resourceId")
    private Integer resourceId;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
