package com.haiyu.manager.controller.settings;

import com.haiyu.manager.dto.PayTypeSearchDTO;
import com.haiyu.manager.pojo.SettingsPayType;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PayTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本设置-支付方式
 */
@Controller
@RequestMapping(value = "/settings/payType")
public class PayTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PayTypeService payTypeService;


    /**
     * 跳转到支付方式管理
     *
     * @return
     */
    @RequestMapping("/payTypeManage")
    public String toPage() {
        logger.info("进入支付方式管理");
        return "/settings/payType/payTypeManage";
    }

    /**
     * 功能描述: 分页查询支付方式列表
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-2 14:32:56
     */
    @RequestMapping(value = "/getPayTypeList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getPayTypeList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ PayTypeSearchDTO payTypeSearchDTO) {
        /*logger.info("分页查询支付方式列表！搜索条件：payTypeSearch：" + payTypeSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        logger.info("url:/settings/payType/getPayTypeList");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取支付方式列表
            pdr = payTypeService.getPayTypeList(payTypeSearchDTO, pageNum, pageSize);
            logger.info("支付方式列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付方式列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 新增和更新支付方式
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 09:49:03
     */
    @RequestMapping(value = "/setPayType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> setPayType(SettingsPayType pojo) {
        logger.info("设置支付方式[新增或更新]！pojo:" + pojo);
        Map<String, Object> data = new HashMap();
        if (pojo.getId() == null) {
            data = payTypeService.addPayType(pojo);
        } else {
            data = payTypeService.updatePayType(pojo);
        }
        return data;
    }

    /**
     * 功能描述: 删除/恢复 支付方式
     *
     * @param:
     * @return:
     * @auther: ChenXue
     * @date: 2021-6-3 15:54:30
     */
    @RequestMapping(value = "/updatePayTypeStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePayTypeStatus(@RequestParam("id") Integer id, @RequestParam("typeStatus") Integer typeStatus) {
        logger.info("删除/恢复支付方式！id:" + id + " typeStatus:" + typeStatus);
        Map<String, Object> data = new HashMap<>();
        if (typeStatus == 0) {
            //删除支付方式
            data = payTypeService.delPayType(id, typeStatus);
        } else {
            //恢复支付方式
            data = payTypeService.recoverPayType(id, typeStatus);
        }
        return data;
    }


}
