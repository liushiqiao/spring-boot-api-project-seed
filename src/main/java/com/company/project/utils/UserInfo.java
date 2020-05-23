package com.company.project.utils;

import lombok.Data;

/**
 * 载荷对象
 */
@Data
public class UserInfo {

    private Integer id;

    private String username;

    public UserInfo() {
    }

    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}