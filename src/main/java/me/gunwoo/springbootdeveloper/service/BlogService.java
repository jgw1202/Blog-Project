package me.gunwoo.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.gunwoo.springbootdeveloper.domain.Article;
import me.gunwoo.springbootdeveloper.dto.AddArticleRequest;
import me.gunwoo.springbootdeveloper.dto.UpdateArticleRequest;
import me.gunwoo.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final , @notNull이 붙은 필드의 생성자 추가
@Service //  빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글을 추가하는 메소드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글을 전체 조회하는 메소드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 1개만을 조회하는 메소드
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
