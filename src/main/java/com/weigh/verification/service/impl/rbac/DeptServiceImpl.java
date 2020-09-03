package com.weigh.verification.service.impl.rbac;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.rbac.DeptDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.DeptModel;
import com.weigh.verification.service.rbac.DeptService;
import com.weigh.verification.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 机构管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public Result add(DeptModel deptModel) {

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        deptModel.setCreateTime(time);
        deptModel.setUpdateTime(time);

        Integer res = deptDao.add(deptModel);

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
    public Result edit(Integer id, DeptModel deptModel) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        deptModel.setId(id);
        deptModel.setUpdateTime(time);

        Integer res = deptDao.edit(deptModel);

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

        Integer res = deptDao.delete(id, time);

        Result result = new Result();

        if (res != 1) {
            result.setCode(400);
            result.setMsg("删除机构失败");
        } else {
            result.setCode(200);
            result.setMsg("删除机构成功");
        }
        return result;
    }

    @Override
    public Result getAll() {
        Result result = new Result();
        List<DeptModel> all = deptDao.getAll();

        result.setCode(200);
        result.setMsg("success");

        if (all == null) {
            result.setData(null);
            return result;
        }

        try {

            List<Map<String, Object>> tree = new TreeUtil(Collections.singletonList(all)).buildTree();
            result.setData(tree);
            return result;
        } catch (Exception e) {
            result.setCode(400);
            result.setMsg("解析树失败");
            return result;
        }
    }
}
