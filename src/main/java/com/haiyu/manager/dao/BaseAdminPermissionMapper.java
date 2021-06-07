package com.haiyu.manager.dao;


import com.haiyu.manager.pojo.BaseAdminPermission;
import com.haiyu.manager.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseAdminPermissionMapper extends MyMapper<BaseAdminPermission> {
    List<PermissionVO> getPermissionList();

    List<PermissionVO> parentPermissionList();

    int updatePermission(BaseAdminPermission permission);

    List<PermissionVO> getPermissionListByPId(@Param("pid") Integer pid);
}