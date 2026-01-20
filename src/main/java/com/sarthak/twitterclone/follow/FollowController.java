package com.sarthak.twitterclone.follow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@PathVariable Long id,
                       @RequestHeader("X-User-Id") Long followerId) {
        followService.follow(followerId, id);
    }

    @DeleteMapping("/{id}/unfollow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@PathVariable Long id,
                         @RequestHeader("X-User-Id") Long followerId) {
        followService.unfollow(followerId, id);
    }
}
