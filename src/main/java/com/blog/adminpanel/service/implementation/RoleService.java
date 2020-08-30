/**
 * 
 */
package com.blog.adminpanel.service.implementation;

import java.util.Arrays;
import java.util.List;

import com.blog.adminpanel.component.ApiUrlList;
import com.blog.adminpanel.component.Endpoint;
import com.blog.adminpanel.domain.dto.BloggerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.blog.adminpanel.domain.Role;

/**
 * @author Aritra
 *
 */
@Service
public class RoleService {
	@Autowired
	OAuth2RestTemplate restTemplate;
	@Value("${blogapi.resource}")
	private String baseUrl;
	public List<Role> getAllRoles(){
		String url=baseUrl+ Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_ROLE);
		Role[] response= restTemplate.getForObject(url,Role[].class);
		return Arrays.asList(response);

	}

}
