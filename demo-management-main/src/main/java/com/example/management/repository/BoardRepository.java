package com.example.management.repository;

import com.example.management.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    //Change from Page to Slice
    Slice<Board> findAllByCreatedBy(String writer, PageRequest pageRequest);
}
