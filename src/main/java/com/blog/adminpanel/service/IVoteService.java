package com.blog.adminpanel.service;

import com.blog.adminpanel.domain.dto.VoteDTO;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:52 PM
 * @project adminpanel
 */

public interface IVoteService {
    VoteDTO saveVote(VoteDTO voteDTO) throws Exception;
}
