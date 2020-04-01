package cn.yyn.service;

import cn.yyn.model.entity.SysLog;
import cn.yyn.model.entity.SysView;

import java.util.List;

/**
 * 日志、访问统计等系统相关
 *
 */
public interface SysService {

    //添加操作日志
    void addLog(SysLog sysLog);

    //添加浏览记录
    void addView(SysView sysView);

    //批量获取
    List<SysLog> getBatchLog(Long id, Integer limit);

    //批量获取
    List<SysView> getBatchView(Long id, Integer limit);
}
