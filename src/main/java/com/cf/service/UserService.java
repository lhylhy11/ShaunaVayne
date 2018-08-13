package com.cf.service;

import com.cf.domain.User;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface UserService {
    List<User> getUserInfo();
    Page<User> getUserInfo1();
}
