package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j //로그찍기
@Service //서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    //index()메서드
    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //게시그 ㄹ수정 요청 개선하기
    //update 메서드
    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 타겟 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            // 400번, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null; //응답은 컨트롤러가 하므로, 여기서는 NULL 값 반환
        }

        // 4. 업데이트하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated; //응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    //create
    public Article create(ArticleForm dto) {
        //DTO -> entity 변환 후, article에 저장
        Article article = dto.toEntity();

        //ID는 데이터를 생성할 때 굳이 넣을 필요 없음 -> DB가 알아서 생성하기 때문
        //따라서, article 객체가 id가 존재한다면, null값으로 반환하는 코드 추가함
        if (article.getId() != null) {
            return null;
        }

        //article을 DB에 저장함.
        return articleRepository.save(article);


    }

    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            return null; //응답은 컨트롤러가 하므로 여기서는 null만 반환

        }

        articleRepository.delete(target);
        return target;  // DB에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        //1. dto묶음(리스트)을 엔ㄴ티티 묶음(리스트)로 변환하기
        //DTO 묶음인 DTOS를 만들기 위해서 , 스트림 문법 활용
        //1-1 dtos를 스트림화
        //1-4 최종 결과는 articleList에 저장한다.
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity()) //1-2
                .collect(Collectors.toList());  //1-3
        //1-2 map()으로 dto가 하나하나 올때마다 dto.toEntity()를 수행하여 맵핑
        //1-3 이렇게 맵핑한 것을 리스트로 묶는다

        //2.엔티티 묶음
        articleList.stream()    //articleList를 스트림화한다.
                .forEach(article ->articleRepository.save(article));

        //3. 강제로 에러 발생시키기
        articleRepository.findById(-1L) //id: -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));  //데이터 없을시 예외 발생

        //4. 결과 값 반환하기
        return articleList;

    /*
    public List<Article> createArticles(List<ArticleForm> dtos){
        List<Article> articleList = new ArrayList<>();
        for(int i = 0; i<dtos.size(): i++){

        }
        //2.엔티티 묶음(리스트)을 DB로 저장하기
        for(int i=0; i<dtos.size(); i++){
            Article article = articleList.get(i);
            articleRepository.save(article);
        }

    * */






    }
}













