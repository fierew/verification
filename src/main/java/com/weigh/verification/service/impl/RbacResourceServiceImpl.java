package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacResourceDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacResourceModel;
import com.weigh.verification.service.RbacResourceService;
import com.weigh.verification.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 资源管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacResourceServiceImpl implements RbacResourceService {
    @Autowired
    private RbacResourceDao rbacResourceDao;

    @Override
    public Result add(RbacResourceModel rbacResourceModel) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacResourceModel.setCreateTime(time);
        rbacResourceModel.setUpdateTime(time);

        Integer res = rbacResourceDao.add(rbacResourceModel);

        if (res != 1) {
            result.setCode(400);
            result.setMsg("新增资源失败！");
        } else {
            result.setCode(200);
            result.setMsg("新增资源成功");
        }
        return result;
    }

    @Override
    public Result edit(Integer id, RbacResourceModel rbacResourceModel) {
        Result result = new Result();
        rbacResourceModel.setId(id);

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacResourceModel.setUpdateTime(time);

        Integer res = rbacResourceDao.edit(rbacResourceModel);

        if (res != 1) {
            result.setCode(400);
            result.setMsg("编辑资源失败！");
        } else {
            result.setCode(200);
            result.setMsg("编辑资源成功");
        }

        return result;
    }

    @Override
    public Result delete(Integer id) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RbacResourceModel> all = rbacResourceDao.getAll();

        if (all == null) {
            result.setCode(400);
            result.setMsg("未找到要删除的数据");
            return result;
        }

        try {
            List<Integer> ids = new TreeUtil(all).buildTreeIds(id);

            rbacResourceDao.deletes(ids, time);

            result.setCode(200);
            result.setMsg("删除资源成功");
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @Override
    public Result modifyState(Integer id, Byte state) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        Integer res = rbacResourceDao.modifyState(id, state, time);

        if (res != 1) {
            result.setCode(400);
            result.setMsg("修改资源状态失败！");
        } else {
            result.setCode(200);
            result.setMsg("修改资源状态成功");
        }

        return result;
    }

    @Override
    public Result getAll() {
        Result result = new Result();

        List<RbacResourceModel> all = rbacResourceDao.getAll();

        result.setCode(200);
        result.setMsg("success");

        if (all == null) {
            result.setData(null);
            return result;
        }

        try {
            // 将数据解析成tree
            List<Map<String, Object>> tree = new TreeUtil(all).buildTree();

            result.setData(tree);
            return result;
        } catch (Exception e) {

            result.setCode(400);
            result.setMsg("解析树失败！");
            return result;
        }

    }
}
