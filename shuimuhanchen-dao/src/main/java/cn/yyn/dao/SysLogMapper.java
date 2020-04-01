package cn.yyn.dao;

import cn.yyn.model.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper {

    void insertLog(@Param("record")SysLog record);

    List<SysLog> selectLogBatch(@Param("id")Long id, @Param("limit")Integer limit);

}