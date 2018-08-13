package com.cf.service.impl;

import com.cf.dao.UserDao;
import com.cf.domain.User;
import com.cf.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.cf.config.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PageInfo pageInfo;

    @Override
    public List<User> getUserInfo() {
        List<User> userList = userDao.getUserList();
        return userList;
    }

    @Override
    public Page<User> getUserInfo1() {
        PageHelper.startPage(1, 2);
        Page<User> userList = userDao.getUserList();
        pageInfo.setList(userList);
        return userList;
    }
}
