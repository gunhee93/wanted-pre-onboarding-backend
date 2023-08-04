package wantedpreonboarding.backend.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wantedpreonboarding.backend.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p " +
            "join fetch p.user u " +
            "order by p.createdAt desc",
            countQuery = "select count(p) from Post p")
    Page<Post> findAllNewest(Pageable pageable);
}
