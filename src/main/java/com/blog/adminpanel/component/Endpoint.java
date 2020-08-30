
package com.blog.adminpanel.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.adminpanel.oauth.SettingsRepository;



@Component

public class Endpoint {

	public static Map<String, String> admin = new HashMap<>();
	public static Map<String, String> blogger = new HashMap<>();
	public static Map<String, String> all = new HashMap<>();


	/*
	 * @Autowired static SettingsRepository settings;
	 */
	private static SettingsRepository settings;
	
	
	

	@Autowired
	public Endpoint(SettingsRepository settings) {
		Endpoint.settings = settings;
		all.put("KEY_GET_OUTH_TOKEN", "oauth/token");
		all.put("REGISTER_USER","all/registration");
		blogger.putAll(all);
		blogger.put("KEY_GET_BLOGGER","blogger/{bloggerId}");
		blogger.put("KEY_CREATE_BLOG","blogger/post");
		blogger.put("KEY_GET_BLOG","blogger/post/{postId}");
		blogger.put("KEY_GET_MY_BLOG_POSTS","blogger/post/my/list?bloggerId={bloggerId}&page={page}");
		blogger.put("KEY_DELETE_BLOG","blogger/post/");
		blogger.put("KEY_GET_ALL_APPROVED_BLOG","blogger/post?fromDate={fromDate}&page={page}");
		blogger.put("KEY_CREATE_COMMENT","blogger/comment");
		blogger.put("KEY_GET_COMMENTS_BY_BLOG","blogger/comment?blogId={blogId}");
		blogger.put("KEY_DELETE_COMMENT","blogger/comment/");
		blogger.put("KEY_SAVE_VOTE","blogger/vote");
		admin.putAll(blogger);
		admin.put("KEY_GET_ROLE","admin/user/roles");
		admin.put("KEY_CREATE_BLOGGER","admin/user");
		admin.put("KEY_GET_BLOGGER_BY_ROLE","admin/user?roleId={roleId}&approvalStatus={approvalStatus}");
		admin.put("KEY_APPROVE_BLOGGER","admin/user/approve?bloggerId={bloggerId}");
		admin.put("KEY_DELETE_BLOGGER","admin/user/");
		admin.put("KEY_GET_ALL_BLOG_POST","admin/blog?fromDate={fromDate}&type={type}&page={page}");
		admin.put("KEY_APPROVE_BLOG","admin/blog/approve?blogId={blogId}&bloggerId={bloggerId}");
		
	}

	public static String fetchEndpoint(String key) {
		// String roleID = settings.getRolename();
		String roleID = settings.getRoleName();
		System.out.println(roleID + " " + key);
		switch (roleID) {

		case "ROLE_ADMIN":
			// break;
			System.out.println("admin key is" + admin.get(key));
			return admin.get(key);

		
		case "ROLE_BLOGGER":
			// break;
			System.out.println("employee key is" + blogger.get(key));
			return blogger.get(key);

		}
		return "Default String";

	}

}
