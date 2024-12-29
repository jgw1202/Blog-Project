package me.gunwoo.springbootdeveloper.dto;

// DTO : data transfer object : 계층끼리 데이터 교환하기 위해 쓰는 객체

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gunwoo.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(String author) { // 생성자를 사용해 객체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    } // 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도
}
