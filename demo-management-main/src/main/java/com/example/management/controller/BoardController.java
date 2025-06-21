package com.example.management.controller;

import com.example.management.domain.Board;
import com.example.management.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService service) {
        this.boardService = service;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("list")
    public String boardList(Model model) {
        PageRequest pageRequest = null;
        // List<Board> list = boardService.getBoardList(null);
        List<Board> list = boardService.getBoardList();
        model.addAttribute("list", list);
        return "boardList";
    }

    final int pageSize = 10;

    //paging type 1
    public String boardList1(Model model, @RequestParam(required = false, defaultValue = "0") int pageNo) {
     if(pageNo < 0) pageNo = 0;
        PageRequest pageRequest  = PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.DESC,"id"));
        List<Board> list = boardService.getBoardList();
        model.addAttribute("list",list);
        return "boardList";
    }

    //paging type 2
    public String boardList2(Model model, @RequestParam(required = false,defaultValue ="0") int pageNo){
        if(pageNo < 0) pageNo = 0;
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Slice<Board> list = boardService.getBoardList2(pageRequest);
        model.addAttribute("list",list);
        return "boardList2";
    }

    @GetMapping("/add/form")
    public String addForm(){
        return "boardAdd";
    }

    @PostMapping("/add/form")
    public String addForm(@Valid BoardForm form){
        Board newBoard = new Board();
        newBoard.setCreatedBy(form.getCreatedBy());
        newBoard.setTitle(form.getTitle());
        newBoard.setContent(form.getContent());
        boardService.saveBoard(newBoard);
        return "redirect:/list";
    }

    @GetMapping("/bulk")
    public String bulk(){
        boardService.bulkBoard();
        return "redirect:/list";
    }
}
