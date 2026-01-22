package com.sarthak.twitterclone.follow;

import com.sarthak.twitterclone.common.exception.ResourceNotFoundException;
import com.sarthak.twitterclone.user.User;
import com.sarthak.twitterclone.user.UserRepository;
import com.sarthak.twitterclone.user.dto.UserSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FollowQueryService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public FollowQueryService(UserRepository userRepository, FollowRepository followRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public Page<UserSummaryDTO> getFollowers(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Page<Follow> follows = followRepository.findByFollowing(user, pageable);

        return follows.map(follow ->
                new UserSummaryDTO(
                        follow.getFollower().getId(),
                        follow.getFollower().getEmail()
                )
        );
    }

    public Page<UserSummaryDTO> getFollowing(Long userId, Pageable pageable) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Page<Follow> follows = followRepository.findByFollower(user, pageable);

        return follows.map(follow ->
                new UserSummaryDTO(
                        follow.getFollowing().getId(),
                        follow.getFollowing().getEmail()
                )
        );
    }
}
