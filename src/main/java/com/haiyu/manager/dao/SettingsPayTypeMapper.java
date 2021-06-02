package com.haiyu.manager.dao;


import com.haiyu.manager.dto.PayTypeDTO;
import com.haiyu.manager.dto.PayTypeSearchDTO;
import com.haiyu.manager.pojo.SettingsPayType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SettingsPayTypeMapper extends MyMapper<SettingsPayType> {

    List<PayTypeDTO> getPayTypeList(PayTypeSearchDTO payTypeSearchDTO);

    SettingsPayType findByPayTypeName(@Param("typeName") String typeName);


}