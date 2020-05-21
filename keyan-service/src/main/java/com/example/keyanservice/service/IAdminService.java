package com.example.keyanservice.service;

import com.example.keyanservice.entity.Admin;
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
public interface IAdminService extends IService<Admin> {
    List<Admin> login(Admin admin);
    List<Admin> islogin(String adminname);

}
