package com.haiyu.manager.service;

import com.haiyu.manager.pojo.SettingsPort;
import com.haiyu.manager.query.PortQuery;
import com.haiyu.manager.response.PageDataResult;

import java.util.Map;


/**
 * @Title: PortService
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:03:05
 */
public interface PortService {

    PageDataResult getPortList(PortQuery query, Integer pageNum, Integer pageSize);

    Map<String, Object> addPort(SettingsPort pojo);

    Map<String, Object> updatePort(SettingsPort pojo);

    Map<String, Object> delPort(Integer id, Integer status);

    Map<String, Object> recoverPort(Integer id, Integer status);

}
