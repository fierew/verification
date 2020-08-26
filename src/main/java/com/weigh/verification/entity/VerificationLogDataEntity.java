package com.weigh.verification.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weigh.verification.model.VerificationLogModel;
import lombok.Data;

import java.util.List;

/**
 * @author xuyang
 */
@Data
public class VerificationLogDataEntity {
    @JsonProperty(value = "data")
    private List<VerificationLogModel> data;
}
