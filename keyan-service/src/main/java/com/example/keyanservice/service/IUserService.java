package com.example.keyanservice.service;

import com.example.keyanservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
public interface IUserService extends IService<User> {
    List<User> getlist();

    List<User> login(User user);

    List<User> islogin(String username);
}
