package cn.yyn.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义监听类
 */
public class ExcelListener extends AnalysisEventListener {

    private static Logger LOG = LoggerFactory.getLogger(ExcelListener.class);

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(object);
        //根据业务自行 do something
        doSomething();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            context.getInputStream().close();
        } catch (IOException e) {
            LOG.warn("文件流关闭异常！");
        }
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void clear() {
        if (datas != null) {
            getDatas().clear();//解析结束销毁不用的资源
        }
    }

    /**
     * 根据业务自行实现该方法，例如数据分批处理等
     */
    private void doSomething() {
    }
}