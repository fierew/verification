package com.weigh.verification.entity;

import lombok.Data;

/**
 * @author xuyang
 */
@Data
public class TemplateParamEntity {
    private String name;
    private String key;
    private String type;
    private Byte isNull;
}
