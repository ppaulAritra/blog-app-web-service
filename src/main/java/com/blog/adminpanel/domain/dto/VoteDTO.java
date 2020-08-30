package com.blog.adminpanel.domain.dto;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 11:54 PM
 * @project adminpanel
 */

public class VoteDTO {
    private Long id;
    private Boolean voteStatus;
    private Long commentId;
    private Long blogId;
    private Long bloggerId;

    public Boolean getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Boolean voteStatus) {
        this.voteStatus = voteStatus;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Long bloggerId) {
        this.bloggerId = bloggerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "id=" + id +
                ", voteStatus=" + voteStatus +
                ", commentId=" + commentId +
                ", blogId=" + blogId +
                ", bloggerId=" + bloggerId +
                '}';
    }
}
