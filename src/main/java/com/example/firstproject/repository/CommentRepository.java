package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    // value = "실행하고자 하는 쿼리문 작성"
    @Query(value = "SELECT * FROM comment WHERE article_id= :articleId",
            nativeQuery = true)
    //1. 특정 게시글의 모든 댓글 조회
    List<Comment> findByArticleID(Long articleId);



    //2. 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);



}
