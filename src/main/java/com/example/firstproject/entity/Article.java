package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //모든 필드 매개변수 갖는 생성자(자동 생성)
@NoArgsConstructor  //매개변수가 아예 없는 기본 생성자(자동 생성)
@ToString           //모든 필드 출력
@Entity             //해당 클래스 엔티티 선언, 클래스 필드 바탕으로 DB에 테이블 생성
@Getter             //각 필드값 조회(자동 생성)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 ID 자동생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }
}
