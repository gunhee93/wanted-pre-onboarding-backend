package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostResponse {

    private String title;
    private String content;
}
