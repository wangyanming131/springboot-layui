package com.haiyu.manager.query;

import lombok.Data;

/**
 * @Title: DicTypeQuery
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 14:53:57
 */
@Data
public class DicTypeQuery {


    private String typeCode;

    private String typeName;

    private String status;

    private String startTime;

    private String endTime;
}
