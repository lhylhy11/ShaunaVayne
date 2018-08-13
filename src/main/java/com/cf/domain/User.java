package com.cf.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class User {
    private String userId;
    private String password;
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    public User() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
