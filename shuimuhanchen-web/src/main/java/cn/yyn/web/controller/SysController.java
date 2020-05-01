package cn.yyn.web.controller;

import cn.yyn.model.entity.SysLog;
import cn.yyn.model.entity.SysView;
import cn.yyn.service.SysService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志Controller
 *
 * @author:yyn
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Resource
    private SysService sysService;



    private static final Integer batchNum = 10;

    /**
     * 返回所有的系统日志记录信息
     */
    @ApiOperation("返回所有的SysLog信息")
    @GetMapping("/getLog")
    public List<SysLog> getAllLog() {
        List<SysLog> sysLogs = new ArrayList<>();
        String remark = "/shuimuhanchen/";
        Long id = 0L;
        while (true) {
            List<SysLog> batchLog = sysService.getBatchLog(id, remark, batchNum);
            sysLogs.addAll(batchLog);
            int size = batchLog.size();
            if(CollectionUtils.isEmpty(batchLog) || size < batchNum){
                break;
            }
            id = batchLog.get(size - 1).getId();
        }
        return sysLogs;
    }

    /**
     * 返回所有的系统浏览记录信息
     */
    @ApiOperation("返回所有的SysView信息")
    @GetMapping("/getView")
    public List<SysView> getAllView() {
        List<SysView> sysViews = new ArrayList<>();
        Long id = 0L;
        while (true) {
            List<SysView> batchView = sysService.getBatchView(id, batchNum);
            sysViews.addAll(batchView);
            int size = batchView.size();
            if(CollectionUtils.isEmpty(batchView) || size < batchNum){
                break;
            }
            id = batchView.get(size - 1).getId();
        }
        return sysViews;
    }

}
