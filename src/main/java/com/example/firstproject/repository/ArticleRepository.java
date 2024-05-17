package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

//repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
//    Iterable<Article> findAll();
    ArrayList<Article> findAll(); //Iterable을 arrayList로 수정 ★★★★★
    //＊Article : 관리 대상 엔티티의 클래스 타입이다. 여기서는 Article이다.
    //＊Long   : 관리 대상 엔티티의 대푯값 타입이다. Article.java 파일에 가보면 id를 대푯값으로 지정했으며, id의 타입은 Lond이므로 Long을 입력한다.
    //비워두기
}
