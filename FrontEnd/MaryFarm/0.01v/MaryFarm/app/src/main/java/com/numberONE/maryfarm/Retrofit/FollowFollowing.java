package com.numberONE.maryfarm.Retrofit;

public class FollowFollowing {
    int followerCount, followingCount;

    public FollowFollowing(int followerCount, int followingCount) {
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }
}
