package com.blog.adminpanel.service.implementation;

import com.blog.adminpanel.component.ApiUrlList;
import com.blog.adminpanel.component.Endpoint;
import com.blog.adminpanel.domain.dto.CommentDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import com.blog.adminpanel.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:52 PM
 * @project adminpanel
 */
@Service
public class CommentService implements ICommentService {
    @Autowired
    OAuth2RestTemplate restTemplate;
    @Value("${blogapi.resource}")
    private String baseUrl;
    @Autowired
    private SettingsRepository settingsRepository;
    @Override
    public CommentDTO createComment(CommentDTO comment) {
        String url=baseUrl+ Endpoint.fetchEndpoint(ApiUrlList.KEY_CREATE_COMMENT);
        CommentDTO response= restTemplate.postForObject(url, comment, CommentDTO.class);
        return response;
    }

    @Override
    public String deleteComment(Long commentId, Long bloggerId) {
        String url=baseUrl+Endpoint.fetchEndpoint( ApiUrlList.KEY_CREATE_COMMENT)+commentId+"/"+bloggerId;
        restTemplate.delete(url);
        return "Successfully deleted";
    }

    @Override
    public List<CommentDTO> getCommentsByBlog(Long blogId) {
        String url=baseUrl+Endpoint.fetchEndpoint( ApiUrlList.KEY_GET_COMMENTS_BY_BLOG);
        CommentDTO[] response= restTemplate.getForObject(url, CommentDTO[].class,blogId);
        return Arrays.asList(response);
    }
}
