package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class VerificationLogModel {
    @JsonProperty(value = "userId")
    private Integer userId;

    @JsonProperty(value = "verificationId")
    private Integer verificationId;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "formName")
    private String formName;

    @JsonProperty(value = "updateValue")
    private String updateValue;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
