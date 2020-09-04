package com.weigh.verification.service.impl.rbac;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.rbac.ResourceDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.service.rbac.ResourceService;
import com.weigh.verification.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Result add(ResourceModel resourceModel) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        resourceModel.setCreateTime(time);
        resourceModel.setUpdateTime(time);

        Integer res = resourceDao.add(resourceModel);

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
    public Result edit(Integer id, ResourceModel resourceModel) {
        Result result = new Result();
        resourceModel.setId(id);

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        resourceModel.setUpdateTime(time);

        Integer res = resourceDao.edit(resourceModel);

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

        Integer res = resourceDao.delete(id, time);

        if (res != 1) {
            result.setCode(400);
            result.setMsg("删除资源失败！");
        } else {
            result.setCode(200);
            result.setMsg("删除资源成功");
        }

        return result;
    }

    @Override
    public Result modifyState(Integer id, Byte state) {
        Result result = new Result();

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        Integer res = resourceDao.modifyState(id, state, time);

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

        List<ResourceModel> all = resourceDao.getAll();

        result.setCode(200);
        result.setMsg("success");

        if(all == null){
            result.setData(null);
            return result;
        }

        try {
            // 将数据解析成tree
            List<Map<String, Object>> tree = new TreeUtil(Collections.singletonList(all)).buildTree();

            result.setData(tree);
            return result;
        }catch (Exception e){

            result.setCode(400);
            result.setMsg("解析树失败！");
            return result;
        }

    }
}
