package com.haiyu.manager.service;

import com.haiyu.manager.pojo.SettingsDicType;
import com.haiyu.manager.query.DicTypeQuery;
import com.haiyu.manager.response.PageDataResult;

import java.util.Map;


/**
 * @Title: DicTypeService
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:03:05
 */
public interface DicTypeService {

    PageDataResult getDicTypeList(DicTypeQuery query, Integer pageNum, Integer pageSize);

    Map<String, Object> addDicType(SettingsDicType pojo);

    Map<String, Object> updateDicType(SettingsDicType pojo);

    Map<String, Object> delDicType(Integer id, Integer status);

    Map<String, Object> recoverDicType(Integer id, Integer status);

}
