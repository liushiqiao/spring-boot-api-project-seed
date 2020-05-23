package com.company.project.entry.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_account")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    private Integer age;
}