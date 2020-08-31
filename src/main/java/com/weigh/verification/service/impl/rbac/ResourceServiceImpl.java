package com.weigh.verification.service.impl.rbac;

import com.weigh.verification.dao.rbac.ResourceDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.service.rbac.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<ResourceModel> getTree() {
        return null;
    }

    @Override
    public Result add(ResourceModel resourceModel) {
        Result result = new Result();
        Integer res = resourceDao.add(resourceModel);

        if(res != 1){
            result.setCode(400);
            result.setMsg("新增资源失败！");
        }else{
            result.setCode(200);
            result.setMsg("新增资源成功过");
        }
        return result;
    }

    @Override
    public Result edit(ResourceModel resourceModel) {
        return null;
    }

    @Override
    public Result delete(Integer id) {
        return null;
    }

    @Override
    public Result modifyState(Integer id) {
        return null;
    }
}
