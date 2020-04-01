package cn.yyn.web.controller;

import cn.yyn.service.ArticleService;
import cn.yyn.service.CategoryService;
import cn.yyn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制器，包含了Controller层中共有的一些Service
 *
 * @author:yyn
 */
public class BaseController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    CategoryService categoryService;

}
