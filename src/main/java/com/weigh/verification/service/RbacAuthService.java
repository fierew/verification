package com.weigh.verification.service;

import com.weigh.verification.entity.Result;

/**
 * @author xuyang
 */
public interface RbacAuthService {
    /**
     * 根据用户id查权限
     *
     * @param id 用户id
     * @return 权限信息
     */
    Result getAuth(Integer id);

    /**
     * 根据用户id获取菜单
     * @param id 用户id
     * @return 菜单信息
     */
    Result getMenu(Integer id);
}
