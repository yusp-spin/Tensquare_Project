package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/*
远程调用其它微服务
 */
@FeignClient("tensquare-user")
public interface UserClient {
    @PutMapping("/user/{userId}/{friendId}/{x}")
    void updateFansAndFollower(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId,
                               @PathVariable("x") int x);
}
