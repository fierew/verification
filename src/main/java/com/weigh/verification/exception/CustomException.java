package com.weigh.verification.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xuyang
 */
@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String code;
    private String msg;
}
