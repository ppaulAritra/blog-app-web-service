package com.blog.adminpanel.domain.dto;

import java.util.Date;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 11:54 PM
 * @project adminpanel
 */

public class CommentDTO {
    private Long commentId;
    private String commentBody;
    private Long createdById;
    private Long blogId;
    private Date commentDate;
    private Boolean isVoted;
    private Boolean isDeleted;
    private Boolean voteType;
    private Integer totalVotes;
    private BloggerDTO createdBy;
    private BloggerDTO deletedBy;
    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public BloggerDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BloggerDTO createdBy) {
        this.createdBy = createdBy;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Boolean getVoted() {
        return isVoted;
    }

    public void setVoted(Boolean voted) {
        isVoted = voted;
    }

    public Boolean getVoteType() {
        return voteType;
    }

    public void setVoteType(Boolean voteType) {
        this.voteType = voteType;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public BloggerDTO getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(BloggerDTO deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentId=" + commentId +
                ", commentBody='" + commentBody + '\'' +
                ", createdById=" + createdById +
                ", blogId=" + blogId +
                ", commentDate=" + commentDate +
                ", isVoted=" + isVoted +
                ", voteType=" + voteType +
                ", totalVotes=" + totalVotes +
                ", createdBy=" + createdBy +
                ", deletedBy=" + deletedBy +
                '}';
    }
}
