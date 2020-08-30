package com.blog.adminpanel.service;

import com.blog.adminpanel.domain.dto.CommentDTO;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:51 PM
 * @project adminpanel
 */

public interface ICommentService {
    CommentDTO createComment(CommentDTO comment);
    String deleteComment(Long commentId,Long bloggerId);
    List<CommentDTO> getCommentsByBlog(Long blogId);
}
