package com.example.firstproject.service;


import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//1. 서비스로 선언
@Service

public class CommentService {

    //2. 댓글 리파지토리
    @Autowired
    private CommentRepository commentRepository;

    //3. 게시글 리파지토리 객체 주입
    @Autowired
    private ArticleRepository articleRepository;



    //comments() 메서드
    public List<CommentDto> comments(Long articleId) {
/*

        //1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleID(articleId);

        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        //for문
        //조회한 댓글  엔티티 수만큼 반복하기
        for(int i=0; i< comments.size();i++ ){
            //조회한 댓글 엔티티 하나씩 가져오기
            Comment c = comments.get(i);
            //엔티티를 dto로 변환
            CommentDto dto = CommentDto.createCommentDto(c);
            //변환한 dto를 dtos 리스트에 저장
            dtos.add(dto);

        }

 */

        //3. 결과 반환 - 댓글 엔티티 목록 바로 가져오기

        return commentRepository.findByArticleID(articleId)
                .stream()
                //엔티티를 DTO로 변환
                .map(comment -> CommentDto.createCommentDto(comment))
                //스트림을 리스트로 변경
                .collect(Collectors.toList());


    }



    @Transactional
    public CommentDto create (Long articleId, CommentDto dto){
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                        "대상 게시글이 없습니다."));

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO로 변환하여 반환
        return CommentDto.createCommentDto(created);


    }

    // 댓글 수정 메서드
    @Transactional      //롤백을 위핸 어노테이션
    public CommentDto update(Long id, CommentDto dto){
        //1. DB에 해당 댓글 조회하고, 가져오고, 없을 경우 예외 발생하기
        Comment target = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글 수정 실패 " + "대상 게시글이 없습니다."  )
        );


        //2. 가져온 댓글 내용 수정하기
        //예외가 발생하지 않았다면 target에 정보를 넣음
        target.patch(dto);


        //3. 수정한 댓글을 DB에 갱신하기(수정 데이터로 덮어 쓰기)
        Comment updated = commentRepository.save(target);


        //4. DB에 반영된 엔티티를 DTO로 변환해, 컨트롤러 반환하기
        return CommentDto.createCommentDto(updated);



    }

    // 댓글 삭제 메서드
    @Transactional      //롤백을 위핸 어노테이션
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        //삭제할 댓글 가져오기
        //id를 비교하여 없을시 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패 - 대상 댓글이 없습니다.")
        );

        // 댓글 삭제
        commentRepository.delete(target);

        //삭제 댓글 DTO변환 및 반환
        return CommentDto.createCommentDto(target);
    }

}
















