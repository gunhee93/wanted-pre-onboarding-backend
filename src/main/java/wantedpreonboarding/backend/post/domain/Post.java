package wantedpreonboarding.backend.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import wantedpreonboarding.backend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POSTS")
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String content;
    private Long view_count;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Post(User user, String title,
                String content, Long view_count, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.view_count = view_count;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Post of(User user, String title,
                          String content) {
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

    // 게시글 수정
    public static Post updatePost(String title, String content) {
        return Post.builder()
                .title(title)
                .content(title)
                .build();
    }

    public void addPostCount() {
        this.view_count++;
    }


}
