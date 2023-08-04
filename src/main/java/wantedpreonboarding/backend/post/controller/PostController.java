package wantedpreonboarding.backend.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wantedpreonboarding.backend.common.code.SuccessCode;
import wantedpreonboarding.backend.common.response.ApiResponse;
import wantedpreonboarding.backend.post.dto.*;
import wantedpreonboarding.backend.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestHeader(value = "Authorization") String acTokenRequest,
                                     @Validated @RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(createPostRequest, acTokenRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity postList(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        RecentPostListRequest recentPostListRequest = postService.postList(pageable);

        return new ResponseEntity(recentPostListRequest, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity lookUpPost(@PathVariable("postId") Long postId) {
        LookUpPostResponse lookUpPostResponse = postService.findPost(postId);

        return new ResponseEntity(lookUpPostResponse, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity updatePost(@Validated @RequestBody UpdatePostRequest updatePostRequest) {
        UpdatePostResponse updatePostResponse = postService.updatePost(updatePostRequest);

        return new ResponseEntity(updatePostResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);

        return new ResponseEntity(new ApiResponse(SuccessCode.DELETE_POST), HttpStatus.OK);
    }
}
