package com.github.awesomechat.chat.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    @Autowired
    UserRepositoryImpl(DataSource ds) {
        this.dataSource = ds;
    }
}
