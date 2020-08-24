package com.weigh.verification.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.TemplateDao;
import com.weigh.verification.dao.VerificationDao;
import com.weigh.verification.dao.VerificationLogDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TemplateParamEntity;
import com.weigh.verification.entity.VerificationLogEntity;
import com.weigh.verification.entity.VerificationParamEntity;
import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.model.VerificationLogModel;
import com.weigh.verification.model.VerificationModel;
import com.weigh.verification.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private VerificationDao verificationDao;

    @Autowired
    private VerificationLogDao verificationLogDao;

    @Override
    public Result add(Integer userId, VerificationModel verificationModel) {
        Result result = new Result();

        Result checkResult = this.verificationDataCheck(verificationModel);
        if (checkResult.getCode() != 200) {
            return checkResult;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        verificationModel.setUserId(userId);
        verificationModel.setCreateTime(time);
        verificationModel.setUpdateTime(time);
        Integer res = verificationDao.add(verificationModel);

        if (res != 1) {
            result.setMsg("新增鉴定失败");
            result.setCode(400);
            return result;
        }

        result.setMsg("新增鉴定成功");
        result.setCode(200);
        return result;
    }

    @Override
    public Result edit(Integer id, VerificationModel verificationModel) {
        Result result = new Result();

        Result checkResult = this.verificationDataCheck(verificationModel);
        if (checkResult.getCode() != 200) {
            return checkResult;
        }
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        verificationModel.setUpdateTime(time);
        verificationModel.setId(id);
        Integer res = verificationDao.edit(verificationModel);
        if (res != 1) {
            result.setMsg("修改鉴定失败");
            result.setCode(400);
            return result;
        }

        result.setMsg("修改鉴定成功");
        result.setCode(200);
        return result;
    }

    @Override
    public Result delete(Integer id) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        Integer res = verificationDao.delete(id, time);
        if (res != 1) {
            result.setMsg("删除鉴定失败");
            result.setCode(400);
            return result;
        }

        result.setMsg("删除鉴定成功");
        result.setCode(200);
        return result;
    }

    @Override
    public Result getInfo(Integer id) {
        Result result = new Result();
        VerificationModel res = verificationDao.getInfo(id);
        if (res == null) {
            result.setMsg("获取鉴定信息失败");
            result.setCode(400);
            return result;
        }

        result.setMsg("获取鉴定信息成功");
        result.setCode(200);
        result.setData(res);
        return result;
    }

    @Override
    public Result getList(Integer page, Integer pageSize, VerificationModel verificationModel) {
        Result result = new Result();

        PageHelper.startPage(page, pageSize);
        List<VerificationModel> list = verificationDao.getList(verificationModel);
        PageInfo<VerificationModel> res = new PageInfo<>(list);

        result.setMsg("获取鉴定信息成功");
        result.setCode(200);
        result.setData(res);
        return result;
    }

    @Override
    public Result getLogList(Integer page, Integer pageSize, Integer id) {
        Result result = new Result();

        PageHelper.startPage(page, pageSize);
        List<VerificationLogEntity> list = verificationLogDao.getLogList(id);
        PageInfo<VerificationLogEntity> res = new PageInfo<>(list);

        result.setMsg("获取鉴定信息日志成功");
        result.setCode(200);
        result.setData(res);
        return result;
    }

    @Override
    public Result addLog(Integer userId, VerificationLogModel verificationLogModel) {
        Result result = new Result();

        verificationLogModel.setUserId(userId);
        Integer res = verificationLogDao.addLog(verificationLogModel);

        if (res != 1) {
            result.setMsg("新增鉴定信息日志失败");
            result.setCode(400);
            return result;
        }

        result.setMsg("新增鉴定信息日志成功");
        result.setCode(200);
        return result;
    }

    private Result verificationDataCheck(VerificationModel verificationModel) {
        Result result = new Result();
        TemplateModel templateInfo = templateDao.getInfo(verificationModel.getTemplateId());
        if (templateInfo == null) {
            result.setMsg("模板不存在");
            result.setCode(400);
            return result;
        }

        if (templateInfo.getParams() == null) {
            result.setMsg("模板参数不存在");
            result.setCode(400);
            return result;
        }

        try {
            TypeReference<List<TemplateParamEntity>> templateParamEntity = new TypeReference<List<TemplateParamEntity>>() {
            };
            TypeReference<List<VerificationParamEntity>> verificationParamEntity = new TypeReference<List<VerificationParamEntity>>() {
            };

            ObjectMapper mapper = new ObjectMapper();

            List<TemplateParamEntity> templateParamResults = mapper.readValue(templateInfo.getParams(), templateParamEntity);
            List<VerificationParamEntity> verificationParamResults = mapper.readValue(verificationModel.getParams(), verificationParamEntity);

            for (TemplateParamEntity templateParam : templateParamResults) {
                for (VerificationParamEntity verificationParam : verificationParamResults) {
                    if (templateParam.getKey().equals(verificationParam.getKey())) {
                        if (templateParam.getIsNull() == '1') {
                            if (verificationParam.getValue() == null) {
                                result.setMsg("'" + templateParam.getKey() + "'的值必须有值");
                                result.setCode(400);
                                return result;
                            }
                        }

                        if ("number".equals(templateParam.getType())) {
                            if (!verificationParam.getValue().matches("[0-9]+")) {
                                result.setMsg("'" + templateParam.getKey() + "'的值必须是数字");
                                result.setCode(400);
                                return result;
                            }
                        }
                        break;
                    }
                }
            }

            result.setMsg("success");
            result.setCode(200);
            return result;
        } catch (Exception e) {
            result.setMsg("数据解析失败");
            result.setCode(400);
            return result;
        }
    }
}
