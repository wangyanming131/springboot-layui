package com.haiyu.manager.query;


import lombok.Data;

/**
 * @Title: PortQuery
 * @Description:
 * @author: ChenXue
 * @version: 1.0
 * @date: 2021-6-2 14:53:57
 */
@Data
public class PortQuery {

    private Integer value;

    private String description;

    private String link;

    private Integer starting_type;

    private String startTime;

    private String endTime;

    private String status;

}
