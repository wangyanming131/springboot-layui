package com.haiyu.manager.controller.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基本设置-支付方式
 */
@Controller
@RequestMapping(value = "/settings/payType")
public class PayTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 跳转到角色管理
     *
     * @return
     */
    @RequestMapping("/payTypeManage")
    public String toPage() {
        logger.info("进入角色管理");
        return "/settings/payType/payTypeManage";
    }


}
