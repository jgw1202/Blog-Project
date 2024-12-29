package me.gunwoo.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티가 생성이 될 때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정이 될때 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content= content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/*
빌더 패턴 :
// 빌더 패턴 사용 안할 때
new Article("abc", "def");

// 빌더 패턴을 사용 할 때
Aticle.builder()
    .title("abc") // 뭐가 뭔지 명시적이게 됨
    .content("def")
    .build();
 */
