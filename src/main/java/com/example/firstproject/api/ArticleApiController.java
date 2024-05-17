package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //로그 찍을 수 있게 어노테이션 추가
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService; //게시글 리파지터리 객체 주입

    //GET-게시글 조회요청 개선 하기
    //모든 게시글 조회 요청 개선하기
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }
    //GET - 게시글 조회요청 개선 하기
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created)
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        //1.서비스를 통해 게시글 수정
        Article updated = articleService.update(id,dto);
        // 2.수정되면 정상, 안되면 오류 응답
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated)
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
        //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete (@PathVariable Long id) {
        //1. 서비스를 통해 게시글 삭제
        Article deleted = articleService.delete(id);

        //2. 삭제 결과에 따라 응답 처리
        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("api/transaction-test")  //여러 게시글 수정 요청 접수
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        //transactionTest() 메서드 정의
        //1. 서비스 호출
        List<Article> createdList = articleService.createArticles(dtos);

        //2. 생성 결과에 따라 응답 처리
        return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList)
                                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }




//    @Autowired
//    private ArticleRepository articleRepository;
//
//    //GET - 모든 게시글
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    //GET - 단일
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    //PATCH
//    //■컨트롤러의 역할 : 클라이언트 요청받기-------------------------------------------------------------------
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update (@PathVariable Long id, @RequestBody ArticleForm dto) {
//        //■서비스의 역할 -------------------------------------------------------------
//        //1. DTO -> entity로 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}" ,id, article.toString());
//
//        //2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //3. 잘못된 요청 처리하기
//            //3-1. 조건문
//            //3-2. 로그
//            //3-3. 400 오류 반환 - 상태코드 실어보냄
//            //3-4. null 실어 반환
//        if (target == null || id != article.getId()) {
//            //400. 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}" ,id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //4. 업데이트 및 정상 응답(200)하기
//        target.patch(article); //기존 데이터에 새 데이터 붙이기
//            //4-1. Article entity DB에 저장
//        Article updated = articleRepository.save(article);
//            //4-2. 정상 응답
//        //■컨트롤러의 역할 : 클라이언트에게 응답하기-------------------------------------------------------------------
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete (@PathVariable Long id) {
//        //1.DB에서 대상 entity가 있는지 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //2.대상 entity가 없어서 요청 자체가 잘못된 경우 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //3.대상 entity가 있으면 삭제, 정상 응답(200)반환하기
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK)
////                .body(target); 대신에 build()사용 가능
//                .build();
//    }
}
