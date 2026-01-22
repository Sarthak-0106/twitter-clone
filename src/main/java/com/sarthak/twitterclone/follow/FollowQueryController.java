package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.user.dto.UserSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class FollowQueryController {
    private final FollowQueryService followQueryService;

    public FollowQueryController(FollowQueryService followQueryService) {
        this.followQueryService = followQueryService;
    }

    @GetMapping("/{id}/followers")
    public Page<UserSummaryDTO> getFollowers(@PathVariable Long id, Pageable pageable) {
        return followQueryService.getFollowers(id, pageable);
    }

    @GetMapping("/{id}/following")
    public Page<UserSummaryDTO> getFollowing(@PathVariable Long id, Pageable pageable) {
        return followQueryService.getFollowing(id, pageable);
    }
}
