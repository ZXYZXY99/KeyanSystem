package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.User;
import com.example.keyanservice.mapper.UserMapper;
import com.example.keyanservice.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
@Resource
private UserMapper userMapper;

    @Override
    public List<User> getlist() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> login(User user) {
        return userMapper.selectList(new QueryWrapper<User>().eq("user_name",user.getUserName())
                .eq("user_password",user.getUserPassword()));
    }

    @Override
    public List<User> islogin(String username) {
        return userMapper.selectList(new QueryWrapper<User>().eq("user_name",username));
    }

    @Override
    public void DeleteUser(User user) {
        userMapper.deleteById(user.getId());
    }

    @Override
    public void UpdateUser(User user) {
        userMapper.updateById(user);
    }
}
