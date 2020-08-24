package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class FileModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "userId")
    private Integer userId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "path")
    private String path;

    @JsonProperty(value = "size")
    private Long size;

    @JsonProperty(value = "hash")
    private String hash;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
