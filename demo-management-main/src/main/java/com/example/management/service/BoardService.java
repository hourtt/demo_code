package com.example.management.service;

import com.example.management.domain.Board;
import com.example.management.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  // missing this line
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository repository) {
        this.boardRepository = repository;
    }

    //list
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    //Paging type 1
    public Page<Board> getBoardList1(PageRequest pageRequest) {
        return boardRepository.findAll(pageRequest);
    }

    //Paging type 2
    public Slice<Board> getBoardList2(PageRequest pageRequest) {
        // return boardRepository.findAll(pageRequest);
        //Replace from findAll to findAllByCreateBy
        return boardRepository.findAllByCreatedBy("Writer", pageRequest);
    }

    //get board

    //add process
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void bulkBoard() {
        try {
            List<Board> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Board board = new Board();
                board.setCreatedBy("Writer");
                board.setTitle("bulk title - " + 1);
                board.setContent("bulk content - " + 1);
                list.add(board);
            }
            boardRepository.saveAll(list);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
