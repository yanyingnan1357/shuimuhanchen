package cn.yyn.service;

import cn.yyn.model.entity.CategoryInfo;

import java.util.List;

/**
 * 分类Service
 *
 */
public interface CategoryService {
    void addCategory(CategoryInfo categoryInfo);

    void deleteCategoryById(Long id);

    void updateCategory(CategoryInfo categoryInfo);

    CategoryInfo getOneById(Long id);

    List<CategoryInfo> listAllCategory();

}
