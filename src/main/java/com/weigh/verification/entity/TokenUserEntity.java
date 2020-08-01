package com.weigh.verification.entity;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class TokenUserEntity {
    private Integer userId;
    private String username;
    private String role;
}
