package wantedpreonboarding.backend.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wantedpreonboarding.backend.common.code.ErrorCode;
import wantedpreonboarding.backend.common.exception.CustomIllegalStateException;
import wantedpreonboarding.backend.jwt.TokenProvider;
import wantedpreonboarding.backend.post.domain.Post;
import wantedpreonboarding.backend.post.dto.*;
import wantedpreonboarding.backend.post.repository.PostRepository;
import wantedpreonboarding.backend.user.domain.User;
import wantedpreonboarding.backend.user.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    // 게시글 찾기
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new CustomIllegalStateException(ErrorCode.NOT_FOUND_POST);
                });
    }

    public void createPost(CreatePostRequest createPostRequest, String acTokenRequest) {
        String accessToken = acTokenRequest.substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String strUserId = authentication.getName();
        Long userId = Long.parseLong(strUserId);

        User writer = userService.findById(userId);
        Post post = Post.of(writer, createPostRequest.getTitle(), createPostRequest.getContent());
        postRepository.save(post);
    }

    public RecentPostListRequest postList(Pageable pageable) {
        Page<Post> findPosts = postRepository.findAllNewest(pageable);
        List<RecentPostsDto> recentPosts = findPosts.stream().map(post -> new RecentPostsDto(
                post.getId(), post.getTitle(), post.getUser().getNickname(),
                post.getCreatedAt().format(DateTimeFormatter.ofPattern("MM dd HH:mm"))
        )).collect(Collectors.toList());

        return new RecentPostListRequest(recentPosts);
    }

    public LookUpPostResponse findPost(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> {throw new CustomIllegalStateException(ErrorCode.NOT_FOUND_POST);
                });

        findPost.addPostCount();

        return new LookUpPostResponse(findPost.getTitle(), findPost.getContent(), findPost.getUser().getNickname(),
                findPost.getCreatedAt().format(DateTimeFormatter.ofPattern("MM dd HH:mm")));
    }

    public UpdatePostResponse updatePost(Long postId, UpdatePostRequest updatePostRequest) {

        Post post = findById(postId);
        Post updatePost = post.updatePost(updatePostRequest);
        postRepository.save(updatePost);

        return new UpdatePostResponse(updatePostRequest.getTitle(), updatePostRequest.getContent());
    }

    public void deletePost(Long postId) {
        Post post = findById(postId);
        postRepository.delete(post);
    }
}
