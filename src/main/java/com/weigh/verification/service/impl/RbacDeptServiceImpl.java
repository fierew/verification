package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacDeptDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacDeptModel;
import com.weigh.verification.service.RbacDeptService;
import com.weigh.verification.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacDeptServiceImpl implements RbacDeptService {
    @Autowired
    private RbacDeptDao rbacDeptDao;

    @Override
    public Result add(RbacDeptModel rbacDeptModel) {

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        rbacDeptModel.setCreateTime(time);
        rbacDeptModel.setUpdateTime(time);

        Integer res = rbacDeptDao.add(rbacDeptModel);

        Result result = new Result();

        if (res != 1) {
            result.setCode(400);
            result.setMsg("新增机构失败");
        } else {
            result.setCode(200);
            result.setMsg("新增机构成功");
        }
        return result;
    }

    @Override
    public Result edit(Integer id, RbacDeptModel rbacDeptModel) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacDeptModel.setId(id);
        rbacDeptModel.setUpdateTime(time);

        Integer res = rbacDeptDao.edit(rbacDeptModel);

        Result result = new Result();

        if (res != 1) {
            result.setCode(400);
            result.setMsg("编辑机构失败");
        } else {
            result.setCode(200);
            result.setMsg("编辑机构成功");
        }
        return result;
    }

    @Override
    public Result delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        List<RbacDeptModel> all = rbacDeptDao.getAll();

        Result result = new Result();

        if (all == null) {
            result.setCode(400);
            result.setMsg("未找到要删除的数据");
            return result;
        }

        try {
            List<Integer> ids = new TreeUtil(all).buildTreeIds(id);
            log.info(String.valueOf(ids));
            rbacDeptDao.deletes(ids, time);

            result.setCode(200);
            result.setMsg("删除机构成功");
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @Override
    public Result getAll() {
        Result result = new Result();
        List<RbacDeptModel> all = rbacDeptDao.getAll();

        result.setCode(200);
        result.setMsg("success");

        if (all == null) {
            result.setData(null);
            return result;
        }

        try {
            List<Map<String, Object>> tree = new TreeUtil(all).buildTree();
            result.setData(tree);
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMsg("解析树失败");
            return result;
        }
    }
}
