package com.company.project.service;

import com.company.project.entry.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/05/23.
 */
public interface UserService extends Service<User> {

    User queryUser(String username, String password);

}
