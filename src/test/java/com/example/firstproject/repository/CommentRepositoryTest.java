package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest        //해당 클래스를 JPA와 연동해서 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;        //commentRepository 객체 주입

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")      //메서드 이름은 그대로 둔 채 테스트 이름을 변경
    void findByArticleID() {
//        Case 1: 4번의 게시글의 모든 댓글 조회
        {
        //입력 데이터
            Long articleId = 4L;

        //실제 데이터
            List<Comment> comments = commentRepository.findByArticleID(articleId);


        //예상 데이터
        //부모 게시글 객체 생성
            Article article = new Article(4L, "당신의 인생영화는?", "댓글 go");

        //댓글 객체 생성
            Comment a = new Comment(1L, article, "google", "굿윌 헌팅");
        // 댓글 객체 생성
            Comment b = new Comment(2L, article, "naver", "듄");
        //댓글 객체 생성
            Comment c = new Comment(3L, article, "daum", "아이언맨이 짱임");

        // 댓글 객체 합치기
            List<Comment> expected = Arrays.asList(a,b,c);

            //비교 및 검증
            assertEquals(expected.toString(), comments.toString());


        }




    }


    @Test
    void findByNickname() {




    }
}