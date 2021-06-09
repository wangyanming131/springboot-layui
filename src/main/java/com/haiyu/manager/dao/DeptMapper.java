package com.haiyu.manager.dao;

import com.haiyu.manager.pojo.BaseDept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface DeptMapper extends MyMapper<BaseDept> {

    List<BaseDept> getDeptList();

    List<BaseDept> getDepts();

    int updateDept(BaseDept role);

    int updateDeptStatus(@Param("id") Integer id, @Param("status") Integer status);

}