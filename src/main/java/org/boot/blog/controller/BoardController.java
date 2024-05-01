package org.boot.blog.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.boot.blog.model.BoardModel;
import org.boot.blog.service.BoardService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name="boardService")
    private BoardService boardService;

    @GetMapping("/boardInfo.do")
    public ModelAndView boardInfo(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            BoardModel boardModel = new BoardModel();
            boardModel.setBoardUuid(request.getParameter("uuid"));
            BoardModel boardInfo = boardService.boardInfo(boardModel);

            /** Gson - 조회한 데이터 확인을 위해 사용
             * Gson dataGson = new Gson();
             * String dataJson = dataGson.toJson(boardInfo);
             * System.out.println(dataJson);
             */

            modelAndView.setViewName("board/board_info");
            modelAndView.addObject("writeId", boardInfo.getWriteId());
            modelAndView.addObject("boardTitle", boardInfo.getBoardTitle());
            modelAndView.addObject("boardContent", boardInfo.getBoardContent());
            modelAndView.addObject("registryDate", boardInfo.getRegistryDate());
        }

        return modelAndView;
    }

    @GetMapping("/boardList.do")
    public ModelAndView boardList(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        BoardModel boardModel = new BoardModel();

        int totalRow = boardService.boardCount(boardModel); // 해당 테이블의 전체 갯수
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
         * String dataJson = dataGson.toJson(boardService.boardList(boardModel, offset, limitRow));
         * System.out.println(dataJson);
         */

        modelAndView.setViewName("board/board_list");
        modelAndView.addObject("boardList", boardService.boardList(boardModel, offset, limitRow));
        modelAndView.addObject("totalRow", totalRow);
        modelAndView.addObject("pageNum", pageNum);

        return modelAndView;
    }

    @RequestMapping(value = "/boardWrite.do")
    public ModelAndView boardWrite()  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/board_write");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/insertBoard")
    public void insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoardModel boardModel = new BoardModel();

        UUID uuid = UUID.randomUUID();
        String boardUuid = uuid.toString();
        boardModel.setBoardUuid(boardUuid);

        if(request.getParameter("writeId") != null && request.getParameter("writeId").isBlank() == false) {
            boardModel.setWriteId(request.getParameter("writeId"));
        }

        if(request.getParameter("boardTitle") != null && request.getParameter("boardTitle").isBlank() == false) {
            boardModel.setBoardTitle(request.getParameter("boardTitle"));
        }

        if(request.getParameter("boardContent") != null && request.getParameter("boardContent").isBlank() == false) {
            boardModel.setBoardContent(request.getParameter("boardContent"));
        }

        int resultNumber = boardService.insertBoard(boardModel);

        if(resultNumber > 0) {
            response.sendRedirect("./boardList.do");
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>alert('해당 글을 등록하는데 실패하였습니다.');</script>");
            out.flush();
        }
    }

    @GetMapping(value = "/boardModify.do")
    public ModelAndView boardModify(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            BoardModel boardModel = new BoardModel();
            boardModel.setBoardUuid(request.getParameter("uuid"));
            BoardModel boardInfo = boardService.boardInfo(boardModel);

            /** Gson - 조회한 데이터 확인을 위해 사용
             * Gson dataGson = new Gson();
             * String dataJson = dataGson.toJson(boardInfo);
             * System.out.println(dataJson);
             */

            modelAndView.setViewName("board/board_modify");
            modelAndView.addObject("boardUuid", boardInfo.getBoardUuid());
            modelAndView.addObject("writeId", boardInfo.getWriteId());
            modelAndView.addObject("boardTitle", boardInfo.getBoardTitle());
            modelAndView.addObject("boardContent", boardInfo.getBoardContent());
            modelAndView.addObject("registryDate", boardInfo.getRegistryDate());
        }

        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/updateBoard")
    public void updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoardModel boardModel = new BoardModel();

        if(request.getParameter("boardUuid") != null && request.getParameter("boardUuid").isBlank() == false) {
            boardModel.setBoardUuid(request.getParameter("boardUuid"));
        }

        if(request.getParameter("writeId") != null && request.getParameter("writeId").isBlank() == false) {
            boardModel.setWriteId(request.getParameter("writeId"));
        }

        if(request.getParameter("boardTitle") != null && request.getParameter("boardTitle").isBlank() == false) {
            boardModel.setBoardTitle(request.getParameter("boardTitle"));
        }

        if(request.getParameter("boardContent") != null && request.getParameter("boardContent").isBlank() == false) {
            boardModel.setBoardContent(request.getParameter("boardContent"));
        }

        int resultNumber = boardService.updateBoard(boardModel);

        if(resultNumber > 0) {
            response.sendRedirect("./boardInfo.do?uuid=" + boardModel.getBoardUuid());
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

    @GetMapping(value = "/deleteBoard")
    public void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getParameter("uuid") != null && request.getParameter("uuid").isBlank() == false) {

            BoardModel boardModel = new BoardModel();

            boardModel.setBoardUuid(request.getParameter("uuid"));

            int resultNumber = boardService.deleteBoard(boardModel);

            if(resultNumber > 0) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script type='text/javascript'>");
                out.println("alert('해당 글이 삭제되었습니다.');");
                out.println("window.location.href='/board/boardList.do';");
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