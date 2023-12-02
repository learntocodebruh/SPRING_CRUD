package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/board")

public class BoardController {

    @Autowired
    BoardServiceImpl boardServiceImpl;
    @RequestMapping("/list")
    public String boardList(Model model) {
        List<BoardVO> list = boardServiceImpl.getBoardList();
        model.addAttribute("list", list);
        return "boardList";
    }

    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String addPost() {

        return "addpostform";
    }

    @RequestMapping(value = "/addok", method = RequestMethod.POST)
    public String addPostOK(BoardVO vo) {

        if (boardServiceImpl.insertBoard(vo) == 0)
            System.out.println("데이터 추가 실패");

        else
            System.out.println("데이터 추가 성공!");

        return "redirect:boardList";
    }

}