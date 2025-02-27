package com.haiyu.manager.service;

import com.haiyu.manager.pojo.SettingsPayType;
import com.haiyu.manager.query.PayTypeQuery;
import com.haiyu.manager.response.PageDataResult;

import java.util.Map;


/**
 * @Title: PayTypeService
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:03:05
 */
public interface PayTypeService {

    PageDataResult getPayTypeList(PayTypeQuery query, Integer pageNum, Integer pageSize);

    Map<String, Object> addPayType(SettingsPayType pojo);

    Map<String, Object> updatePayType(SettingsPayType pojo);

    Map<String, Object> delPayType(Integer id, Integer status);

    Map<String, Object> recoverPayType(Integer id, Integer status);

}
