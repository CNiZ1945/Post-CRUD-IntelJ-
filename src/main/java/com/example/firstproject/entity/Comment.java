package com.example.firstproject.entity;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity     //해당 클래스 엔티티 선언, 클래스 필드 바탕으로 DB에 테이블 생성
@Getter     //각 필드값 조회(자동 생성)
@ToString   //모든 필드 출력
@AllArgsConstructor     //모든 필드 매개변수 갖는 생성자(자동 생성)
@NoArgsConstructor      //매개변수가 아예 없는 기본 생성자(자동 생성)


//댓글 엔티티
public class Comment {
    //대표키(id)
    @Id// 대표키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //DB에서 자동으로 1씩 증가
    private Long id;


    //1:n 관계 어노테이선
    @ManyToOne      //comment - article 관계
    //외래키 설정
    @JoinColumn(name = "article_id")    //article 엔티티의 기본키(id)와 매핑
    //해당 댓글의 부모 게시글(article)
    private Article article;

    @Column     //해당 필드를 테이블의 속성으로 매핑
    //댓글을 단 사람(nickname)
    private String nickname;

    @Column     //해당 필드를 테이블의 속성으로 매핑
    //댓글 본문(body)
    private String body;

    //Comment - createComment() 메서드
    public static Comment createComment(CommentDto dto, Article article){
        //예외 발생 1
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다.");
        }
        //예외 발생 2
        if(dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 아닙니다.");
        }
        
        //엔티티 생성 및 반환
        return new Comment(
                //댓글 아이디
                dto.getId(),
                //부모 게시글
                article,
                //댓글 닉네임
                dto.getNickname(),
                //댓글 본문
                dto.getBody()

        );

    }

    // 댓글 수정
    public void patch(CommentDto dto){
        //1. 예외 발생
        if(this.id != dto.getId()){
            throw new IllegalArgumentException(
                "댓글 수정 실패 잘못된 ID가 입력됨"   );
        }


        //2. 객체 갱신
        if(dto.getNickname() != null){
            //수정할 닉네임이 있을 시
            this.nickname = dto.getNickname();
            //받은 닉네임으로 수정
        }
        if(dto.getBody() != null){
            //수정할 본문(body)이 있을 시
            this.body = dto.getBody();
            //받은 닉네임으로 수정
        }

    }







}
