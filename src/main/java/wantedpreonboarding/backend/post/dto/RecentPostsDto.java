package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecentPostsDto {

    private Long postId;
    private String title;
    private String nickName;
    private String writeTime;
}
