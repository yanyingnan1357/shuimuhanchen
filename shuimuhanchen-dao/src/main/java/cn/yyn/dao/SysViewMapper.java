package cn.yyn.dao;

import cn.yyn.model.entity.SysView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysViewMapper {

    void insertView(@Param("record")SysView record);

    List<SysView> selectViewBatch(@Param("id")Long id, @Param("limit")Integer limit);

}