package cn.yyn.jsonConfig;

import cn.yyn.model.dto.FieldTemplateConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class FieldBeanConfig {

    @Value("classpath:fields.json")
    private Resource fieldsResource;


    @Bean("fieldTemplates")
    public List<FieldTemplateConfig> fieldTemplates() throws IOException {
        JSONObject json = getJsonByResource(fieldsResource);
        if (json != null) {
            return JSON.parseArray(json.getString("fields"), FieldTemplateConfig.class);
        } else {
            throw new IOException();
        }
    }

    private JSONObject getJsonByResource(Resource resource) throws IOException {
        JSONObject json = null;
        if (resource.exists()) {
            json = JSON.parseObject(resource.getInputStream(), StandardCharsets.UTF_8, JSONObject.class,
                    // 自动关闭流
                    Feature.AutoCloseSource,
                    // 允许注释
                    Feature.AllowComment,
                    // 允许单引号
                    Feature.AllowSingleQuotes,
                    // 使用 Big decimal
                    Feature.UseBigDecimal);
        }
        return json;
    }

}
