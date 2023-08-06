package wantedpreonboarding.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wantedpreonboarding.backend.post.domain.Post;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {


    @NotBlank(message = "수정할 게시글을 입력하세요.")
    private String title;

    @NotBlank(message = "수정할 내용을 입력하세요.")
    private String content;


}
