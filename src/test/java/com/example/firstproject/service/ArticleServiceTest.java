package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 1. 해당 클래스를 스프링부트와 연동해 테스트


class ArticleServiceTest {  //모든 테스트를 실행하는 방법
    //2. 객체 주입
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가","1111");
        Article b = new Article(2L, "나나나나","2222");
        Article c = new Article(3L, "다다다다","3333");
        //a, b, c를 합치기
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
//        List<String> list = new ArrayList<>(fixedSizeListA);


        //2. 실제 데이터
        List<Article> articles = articleService.index();



        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());

        //assertEquals(x, y);
        //예상데이터(x)와 실제 데이터(y)를 비교해 일치하면 테스트 통과




    }

    @Test
    void show_성공_존재하는_id입력() {
        //1 예상 데이터
        Long id = 1L;
        //2 메서드 이름 작성

        //3 예상 데이터 저장
        Article expected = new Article(id, "가가가가","1111");
        //4 실제 데이터 저장
        Article article = articleService.show(id);

        //5 비교 및 검증
        assertEquals(expected.toString(), article.toString());


    }

    @Test
    void show_실패_존재하지_않는_ID입력() {
        //예상 데이터
        Long id = -1L;
        Article expected = null;

        //실제 데이터
        Article article = articleService.show(id);

        //비교 및 검증
        assertEquals(expected, article);

    }

    // title, content만 있는 dto를 입력한 경우
    @Test
    void create_성공_title_content() {
        //예상 데이터
        //임의의 title, content 입력
        String title = "라라라라";
        String content = "4444";

        //dto 생성
        ArticleForm dto = new ArticleForm(null, title, content);

        //예상 데이터 저장
        Article expected = new Article(4L, title, content);

        //실제 데이터
        Article article = articleService.create(dto);

        //비교 및 검증
        //사용자가 임의의 게시물 작성시 테스트
        assertEquals(expected.toString(), article.toString());

    }


    @Test
    void create_실패_id가_포함된_dto() {
        //예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";

        // dto생성
        ArticleForm dto = new ArticleForm(id, title, content);

        //예상 데이터 저장
        Article expected = null;

        //실제 데이터: 실제 생성 결과를 저장함
        Article article = articleService.create(dto);


        //비교 및 검증
        assertEquals(expected, article);

    }
}





