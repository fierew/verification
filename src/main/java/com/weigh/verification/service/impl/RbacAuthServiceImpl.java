package com.weigh.verification.service.impl;

import com.weigh.verification.dao.*;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.*;
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

    @Autowired
    private RbacDeptDao rbacDeptDao;

    @Override
    public Result getApiAuth(Integer id) {
        Result res = getUserAuth(id);
        if(res.getCode() != 200){
            return res;
        }

        List<String> access = new ArrayList<>();
        List<RbacResourceModel> rbacResourceInfos = (List<RbacResourceModel>) res.getData();
        for (RbacResourceModel rbacResourceMenuInfo : rbacResourceInfos) {
            if (rbacResourceMenuInfo.getType() == 2) {
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

    private Result getUserIdsToUserIdOnType(Integer userId, Byte dataRange, String deptIdArray)
    {
        List<Integer> userIds = new ArrayList<>();
        Result result = new Result();

        // 根据用户id获取部门id
        RbacUserModel rbacUserModel = rbacUserDao.getInfoById(userId);
        Integer deptId = rbacUserModel.getDeptId();

        switch (dataRange){
            case 1:
                // 1：仅允许查看本部门
                // 根据部门id获取所有用户id
                List<RbacUserModel> rbacUserList = rbacUserDao.getIdsByDeptId(deptId);
                for (RbacUserModel rbacUser: rbacUserList) {
                    userIds.add(rbacUser.getId());
                }
                return result;
            case 2:
                // 2：允许查看本部门及下属部门
                List<RbacDeptModel> all = rbacDeptDao.getAll();

                result.setCode(200);
                result.setMsg("success");

                if (all == null) {
                    result.setData(null);
                    return result;
                }

                try {
                    List<Integer> deptIds = new TreeUtil(all).buildTreeIds(deptId);
                    List<RbacUserModel> rbacUserLists = rbacUserDao.getIdsByDeptIds(deptIds);
                    for (RbacUserModel rbacUser: rbacUserLists) {
                        userIds.add(rbacUser.getId());
                    }
                    result.setData(userIds);
                    result.setCode(200);
                    result.setMsg("success");
                } catch (Exception e) {
                    result.setCode(400);
                    result.setMsg("解析树失败");
                }
                return result;
            case 3:
                // 3：自定义
                List<RbacDeptModel> all1 = rbacDeptDao.getAll();

                result.setCode(200);
                result.setMsg("success");

                if (all1 == null) {
                    result.setData(null);
                    return result;
                }

                return result;
            default:
                // 0:仅允许查看自己
                userIds.add(userId);
                result.setData(userIds);
                return result;
        }
    }
}
