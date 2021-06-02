package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dao.SettingsPayTypeMapper;
import com.haiyu.manager.dto.PayTypeDTO;
import com.haiyu.manager.dto.PayTypeSearchDTO;
import com.haiyu.manager.pojo.SettingsPayType;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PayTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PayTypeServiceImpl
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 15:14:38
 */
@Service
public class PayTypeServiceImpl implements PayTypeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettingsPayTypeMapper settingsPayTypeMapper;

    @Override
    public PageDataResult getPayTypeList(PayTypeSearchDTO userSearch, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<PayTypeDTO> payTypes = settingsPayTypeMapper.getPayTypeList(userSearch);

        PageHelper.startPage(pageNum, pageSize);

        if (payTypes.size() != 0) {
            PageInfo<PayTypeDTO> pageInfo = new PageInfo<>(payTypes);
            pageDataResult.setList(payTypes);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    @Override
    public Map<String, Object> addPayType(SettingsPayType pojo) {
        Map<String, Object> data = new HashMap();
        try {
            SettingsPayType old = settingsPayTypeMapper.findByPayTypeName(pojo.getTypeName());
            if (old != null) {
                data.put("code", 0);
                data.put("msg", "该支付方式已存在！");
                logger.error("用户[新增]，结果=该支付方式已存在！");
                return data;
            }

            pojo.setCreatedTime(DateUtils.getCurrentDate());
            pojo.setTypeStatus(1);
            int result = settingsPayTypeMapper.insert(pojo);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("支付方式[新增]，结果=新增失败！");
                return data;
            }
            data.put("code", 1);
            data.put("msg", "新增成功！");
            logger.info("支付方式[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付方式[新增]异常！", e);
            return data;
        }
        return data;
    }


}
