package com.weigh.verification.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.FileDao;
import com.weigh.verification.dao.TemplateDao;
import com.weigh.verification.entity.TemplateParamEntity;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.service.TemplateService;
import com.weigh.verification.utils.WordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuyang
 */
@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private WordUtil wordUtil;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private TemplateDao templateDao;

    @Override
    public List<String> analysis(String filePath) {
        String text = wordUtil.getText(filePath);

        String regex = "\\{\\{(.*?)}}";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(text);

        List<String> list = new ArrayList<>();

        while (m.find()) {
            String key = m.group(1);

            if (!list.contains(key)) {
                list.add(m.group(1));
            }
        }
        return list;
    }

    @Override
    public Integer add(Integer userId, TemplateModel templateModel) {
        List<String> keys = this.verifyTempParam(templateModel);
        if (keys == null) {
            return null;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        templateModel.setCreateTime(time);
        templateModel.setUpdateTime(time);
        templateModel.setUserId(userId);
        templateModel.setKeys(String.join(",", keys));
        return templateDao.add(templateModel);
    }

    @Override
    public Integer edit(Integer id, Integer userId, TemplateModel templateModel) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        templateModel.setUpdateTime(time);
        templateModel.setId(id);

        // 验证数据
        List<String> keys = this.verifyTempParam(templateModel);
        if (keys == null) {
            log.info("验证数据失败！");
            return null;
        }
        templateModel.setKeys(String.join(",", keys));

        return templateDao.edit(templateModel);
    }

    @Override
    public Integer delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        return templateDao.delete(id, time);
    }

    @Override
    public TemplateModel getInfoById(Integer id) {
        return templateDao.getInfo(id);
    }

    @Override
    public PageInfo<TemplateModel> getList(Integer page, Integer pageSize, TemplateModel templateModel) {
        PageHelper.startPage(page, pageSize);
        List<TemplateModel> list = templateDao.getList(templateModel);
        return new PageInfo<>(list);
    }

    @Override
    public List<TemplateModel> getAll() {
        return templateDao.getAll();
    }

    @Override
    public Integer modifyState(Integer id, Byte state) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        return templateDao.modifyState(id, state, time);
    }

    private List<String> verifyTempParam(TemplateModel templateModel) {
        if (templateModel.getFileId() == null) {
            log.info("文件ID不能是空！");
            return null;
        }

        // 根据模板文件id获取文件路径
        FileModel fileInfo = fileDao.getInfo(templateModel.getFileId());
        if (fileInfo == null) {
            log.info("文件信息不存在");
            return null;
        }

        List<String> keys = this.analysis(System.getProperty("user.dir") + "/upload/" + fileInfo.getPath());

        // 解析模板参数
        if (templateModel.getParams() == null) {
            log.info("文件不存在！");
            return null;
        }

        TypeReference<List<TemplateParamEntity>> typeReference = new TypeReference<List<TemplateParamEntity>>() {
        };
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<TemplateParamEntity> results = mapper.readValue(templateModel.getParams(), typeReference);
            List<String> type = new ArrayList<>(Arrays.asList("number", "text", "text_area", "date"));
            List<Byte> isNulls = new ArrayList<Byte>() {{
                add((byte) 1);
                add((byte) 0);
            }};
            // 判断模板参数是否正确
            for (String key : keys) {
                boolean bol = false;
                for (TemplateParamEntity result : results) {
                    if (key.equals(result.getKey())) {
                        bol = true;

                        if (!type.contains(result.getType())) {
                            return null;
                        }
                        if (!isNulls.contains(result.getIsNull())) {
                            return null;
                        }

                        break;
                    }
                }

                // 如果没有参数  说明参数不正确
                if (!bol) {
                    return null;
                }
            }
            return keys;
        } catch (Exception e) {
            return null;
        }
    }
}
