package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)  // 表示是联合主键
public class NoFriend implements Serializable {
    @Id
    private String userid;

    @Id
    private String friendid;

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getFriendId() {
        return friendid;
    }

    public void setFriendId(String friendId) {
        this.friendid = friendId;
    }
}
