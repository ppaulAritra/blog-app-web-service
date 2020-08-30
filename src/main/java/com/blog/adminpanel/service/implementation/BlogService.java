package com.blog.adminpanel.service.implementation;

import com.blog.adminpanel.component.ApiUrlList;
import com.blog.adminpanel.component.Endpoint;
import com.blog.adminpanel.config.RestResponsePage;
import com.blog.adminpanel.domain.dto.BlogDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import com.blog.adminpanel.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:52 PM
 * @project adminpanel
 */
@Service
public class BlogService implements IBlogService {
    @Autowired
    OAuth2RestTemplate restTemplate;
    @Value("${blogapi.resource}")
    private String baseUrl;
    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public BlogDTO createBlogPost(BlogDTO blog) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_CREATE_BLOG);
        BlogDTO response = restTemplate.postForObject(url, blog, BlogDTO.class);
        return response;
    }

    @Override
    public BlogDTO getBlog(Long postId) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_BLOG);
        BlogDTO response = restTemplate.getForObject(url, BlogDTO.class, postId);
        return response;
    }

    @Override
    public BlogDTO approveBlog(Long blogId, Long bloggerId) {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_APPROVE_BLOG);
        BlogDTO response = restTemplate.getForObject(url, BlogDTO.class, blogId, bloggerId);
        return response;
    }

    @Override
    public String deleteBlog(Long blogId, Long bloggerId) throws Exception {
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_DELETE_BLOG) + blogId + "/" + bloggerId;
        restTemplate.delete(url);
        return "Successfully deleted";
    }

    @Override
    public Page<BlogDTO> getApprovedBlogPost(String fromDate, int page) {
        page = page - 1;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<RestResponsePage<BlogDTO>> ptr =
                new ParameterizedTypeReference<RestResponsePage<BlogDTO>>() {
                };
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_ALL_APPROVED_BLOG);
        Page<BlogDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ptr, fromDate, page).getBody();
        return response;
    }

    @Override
    public Page<BlogDTO> getBlogByBlogger(Long bloggerId, int page) {
        page = page - 1;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<RestResponsePage<BlogDTO>> ptr =
                new ParameterizedTypeReference<RestResponsePage<BlogDTO>>() {
                };
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_MY_BLOG_POSTS);
        Page<BlogDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ptr, bloggerId, page).getBody();
        return response;
    }

    @Override
    public Page<BlogDTO> getAllBlogByDate(String fromDate, String type, int page) {
        page = page - 1;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<RestResponsePage<BlogDTO>> ptr =
                new ParameterizedTypeReference<RestResponsePage<BlogDTO>>() {
                };
        String url = baseUrl + Endpoint.fetchEndpoint(ApiUrlList.KEY_GET_ALL_BLOG_POST);
        Page<BlogDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ptr, fromDate, type, page).getBody();
        return response;
    }
}
