package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;
    @RequestMapping(value = "/board/list")
    public String boardList(Model model) {
        List<BoardVO> list = boardService.getBoardList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping (value = "/board/add", method = RequestMethod.GET)
    public String addPost() {

        return "addpostform";
    }

    @RequestMapping(value = "/board/addok", method = RequestMethod.POST)
    public String addPostOK(BoardVO vo) {

        if (boardService.insertBoard(vo) == 0)
            System.out.println("데이터 추가 실패");

        else
            System.out.println("데이터 추가 성공!");

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/board/edit", method = RequestMethod.GET)
    public String editBoard(@RequestParam("id") int id, Model model) {
        BoardVO board = boardService.getBoard(id);
        if (board == null) {

            return "redirect:/board/list";
        }
        model.addAttribute("board", board);
        return "editform";
    }

    @RequestMapping(value = "/board/editok", method = RequestMethod.POST)
    public String editPostOK(BoardVO vo) {
        if (boardService.updateBoard(vo) == 0) {
            System.out.println("데이터 수정 실패");
        } else {
            System.out.println("데이터 수정 성공!");
        }
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/deleteok/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") int id) {
        if (boardService.deleteBoard(id) == 0) {
            System.out.println("데이터 삭제 실패");
        } else {
            System.out.println("데이터 삭제 성공!");
        }
        return "redirect:/board/list";
    }



}