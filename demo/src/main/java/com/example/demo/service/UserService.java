package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;
import java.util.concurrent.Future;


public interface UserService {
    User getUser();

    List<User> getUsers();

    Future<User> getUserById(int id);
}
