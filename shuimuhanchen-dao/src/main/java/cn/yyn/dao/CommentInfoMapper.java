package cn.yyn.dao;

import cn.yyn.model.entity.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentInfoMapper {

    int insertCommentInfo(@Param("commentInfo")CommentInfo commentInfo);

    CommentInfo selectCommentInfoById(@Param("id")Long id);

}