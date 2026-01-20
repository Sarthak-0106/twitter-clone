package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.auth.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// @Authentication helps to get the authentication object directly from the securityContext
// by this we can directly extract the userPrincipal that we created at the time of filter

@RestController
@RequestMapping("/users")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    //We use @AuthenticationPrincipal to inject the authenticated user directly
    // into controller methods, avoiding direct access to the SecurityContext and
    // keeping controllers clean and testable
    @PostMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@PathVariable Long id,
                       @AuthenticationPrincipal UserPrincipal principal) {
        followService.follow(principal.getUserId(), id);
    }

    @DeleteMapping("/{id}/unfollow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@PathVariable Long id,
                         @AuthenticationPrincipal UserPrincipal principal) {
        followService.unfollow(principal.getUserId(), id);
    }
}
