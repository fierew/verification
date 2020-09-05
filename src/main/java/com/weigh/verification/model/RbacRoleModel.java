package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xuyang
 */
@Data
public class RbacRoleModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private Integer name;

    @JsonProperty(value = "remarks")
    private Integer remarks;

    @JsonProperty(value = "dataRange")
    private Integer dataRange;

    @JsonProperty(value = "deptArray")
    private List<Integer> deptArray;

    @JsonProperty(value = "resourceArray")
    private List<Integer> resourceArray;

    @JsonProperty(value = "sort")
    private Integer sort;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
