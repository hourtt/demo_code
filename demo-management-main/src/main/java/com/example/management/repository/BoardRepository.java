package com.example.management.repository;

import com.example.management.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

//*Pushing the code (Testing)
public interface BoardRepository extends JpaRepository<Board,Long> {
    //Change from Page to Slice
    Slice<Board> findAllByCreatedBy(String writer, PageRequest pageRequest);
}


//* To Merge the code*//

// 1. Create the new branch (In other word, second branch)
// 2. Commit or push the file into that second branch
// 3. check on the Github (It'll show and tell you to compare & pull request)
//* 4. First, pull request and then merge the code into the first branch (Master in my case)
//* 5. Wait until it finished to pull request and merged
