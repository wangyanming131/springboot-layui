package com.haiyu.manager.vo;

import lombok.Data;

;

/**
 * @Title: PortVO
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 16:41:28
 */
@Data
public class PortVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 端口号值0-65535
     */
    private Integer value;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 启动方式(1:手动;2:随机启动)starting在mysql中是关键字,赋值麻烦改为starting_type
     */
    private Integer starting_type;

    /**
     * 启动方式(1:手动;2:随机启动)中文
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
    private Integer status;

}
