package org.boot.blog.controller;

import org.boot.blog.model.BoardModel;
import org.boot.blog.service.BoardService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name="boardService")
    private BoardService boardService;

    @PostMapping("/boardInfo.do")
    public void boardInfo(HttpServletRequest request) throws Exception {

        BoardModel boardModel = new BoardModel();
        BoardModel boardInfo = boardService.boardInfo(boardModel);
    }

    @PostMapping("/boardList.do")
    public void boardList(HttpServletRequest request) throws Exception {

        List<BoardModel> boardList = boardService.boardList();
    }
}
