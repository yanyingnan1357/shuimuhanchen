package cn.yyn.dao;

import cn.yyn.dao.interceptor.TableShard;
import cn.yyn.model.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@TableShard(tableName = "sys_log", shardByKeys = {"remark","remarks"})
public interface SysLogMapper {

    void insertLog(@Param("record")SysLog record);

//    List<SysLog> selectLogBatch(@Param("id")Long id, @Param("limit")Integer limit);

    List<SysLog> selectLogBatchByRemark(@Param("id")Long id, @Param("remark")String remark, @Param("limit")Integer limit);

}