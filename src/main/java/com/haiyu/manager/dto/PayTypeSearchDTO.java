package com.haiyu.manager.dto;


import lombok.Data;

/**
 * @Title: PayTypeSearchDTO
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 14:53:57
 */
@Data
public class PayTypeSearchDTO {
    

    private String typeName;

    private String typeStatus;

    private String startTime;

    private String endTime;

}
