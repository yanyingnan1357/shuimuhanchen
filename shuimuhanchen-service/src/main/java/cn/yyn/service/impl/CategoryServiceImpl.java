package cn.yyn.service.impl;

import cn.yyn.model.entity.CategoryInfo;
import cn.yyn.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类Service实现类
 *
 * @author:yyn
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Override
    public void addCategory(CategoryInfo categoryInfo) {

    }

    @Override
    public void deleteCategoryById(Long id) {

    }

    @Override
    public void updateCategory(CategoryInfo categoryInfo) {

    }

    @Override
    public CategoryInfo getOneById(Long id) {
        return null;
    }

    @Override
    public List<CategoryInfo> listAllCategory() {
        return null;
    }
}
