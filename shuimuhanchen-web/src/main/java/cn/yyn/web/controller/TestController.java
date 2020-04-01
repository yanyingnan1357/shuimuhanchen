package cn.yyn.web.controller;

import cn.yyn.jsonConfig.FieldTemplateService;
import cn.yyn.model.dto.FieldTemplateConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 测试Controller
 *
 * @author:yyn
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
     private FieldTemplateService fieldTemplateService;

    @GetMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "恭喜你测试成功！");
        return "test";
    }

    @GetMapping("/jsonConfig")
    public List<FieldTemplateConfig> jsonConfig(){
        return fieldTemplateService.getFieldConfigsByDelType();
    }

}
