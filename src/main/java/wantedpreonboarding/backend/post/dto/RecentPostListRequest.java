package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecentPostListRequest {

    List<RecentPostsDto> recentList;
}
