package com.haiyu.manager.vo;

import lombok.Data;

;

/**
 * @Title: PayTypeDTO
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 16:41:28
 */
@Data
public class PayTypeVO {

    private Integer id;

    /**
     * 支付方式代码
     */
    private String typeCode;

    /**
     * 支付方式中文名称
     */
    private String typeName;


    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 修改时间
     */
    private String updatedTime;

    /**
     * 状态(0:无效;1:有效)
     */
    private Integer typeStatus;


}
