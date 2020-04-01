package cn.yyn.web.controller;

import cn.yyn.model.dto.ArticleCommentDto;
import cn.yyn.model.entity.CommentInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台控制器
 *
 * @author:yyn
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    /**
     * 通过评论ID删除文章评论信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除文章评论信息")
    @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "Long")
    @DeleteMapping("/deleteArticleComment/{id}")
    public String deleteArticleComment(@PathVariable Long id) {
        commentService.deleteArticleCommentById(id);
        return null;
    }

    /**
     * 通过id删除某一条留言
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一条留言")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return null;
    }

    /**
     * 回复留言/评论，通过id去找到对应的Email
     *
     * @param id
     * @return
     */
    @ApiOperation("回复留言/评论")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @GetMapping("/replyComment/{id}")
    public String replyComment(@PathVariable Long id) {
        return null;
    }

    /**
     * 通过id获取某一条评论/留言
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一条评论/留言")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @GetMapping("/getCommentById/{id}")
    public CommentInfo getCommentById(@PathVariable Long id) {
        return null;
    }

    /**
     * 获取所有的留言信息
     *
     * @return
     */
    @ApiOperation("获取所有的留言信息")
    @GetMapping("/listAllComment")
    public List<CommentInfo> listAllComment() {
        return commentService.listAllComment();
    }

    /**
     * 通过文章ID获取某一篇文章的评论信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一篇文章的评论信息")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("/listMessageByArticleId/{id}")
    public List<ArticleCommentDto> listMessageByArticleId(@PathVariable Long id) {
        return commentService.listAllArticleCommentById(id);
    }

    /**
     * 给某一篇文章增加一条评论信息
     *
     * @return
     */
    @ApiOperation("给文章中增加一条评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("/addArticleComment/{id}")
    public String addArticleComment(@PathVariable Long id, @RequestBody ArticleCommentDto articleCommentDto, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        articleCommentDto.setIp(ip);
        articleCommentDto.setArticleId(id);
        commentService.addArticleComment(articleCommentDto);

        return null;
    }

    /**
     * 增加一条留言
     *
     * @return
     */
    @ApiOperation("增加一条留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("/addMessage")
    public String addMessage(@RequestBody CommentInfo commentInfo, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        commentInfo.setIp(ip);
        commentService.addComment(commentInfo);

        return null;
    }

}
