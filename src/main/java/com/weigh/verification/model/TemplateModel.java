package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class TemplateModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "userId")
    private Integer userId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "describe")
    private String describe;

    @JsonProperty(value = "fileId")
    private Integer fileId;

    @JsonProperty(value = "keys")
    private String keys;

    @JsonProperty(value = "params")
    private String params;

    @JsonProperty(value = "state")
    private Byte state;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
