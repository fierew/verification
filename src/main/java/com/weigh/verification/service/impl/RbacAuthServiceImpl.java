package com.weigh.verification.service.impl;

import com.weigh.verification.dao.RbacResourceDao;
import com.weigh.verification.dao.RbacRoleDao;
import com.weigh.verification.dao.RbacRoleResourceDao;
import com.weigh.verification.dao.RbacUserDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacResourceModel;
import com.weigh.verification.model.RbacRoleModel;
import com.weigh.verification.model.RbacRoleResourceModel;
import com.weigh.verification.model.RbacUserModel;
import com.weigh.verification.service.RbacAuthService;
import com.weigh.verification.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 获取权限
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacAuthServiceImpl implements RbacAuthService {
    @Autowired
    private RbacUserDao rbacUserDao;

    @Autowired
    private RbacRoleResourceDao rbacRoleResourceDao;

    @Autowired
    private RbacResourceDao rbacResourceDao;

    @Override
    public Result getAuth(Integer id) {
        Result res = getUserAuth(id);
        if(res.getCode() != 200){
            return res;
        }

        List<String> access = new ArrayList<>();
        List<RbacResourceModel> rbacResourceInfos = (List<RbacResourceModel>) res.getData();
        for (RbacResourceModel rbacResourceMenuInfo : rbacResourceInfos) {
            if (rbacResourceMenuInfo.getType() == 0) {
                access.add(rbacResourceMenuInfo.getPath());
            } else if (rbacResourceMenuInfo.getType() == 1) {
                String[] keys = rbacResourceMenuInfo.getKey().split(",");
                access.addAll(Arrays.asList(keys));
            }
        }

        Result result = new Result();
        result.setCode(200);
        result.setData(access);
        result.setMsg("success");
        return result;
    }

    @Override
    public Result getMenu(Integer id) {
        Result res = getUserAuth(id);
        if(res.getCode() != 200){
            return res;
        }

        List<RbacResourceModel> menus = new ArrayList<>();
        List<RbacResourceModel> rbacResourceInfos = (List<RbacResourceModel>) res.getData();

        for (RbacResourceModel rbacResourceMenuInfo : rbacResourceInfos) {
            if (rbacResourceMenuInfo.getType() == 0 && rbacResourceMenuInfo.getIsHide() == 0) {
                menus.add(rbacResourceMenuInfo);
            }
        }

        Result result = new Result();

        try{
            List<Map<String, Object>> tree = new TreeUtil(menus).buildTree();
            result.setCode(200);
            result.setData(tree);
            result.setMsg("success");
        }catch (Exception e){
            result.setCode(400);
            result.setMsg(e.getMessage());
        }

        return result;
    }

    private Result getUserAuth(Integer id){
        // 判断用户是否存在
        RbacUserModel rbacUserModel = rbacUserDao.getInfoById(id);

        Result result = new Result();
        if (rbacUserModel == null) {
            result.setCode(400);
            result.setMsg("用户不存在，可能被删除！");
            return result;
        }

        List<RbacResourceModel> rbacResourceInfos;
        if ("administrator".equals(rbacUserModel.getEmail())) {
            // 获取所有权限
            rbacResourceInfos = rbacResourceDao.getAll();
        } else {
            if (rbacUserModel.getState() == 0) {
                result.setCode(403);
                result.setMsg("用户被禁用，请联系管理员！");
                return result;
            }

            List<String> auths = new ArrayList<>();
            List<Integer> resourceIds = new ArrayList<>();

            List<RbacRoleResourceModel> rbacRoleResourceModelList = rbacRoleResourceDao.getInfoByRoleId(rbacUserModel.getRoleId());

            if (rbacRoleResourceModelList == null) {
                result.setCode(200);
                result.setData(auths);
                result.setMsg("success");
                return result;
            }

            for (RbacRoleResourceModel rbacRoleResourceModel : rbacRoleResourceModelList) {
                resourceIds.add(rbacRoleResourceModel.getResourceId());
            }

            rbacResourceInfos = rbacResourceDao.getInfoByIds(resourceIds, (byte) 1);
        }

        result.setCode(200);
        result.setData(rbacResourceInfos);
        result.setMsg("success");
        return result;
    }
}
