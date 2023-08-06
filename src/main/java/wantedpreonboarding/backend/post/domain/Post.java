package wantedpreonboarding.backend.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import wantedpreonboarding.backend.post.dto.UpdatePostRequest;
import wantedpreonboarding.backend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
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
    public Post(Long id, User user, String title,
                String content, Long view_count, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
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
                .view_count(0L)
                .build();
    }

    // 게시글 수정
    public Post updatePost(UpdatePostRequest updatePostRequest) {
        return Post.builder()
                .id(this.id)
                .user(this.user)
                .title(updatePostRequest.getTitle() != null ? updatePostRequest.getTitle() : this.title)
                .content(updatePostRequest.getContent() != null ? updatePostRequest.getContent() : this.content)
                .createdAt(this.createdAt)
                .view_count(this.view_count)
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public void addPostCount() {
        this.view_count++;
    }


}
