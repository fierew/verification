package com.weigh.verification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class VerificationLogEntity {
    @JsonProperty(value = "id")
    private Integer id;
    
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

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "nickname")
    private String nickname;
}
