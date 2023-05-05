package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.AsyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ThreadPoolTaskExecutor threadPool;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper mapper;

    @Override
    public User getUser() {
        return mapper.getUser();
    }

    @Override
    public List<User> getUsers() {
        return mapper.getUsers();
    }

    /*@Async
    @Override
    public User getUserById(int id) {
        System.out.println("start select>>>");
        User userById = mapper.getUserById(1);
        System.out.println("end select>>>");
        return userById;
    }*/

    @Async
    @Override
    public Future<User> getUserById(int id) {
        threadPool.execute(() -> {
            System.out.println("开始");
            for (int i = 0; i < mapper.getUsers().size(); i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("结束");
        });
        logger.info(Thread.currentThread().getName());
        System.out.println("start select>>>");
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        System.out.println("end select>>>");
        return new AsyncResult<>(userById);
    }
}
