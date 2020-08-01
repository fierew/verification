package com.weigh.verification.service.impl;

import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.service.TemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyang
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    @Override
    public List<String> analysis(String filePath) {
        return null;
    }

    @Override
    public Integer add(TemplateModel templateModel) {
        return null;
    }

    @Override
    public Integer edit(Integer id, TemplateModel templateModel) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public List<TemplateModel> getList(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public List<TemplateModel> getAll() {
        return null;
    }
}
