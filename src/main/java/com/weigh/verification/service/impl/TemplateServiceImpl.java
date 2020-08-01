package com.weigh.verification.service.impl;

import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.service.TemplateService;
import com.weigh.verification.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuyang
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private WordUtil wordUtil;

    @Override
    public List<String> analysis(String filePath) {
        String text = wordUtil.getText(filePath);

        String regex = "\\{\\{(.*?)}}";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(text);

        List<String> list = new ArrayList<>();

        while (m.find()) {
            String key = m.group(1);
            System.out.println(key);

            if(!list.contains(key)){
                list.add(m.group(1));
            }
        }
        return list;
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
