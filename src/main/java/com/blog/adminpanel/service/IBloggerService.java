/**
 * 
 */
package com.blog.adminpanel.service;

import java.util.List;

import com.blog.adminpanel.domain.dto.BloggerDTO;

/**
 * @author Aritra
 *
 */
public interface IBloggerService {
	public BloggerDTO registerBlogger(BloggerDTO bloggerDTO);
	public BloggerDTO createBlogger(BloggerDTO bloggerDTO);
	public List<BloggerDTO> getBloggerByRoleAndStatus(Long roleId,String status);
	public BloggerDTO approveBlogger(Long bloggerId);
	public String deleteBlogger(Long bloggerId,Long decisionById) throws Exception;
	public BloggerDTO getBlogger(Long bloggerId);
	

}
