package com.nowcoder.community.dao;


import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectComments(@Param("entityType") int entityType,
                                 @Param("entityId")int entityId,
                                 @Param("offset") int offset,
                                 @Param("limit") int limit);

    int getTotalOfComment(@Param("entityType")int entityType,
                          @Param("entityId")int entityId);

    int addComment(Comment comment);
}
