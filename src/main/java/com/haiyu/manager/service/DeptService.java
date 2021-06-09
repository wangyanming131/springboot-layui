package com.haiyu.manager.service;

import com.haiyu.manager.pojo.BaseDept;
import com.haiyu.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * @Title: DeptService
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2018/11/21 14:25
 */
public interface DeptService {

    PageDataResult getDeptList(Integer pageNum, Integer pageSize);

    List<BaseDept> getDepts();

    BaseDept findDeptById(Integer id);

    Map<String, Object> updateDept(BaseDept pojo);

    Map<String, Object> delDept(Integer id, Integer status);

    Map<String, Object> recoverDept(Integer id, Integer status);

    Map<String, Object> addDept(BaseDept pojo);

}
