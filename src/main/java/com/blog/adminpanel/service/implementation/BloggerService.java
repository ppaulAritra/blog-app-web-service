/**
 *
 */
package com.blog.adminpanel.service.implementation;

import java.util.Arrays;
import java.util.List;

import com.blog.adminpanel.component.ApiUrlList;
import com.blog.adminpanel.component.Endpoint;
import com.blog.adminpanel.domain.dto.BloggerDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.blog.adminpanel.service.IBloggerService;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aritra
 *
 */
@Service
public class BloggerService implements IBloggerService {

    @Autowired
    OAuth2RestTemplate restTemplate;
    @Value("${blogapi.resource}")
    private String baseUrl;

    @Override
    public BloggerDTO registerBlogger(BloggerDTO bloggerDTO) {
        RestTemplate rt = new RestTemplate();
        String url = baseUrl + ApiUrlList.registration;
        BloggerDTO response = rt.postForObject(url, bloggerDTO, BloggerDTO.class);
        return response;

    }

    @Override
    public BloggerDTO createBlogger(BloggerDTO bloggerDTO) {

        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_CREATE_BLOGGER);
        BloggerDTO response = restTemplate.postForObject(url, bloggerDTO, BloggerDTO.class);
        return response;
    }

    @Override
    public List<BloggerDTO> getBloggerByRoleAndStatus(Long roleId, String approvalStatus) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_BLOGGER_BY_ROLE);
        BloggerDTO[] bloggers = restTemplate.getForObject(url, BloggerDTO[].class, roleId, approvalStatus);
        return Arrays.asList(bloggers);
    }

    @Override
    public BloggerDTO approveBlogger(Long bloggerId) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_APPROVE_BLOGGER);
        BloggerDTO response = restTemplate.getForObject(url, BloggerDTO.class, bloggerId);
        return response;

    }

    @Override
    public String deleteBlogger(Long bloggerId, Long decisionById) throws Exception {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_DELETE_BLOGGER) + bloggerId + "/" + decisionById;
        restTemplate.delete(url);
        return "Successfully deleted";
    }

    @Override
    public BloggerDTO getBlogger(Long bloggerId) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_BLOGGER);
        BloggerDTO response = restTemplate.getForObject(url, BloggerDTO.class, bloggerId);
        return response;
    }
}
