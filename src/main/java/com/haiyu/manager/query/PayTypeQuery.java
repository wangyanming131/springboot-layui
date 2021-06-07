package com.haiyu.manager.query;

import lombok.Data;

/**
 * @Title: PayTypeQuery
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 14:53:57
 */
@Data
public class PayTypeQuery {

    private String typeName;

    private String typeStatus;

    private String startTime;

    private String endTime;
}
