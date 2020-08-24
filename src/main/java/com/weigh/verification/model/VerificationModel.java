package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class VerificationModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "userId")
    private Integer userId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "describe")
    private String describe;

    @JsonProperty(value = "templateId")
    private Integer templateId;

    @JsonProperty(value = "params")
    private String params;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
