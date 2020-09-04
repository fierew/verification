package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RoleResourceModel {

    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "resourceId")
    private Integer resourceId;

    @JsonProperty(value = "createTime")
    private Integer createTime;
}
