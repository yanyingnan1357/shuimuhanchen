package cn.yyn.web.controller;

import cn.yyn.common.Markdown2HtmlUtil;
import cn.yyn.model.dto.ArticleDto;
import cn.yyn.model.dto.ArticleWithPictureDto;
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
@RequestMapping("/article")
public class ArticleController extends BaseController {
    /**
     * 增加一篇文章
     *
     * @return
     */
    @ApiOperation("增加一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PostMapping("/addArticle")
    public String addArticle(@RequestBody ArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return null;
    }


    /**
     * 删除一篇文章
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @DeleteMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return null;
    }

    /**
     * 编辑/更新一篇文章
     *
     * @return
     */
    @ApiOperation("编辑/更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PutMapping("/updateArticle/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.updateArticle(articleDto);
        return null;
    }

    /**
     * 改变某一篇文章的分类
     *
     * @param id
     * @return
     */
    @ApiOperation("改变文章分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true, dataType = "Long"),
    })
    @PutMapping("/changeArticleCategory/{id}")
    public String changeArticleCategory(@PathVariable Long id, Long categoryId) {
        articleService.updateArticleCategory(id, categoryId);
        return null;
    }

    /**
     * 通过题图的id更改题图信息
     *
     * @param id
     * @return
     */
    @ApiOperation("更改文章题图信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pictureUrl", value = "题图URL", required = true, dataType = "String")
    })
    @PutMapping("/updateArticlePicture/{id}")
    public String updateArticlePicture(@PathVariable Long id, String pictureUrl) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setPictureUrl(pictureUrl);
        articleService.updateArticle(articleDto);
        return null;
    }

    /**
     * 通过ID获取一篇文章，内容为MD源码格式
     *
     * @param id
     * @return
     */
    @ApiOperation("获取一篇文章，内容为md源码格式")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("/getArticleMD/{id}")
    public ArticleDto getArticleDtoById(@PathVariable Long id) {
        return articleService.getOneById(id);
    }


    /**
     * 获取所有文章列表
     *
     * @return
     */
    @ApiOperation("获取所有文章")
    @GetMapping("/listAllArticleInfo/list")
    public List<ArticleWithPictureDto> listAllArticleInfo() {
        return articleService.listAll();
    }

    /**
     * 获取某一个分类下的所有文章
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一个分类下的所有文章")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping("/listCategoryArticleInfo/{id}")
    public List<ArticleWithPictureDto> listArticleInfo(@PathVariable Long id) {
        return articleService.listByCategoryId(id);
    }

    /**
     * 获取最新的文章
     *
     * @return
     */
    @ApiOperation("获取最新的几篇文章")
    @GetMapping("/listLastestArticle/lastest")
    public List<ArticleWithPictureDto> listLastestArticle() {
        return articleService.listLastest();
    }

    /**
     * 通过文章的ID获取对应的文章信息
     *
     * @param id
     * @return 自己封装好的文章信息类
     */
    @ApiOperation("通过文章ID获取文章信息")
    @GetMapping("/getArticleById/{id}")
    public ArticleDto getArticleById(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setContent(Markdown2HtmlUtil.markdown2html(articleDto.getContent()));
        return articleDto;
    }

}
