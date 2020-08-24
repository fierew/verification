package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class UserModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "nickname")
    private String nickname;

    @JsonProperty(value = "state")
    private Byte state;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
