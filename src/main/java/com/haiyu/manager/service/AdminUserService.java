package com.haiyu.manager.service;

import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.query.UserQuery;
import com.haiyu.manager.response.PageDataResult;

import java.util.Map;


/**
 * @Title: AdminUserService
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/11/21 11:04
 */
public interface AdminUserService {

    PageDataResult getUserList(UserQuery query, Integer pageNum, Integer pageSize);

    Map<String, Object> addUser(BaseAdminUser user);

    Map<String, Object> updateUser(BaseAdminUser user);

    BaseAdminUser getUserById(Integer id);

    BaseAdminUser findByUserName(String userName);

    int updatePwd(String userName, String password);

    Map<String, Object> delUser(Integer id, Integer status);

    Map<String, Object> recoverUser(Integer id, Integer status);
}
