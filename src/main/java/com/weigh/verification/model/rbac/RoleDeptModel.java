package com.weigh.verification.model.rbac;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RoleDeptModel {
    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "deptId")
    private Integer deptId;

    @JsonProperty(value = "createTime")
    private Integer createTime;
}
