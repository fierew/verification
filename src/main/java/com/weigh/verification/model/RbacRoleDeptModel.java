package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RbacRoleDeptModel {
    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "deptId")
    private Integer deptId;

    @JsonProperty(value = "createTime")
    private Integer createTime;
}
