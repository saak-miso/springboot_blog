package org.boot.blog.controller;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.boot.blog.service.PostService;
import org.boot.blog.model.PostModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.UUID;

@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Resource(name="postService")
    private PostService postService;

    @GetMapping("/postInfo")
    public ModelAndView postInfo(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            PostModel postModel = new PostModel();
            postModel.setPostUuid(request.getParameter("uuid"));
            PostModel postInfo = postService.postInfo(postModel);

            /** Gson - 조회한 데이터 확인을 위해 사용
             * Gson dataGson = new Gson();
             * String dataJson = dataGson.toJson(postInfo);
             * logger.info(dataJson);
             */

            Gson dataGson = new Gson();
            String dataJson = dataGson.toJson(postInfo);
            logger.info(dataJson);

            modelAndView.setViewName("post/post_info");
            modelAndView.addObject("writeId", postInfo.getWriteId());
            modelAndView.addObject("postTitle", postInfo.getPostTitle());
            modelAndView.addObject("postContent", postInfo.getPostContent());
            modelAndView.addObject("registryDate", postInfo.getRegistryDate());
        }

        return modelAndView;
    }

    @GetMapping("/postList")
    public ModelAndView postList(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        PostModel postModel = new PostModel();

        int totalRow = postService.postCount(postModel); // 해당 테이블의 전체 갯수
        int pageNum = 0;    // 선택 페이지
        int offset = 0; // 결과에서 가져올 첫 번째 행의 OFFSET( 0부터 시작 )
        int limitRow = 10;  // 가져올 행의 수를 지정

        if(request.getParameter("page") != null && Integer.parseInt(request.getParameter("page")) > 0) {
            pageNum = Integer.parseInt(request.getParameter("page"));
            offset = (pageNum - 1) * limitRow;
        } else {
            pageNum = 1;
            offset = 0;
        }

        /** Gson 조회한 데이터 확인을 위해 사용
         * Gson dataGson = new Gson();
         * String dataJson = dataGson.toJson(postService.postList(postModel, offset, limitRow));
         * logger.info(dataJson);
         */

        modelAndView.setViewName("post/post_list");
        modelAndView.addObject("postList", postService.postList(postModel, offset, limitRow));
        modelAndView.addObject("totalRow", totalRow);
        modelAndView.addObject("pageNum", pageNum);

        return modelAndView;
    }

    @RequestMapping(value = "/postWrite")
    public ModelAndView postWrite()  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post/post_write");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/insertPost")
    public void insertPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PostModel postModel = new PostModel();

        UUID uuid = UUID.randomUUID();
        String postUuid = uuid.toString();
        postModel.setPostUuid(postUuid);

        if(request.getParameter("writeId") != null && request.getParameter("writeId").isBlank() == false) {
            postModel.setWriteId(request.getParameter("writeId"));
        }

        if(request.getParameter("postTitle") != null && request.getParameter("postTitle").isBlank() == false) {
            postModel.setPostTitle(request.getParameter("postTitle"));
        }

        if(request.getParameter("postContent") != null && request.getParameter("postContent").isBlank() == false) {
            postModel.setPostContent(request.getParameter("postContent"));
        }

        int resultNumber = postService.insertPost(postModel);

        if(resultNumber > 0) {
            response.sendRedirect("./postList");
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>alert('해당 글을 등록하는데 실패하였습니다.');</script>");
            out.flush();
        }
    }

    @GetMapping(value = "/postModify")
    public ModelAndView postModify(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            PostModel postVO = new PostModel();
            postVO.setPostUuid(request.getParameter("uuid"));
            PostModel postInfo = postService.postInfo(postVO);

            /** Gson - 조회한 데이터 확인을 위해 사용
             * Gson dataGson = new Gson();
             * String dataJson = dataGson.toJson(postInfo);
             * logger.info(dataJson);
             */

            modelAndView.setViewName("post/post_modify");
            modelAndView.addObject("postUuid", postInfo.getPostUuid());
            modelAndView.addObject("writeId", postInfo.getWriteId());
            modelAndView.addObject("postTitle", postInfo.getPostTitle());
            modelAndView.addObject("postContent", postInfo.getPostContent());
            modelAndView.addObject("registryDate", postInfo.getRegistryDate());
        }

        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/updatePost")
    public void updatePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PostModel postVO = new PostModel();

        if(request.getParameter("postUuid") != null && request.getParameter("postUuid").isBlank() == false) {
            postVO.setPostUuid(request.getParameter("postUuid"));
        }

        if(request.getParameter("writeId") != null && request.getParameter("writeId").isBlank() == false) {
            postVO.setWriteId(request.getParameter("writeId"));
        }

        if(request.getParameter("postTitle") != null && request.getParameter("postTitle").isBlank() == false) {
            postVO.setPostTitle(request.getParameter("postTitle"));
        }

        if(request.getParameter("postContent") != null && request.getParameter("postContent").isBlank() == false) {
            postVO.setPostContent(request.getParameter("postContent"));
        }


        Gson dataGson = new Gson();
        String dataJson = dataGson.toJson(postVO);
        System.out.println(dataJson);

        int resultNumber = postService.updatePost(postVO);

        if(resultNumber > 0) {
            response.sendRedirect("./postInfo?uuid=" + postVO.getPostUuid());
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("alert('해당 글을 수정하는데 실패하였습니다.');");
            out.println("window.history.back();");
            out.println("</script>");
            out.flush();
        }
    }

    @GetMapping(value = "/deletePost")
    public void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            PostModel postVO = new PostModel();

            postVO.setPostUuid(request.getParameter("uuid"));

            int resultNumber = postService.deletePost(postVO);

            if(resultNumber > 0) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>");
                out.println("alert('해당 글이 삭제되었습니다.');");
                out.println("window.location.href='/post/postList';");
                out.println("</script>");
                out.flush();
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>alert('해당 글을 삭제하는데 실패하였습니다.');</script>");
                out.flush();
            }
        }
    }
}