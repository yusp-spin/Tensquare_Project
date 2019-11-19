package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)  // 表示这个类是联合主键
public class Friend implements Serializable {
    @Id
    private String userid;

    @Id
    private String friendid;

    private String islike;

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

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
