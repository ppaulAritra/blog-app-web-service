package com.blog.adminpanel.service;

import org.springframework.data.domain.Page;

import com.blog.adminpanel.domain.dto.BlogDTO;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:49 PM
 * @project adminpanel
 */

public interface IBlogService {
	BlogDTO createBlogPost(BlogDTO blog);
	BlogDTO getBlog(Long postId);
	BlogDTO approveBlog(Long blogId,Long bloggerId);
	String deleteBlog(Long blogId,Long bloggerId) throws Exception;
	Page<BlogDTO> getApprovedBlogPost(String fromDate, int page);
	Page<BlogDTO> getBlogByBlogger(Long bloggerId,int page);
	Page<BlogDTO> getAllBlogByDate(String fromDate,String type,int page);
}

