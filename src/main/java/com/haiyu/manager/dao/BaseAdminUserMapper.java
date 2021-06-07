package com.haiyu.manager.dao;


import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.query.UserQuery;
import com.haiyu.manager.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseAdminUserMapper extends MyMapper<BaseAdminUser> {

    List<UserVO> getUserList(UserQuery query);

    BaseAdminUser getUserByUserName(@Param("sysUserName") String sysUserName, @Param("id") Integer id);

    int updateUserStatus(@Param("id") Integer id, @Param("status") Integer status);

    int updateUser(BaseAdminUser user);

    BaseAdminUser findByUserName(@Param("userName") String userName);

    int updatePwd(@Param("userName") String userName, @Param("password") String password);

}