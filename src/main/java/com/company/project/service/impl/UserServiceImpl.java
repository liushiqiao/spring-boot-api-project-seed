package com.company.project.service.impl;

import com.company.project.mapper.UserMapper;
import com.company.project.entry.model.User;
import com.company.project.core.AbstractService;
import com.company.project.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/05/23.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userAccountMapper;

    @Override
    public User queryUser(String username, String password) {
        return userAccountMapper.queryUser(username, password);
    }
}
