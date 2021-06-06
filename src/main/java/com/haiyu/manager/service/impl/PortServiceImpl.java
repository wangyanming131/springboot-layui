package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dao.SettingsPortMapper;
import com.haiyu.manager.pojo.SettingsPort;
import com.haiyu.manager.query.PortQuery;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PortService;
import com.haiyu.manager.vo.PortVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PortServiceImpl
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:14:38
 */
@Service
public class PortServiceImpl implements PortService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettingsPortMapper settingsPortMapper;

    @Override
    public PageDataResult getPortList(PortQuery query, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<PortVO> vos = settingsPortMapper.getPortList(query);

        PageHelper.startPage(pageNum, pageSize);

        if (vos.size() != 0) {
            PageInfo<PortVO> pageInfo = new PageInfo<>(vos);
            pageDataResult.setList(vos);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    @Override
    public Map<String, Object> addPort(SettingsPort pojo) {
        Map<String, Object> data = new HashMap();
        try {

            SettingsPort old = settingsPortMapper.findByValue(pojo.getValue());

            if (old != null) {
                data.put("code", 0);
                data.put("msg", "该端口号已占用！");
                logger.error("端口号[新增]，结果=该端口号已占用！");
                return data;
            }

            pojo.setCreatedTime(DateUtils.getCurrentDate());
            pojo.setStatus(1);
            int result = settingsPortMapper.insert(pojo);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("端口号[新增]，结果=新增失败！");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "新增成功！");
            logger.info("端口号[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("msg", "端口号[新增]异常！");
            logger.error("端口号[新增]异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> updatePort(SettingsPort pojo) {
        Map<String, Object> data = new HashMap();

        // 根据id查询
        SettingsPort port = settingsPortMapper.selectByPrimaryKey(pojo.getId());
        if (port.getValue().equals(pojo.getValue())) {
            // 端口号未修改,此时流程往下进行
        } else {
            // 端口号修改时,判断前端新端口号在数据库中是否存在
            Integer value = pojo.getValue();
            // 端口号可以修改
            SettingsPort old = settingsPortMapper.findByValue(value);
            // 存在结果时,如果前端传值相同说明是已存在的同一条数据,允许修改
            if (old != null) {

                data.put("code", 0);
                data.put("msg", "端口号已占用！");
                logger.error("端口号[更新]，结果=端口号已占用！");
                return data;
            }

        }

        pojo.setUpdatedTime(DateUtils.getCurrentDate());
        int rows = settingsPortMapper.updatePort(pojo);
        if (rows == 0) {
            data.put("code", 0);
            data.put("msg", "更新失败！");
            logger.error("端口号[更新]，结果=更新失败！");
            return data;
        }
        data.put("code", 1);
        data.put("msg", "更新成功！");
        logger.info("端口号[更新]，结果=更新成功！");
        return data;
    }

    @Override
    public Map<String, Object> delPort(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除端口号
            int result = settingsPortMapper.updatePortStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "删除端口号失败");
                logger.error("删除端口号失败");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "删除端口号成功");
            logger.info("删除端口号成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除端口号异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> recoverPort(Integer id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = settingsPortMapper.updatePortStatus(id, status);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "恢复端口号失败");
            }
            data.put("code", 1);
            data.put("msg", "恢复端口号成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("恢复端口号异常！", e);
        }
        return data;
    }
}
