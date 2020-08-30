package com.blog.adminpanel.service.implementation;

import com.blog.adminpanel.component.ApiUrlList;
import com.blog.adminpanel.component.Endpoint;
import com.blog.adminpanel.domain.dto.VoteDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import com.blog.adminpanel.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:52 PM
 * @project adminpanel
 */
@Service
public class VoteService implements IVoteService {
    @Autowired
    OAuth2RestTemplate restTemplate;
    @Value("${blogapi.resource}")
    private String baseUrl;
  

    @Override
    public VoteDTO saveVote(VoteDTO voteDTO) throws Exception{
        String url=baseUrl+ Endpoint.fetchEndpoint(ApiUrlList.KEY_SAVE_VOTE);
        VoteDTO response= restTemplate.postForObject(url, voteDTO, VoteDTO.class);
        return response;
    }
}
