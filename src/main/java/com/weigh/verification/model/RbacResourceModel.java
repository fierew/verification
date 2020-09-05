package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xuyang
 */
@Data
public class RbacResourceModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "parentId")
    private Integer parentId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "icon")
    private String icon;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "path")
    private String path;

    @JsonProperty(value = "remarks")
    private Integer remarks;

    @JsonProperty(value = "state")
    private Byte state;

    @JsonProperty(value = "sort")
    private Integer sort;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;

    private List<RbacResourceModel> children;
}
