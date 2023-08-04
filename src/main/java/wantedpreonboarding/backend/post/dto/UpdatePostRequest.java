package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UpdatePostRequest {

    private Long postId;

    @NotBlank(message = "수정할 게시글을 입력하세요.")
    private String title;

    @NotBlank(message = "수정할 내용을 입력하세요.")
    private String content;
}
