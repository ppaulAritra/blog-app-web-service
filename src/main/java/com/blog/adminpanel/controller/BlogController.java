package com.blog.adminpanel.controller;

import com.blog.adminpanel.component.CommonMethod;
import com.blog.adminpanel.component.PagerModel;
import com.blog.adminpanel.domain.dto.BlogDTO;
import com.blog.adminpanel.domain.dto.BloggerDTO;
import com.blog.adminpanel.domain.dto.CommentDTO;
import com.blog.adminpanel.domain.dto.VoteDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import com.blog.adminpanel.service.implementation.BlogService;
import com.blog.adminpanel.service.implementation.CommentService;
import com.blog.adminpanel.service.implementation.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/29/20 at 12:39 PM
 * @project adminpanel
 */
@RestController
@RequestMapping("/blog/post")
public class BlogController {
    private static final int BUTTONS_TO_SHOW = 9;


    private BlogService blogService;
    private SettingsRepository settingsRepository;
    private CommentService commentService;
    private VoteService voteService;
    @Autowired
    public BlogController(BlogService blogService, SettingsRepository settingsRepository, CommentService commentService, VoteService voteService) {
        this.blogService = blogService;
        this.settingsRepository = settingsRepository;
        this.commentService = commentService;
        this.voteService = voteService;
    }

    @GetMapping("/create")
    public ModelAndView createPost() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        BlogDTO blog = new BlogDTO();
        blog.setUserId(settingsRepository.getUserId());
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView createPost(BlogDTO blogDTO) {
        blogService.createBlogPost(blogDTO);
        String redirectUrl = "/blog/post/myself";
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @GetMapping("")
    public ModelAndView getAllBlogPost() {
        ModelAndView modelAndView = new ModelAndView("blog/all");
        String defaultDate = CommonMethod.getCurrentDate();
        String type = "notapproved";
        int page = 1;
        Page<BlogDTO> blogs = blogService.getAllBlogByDate(defaultDate, type, page);
        System.out.println(blogs.getContent());
        PagerModel pager = new PagerModel(blogs.getTotalPages(), blogs.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

    @GetMapping("/by/date")
    public ModelAndView getAllBlogPostByDate(@RequestParam Date fromDate, @RequestParam String type,
                                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("blog/all::blog-list");

        Page<BlogDTO> blogs = blogService.getAllBlogByDate(CommonMethod.convertDate(fromDate), type, page);
        System.out.println(blogs.getContent());
        modelAndView.addObject("blogs", blogs);

        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteBlog(@RequestParam Long blogId) throws Exception {
        return blogService.deleteBlog(blogId, settingsRepository.getUserId());
    }

    @PostMapping("/approve")
    public BlogDTO approveBlog(@RequestParam Long blogId) {
        return blogService.approveBlog(blogId, settingsRepository.getUserId());
    }

    @GetMapping("/approved")
    public ModelAndView getAllApprovedBlogPost() {
        ModelAndView modelAndView = new ModelAndView("blog/approved");
        String defaultDate = CommonMethod.getCurrentDate();
        int page = 1;
        Page<BlogDTO> blogs = blogService.getApprovedBlogPost(defaultDate, page);
        System.out.println(blogs.getContent());
        PagerModel pager = new PagerModel(blogs.getTotalPages(), blogs.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

    @GetMapping("/approved/date")
    public ModelAndView getAllApprovedBlogPost(@RequestParam Date fromDate,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("blog/approved::blog-list");

        Page<BlogDTO> blogs = blogService.getApprovedBlogPost(CommonMethod.convertDate(fromDate), page);
        System.out.println(blogs.getContent());
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/myself")
    public ModelAndView getMyPostedBlogs() {
        ModelAndView modelAndView = new ModelAndView("blog/mypost");
        int page = 1;
        Page<BlogDTO> blogs = blogService.getBlogByBlogger(settingsRepository.getUserId(), page);
        System.out.println(blogs.getContent());
        PagerModel pager = new PagerModel(blogs.getTotalPages(), blogs.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

    @GetMapping("/myself/by/page")
    public ModelAndView getMyPostedBlogsByPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView("blog/mypost::blog-list");

        Page<BlogDTO> blogs = blogService.getBlogByBlogger(settingsRepository.getUserId(), page);
        System.out.println(blogs.getContent());
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/details/{blogId}")
    public ModelAndView getBlogDetails(@PathVariable("blogId") Long blogId) {
        ModelAndView modelAndView = new ModelAndView("blog/details");
        BlogDTO blog = blogService.getBlog(blogId);
        CommentDTO comment = new CommentDTO();
        comment.setCreatedById(settingsRepository.getUserId());
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("comment", comment);
        modelAndView.addObject("comments", blog.getComments());

        return modelAndView;
    }

    @PostMapping("/comment")
    public ModelAndView createComment(CommentDTO commentDTO) {
        System.out.println(commentDTO);
        ModelAndView modelAndView = new ModelAndView("blog/details::blog-comment");
        commentService.createComment(commentDTO);
        List<CommentDTO> comments = commentService.getCommentsByBlog(commentDTO.getBlogId());
        BlogDTO blog = new BlogDTO();
        blog.setComments(comments);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/comment/vote")
    public ModelAndView voteComment(@RequestParam Long commentId, @RequestParam Boolean vote, @RequestParam Long blogId) throws Exception {

        ModelAndView modelAndView = new ModelAndView("blog/details::blog-comment");
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setBloggerId(settingsRepository.getUserId());
        voteDTO.setCommentId(commentId);
        voteDTO.setVoteStatus(vote);
        voteService.saveVote(voteDTO);

        List<CommentDTO> comments = commentService.getCommentsByBlog(blogId);
        BlogDTO blog = new BlogDTO();
        blog.setComments(comments);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/vote")
    public ModelAndView voteBlog(@RequestParam Long blogId, @RequestParam Boolean vote) throws Exception {

        ModelAndView modelAndView = new ModelAndView("blog/details::blog-vote");
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setBloggerId(settingsRepository.getUserId());
        voteDTO.setBlogId(blogId);
        voteDTO.setVoteStatus(vote);
        voteService.saveVote(voteDTO);
        BlogDTO blog = blogService.getBlog(blogId);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
}

