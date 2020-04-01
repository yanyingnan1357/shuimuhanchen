package cn.yyn.jsonConfig;

import cn.yyn.model.dto.FieldTemplateConfig;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class FieldTemplateService {

    @Resource(name = "fieldTemplates")
    private List<FieldTemplateConfig> defaultFields;

    /**
     * 可以获取配置类转化后的对象集合，并进行逻辑校验
     */
    public List<FieldTemplateConfig> getFieldConfigsByDelType() {
        List<FieldTemplateConfig> result = Lists.newArrayList();
        result.addAll(defaultFields);
        return result;
    }

}
