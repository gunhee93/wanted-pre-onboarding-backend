package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class LookUpPostResponse {

    private String nickname;
    private String title;
    private String content;
    private String createAt;


}
