package cn.yyn.web.controller;

import cn.yyn.model.entity.CategoryInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台控制器
 *
 * @author:yyn
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    /**
     * 增加一条分类信息数据
     *
     * @return
     */
    @ApiOperation("增加分类信息")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("/addCategoryInfo")
    public String addCategoryInfo(@RequestBody CategoryInfo categoryInfo) {
        categoryService.addCategory(categoryInfo);
        return null;
    }

    /**
     * 更新/编辑一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("更新/编辑分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    })
    @PutMapping("/updateCategoryInfo/{id}")
    public String updateCategoryInfo(@PathVariable Long id, @RequestBody CategoryInfo categoryInfo) {
        categoryService.updateCategory(categoryInfo);
        return null;
    }

    /**
     * 根据ID删除分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @DeleteMapping("/deleteCategoryInfo/{id}")
    public String deleteCategoryInfo(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return null;
    }

    /**
     * 通过id获取一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一条分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping("/getCategoryInfo/{id}")
    public CategoryInfo getCategoryInfo(@PathVariable Long id) {
        return categoryService.getOneById(id);
    }

    /**
     * 获取所有分类信息
     *
     * @return
     */
    @ApiOperation("获取所有分类信息")
    @GetMapping("/listAllCategoryInfo/list")
    public List<CategoryInfo> listAllCategoryInfo() {
        return categoryService.listAllCategory();
    }

}
