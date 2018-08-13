package com.cf.dao;

import com.cf.domain.User;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    Page<User> getUserList();
}
