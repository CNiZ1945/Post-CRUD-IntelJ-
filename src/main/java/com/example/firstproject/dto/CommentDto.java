package com.example.firstproject.dto;


import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

// 댓글 엔티티를 답는 그릇
public class CommentDto {
    //필드 선언
    private Long id;            //댓글의 id


    //데이터 요청시 ,JSON 데이터의 속성을 필드에 작성된 내용에 따라 정상적으로 작동하도록 도와줌
    @JsonProperty("articleId")
    private Long articleId;     //댓글의 부모

    private String nickname;    //댓글 작성자

    private String body;        //댓글 본문


    public static CommentDto createCommentDto(Comment comment) {
        //static: 정적 메서드 -> createCommentDto()가 객체 생성없이 호출 가능

        //메서드 반환값이 댓글 dto가 되도록 commentDto 호출
        return new CommentDto(
                //댓글 엔티티의 id
                comment.getId(),
                //댓글 엔티티가 속한 부모 게시글
                comment.getArticle().getId(),
                //댓글 엔티티의 nickname
                comment.getNickname(),
                //댓글 엔티티 body
                comment.getBody()


        );

    }
}














