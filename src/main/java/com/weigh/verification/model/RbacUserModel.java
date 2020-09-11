package com.weigh.verification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class RbacUserModel {
    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "deptId")
    private Integer deptId;

    @JsonProperty(value = "roleId")
    private Integer roleId;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "realName")
    private String realName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "mobile")
    private String mobile;

    @JsonProperty(value = "sex")
    private Byte sex;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "loginNum")
    private Integer loginNum;

    @JsonProperty(value = "state")
    private Byte state;

    @JsonProperty(value = "isDelete")
    private Byte isDelete;

    @JsonProperty(value = "createTime")
    private Integer createTime;

    @JsonProperty(value = "updateTime")
    private Integer updateTime;
}
