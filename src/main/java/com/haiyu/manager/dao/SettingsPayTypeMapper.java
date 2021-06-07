package com.haiyu.manager.dao;


import com.haiyu.manager.pojo.SettingsPayType;
import com.haiyu.manager.query.PayTypeQuery;
import com.haiyu.manager.vo.PayTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SettingsPayTypeMapper extends MyMapper<SettingsPayType> {

    List<PayTypeVO> getPayTypeList(PayTypeQuery query);

    SettingsPayType findByPayTypeName(@Param("typeName") String typeName);

    int updatePayType(SettingsPayType pojo);

    int updatePayTypeStatus(@Param("id") Integer id, @Param("typeStatus") Integer typeStatus);

}