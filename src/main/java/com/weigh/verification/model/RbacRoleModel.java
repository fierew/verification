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
    private String name;

    @JsonProperty(value = "remarks")
    private String remarks;

    @JsonProperty(value = "dataRange")
    private Byte dataRange;

    @JsonProperty(value = "deptArray")
    private List<Integer> deptArray;

    @JsonProperty(value = "deptIds")
    private String deptIds;

    @JsonProperty(value = "deptRootIds")
    private String deptRootIds;

    @JsonProperty(value = "resourceArray")
    private List<Integer> resourceArray;

    @JsonProperty(value = "resourceIds")
    private String resourceIds;

    @JsonProperty(value = "resourceRootIds")
    private String resourceRootIds;

    @JsonProperty(value = "sort")
    private Integer sort;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
