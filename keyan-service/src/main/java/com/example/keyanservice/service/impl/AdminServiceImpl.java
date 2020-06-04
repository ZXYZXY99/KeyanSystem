package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.Admin;
import com.example.keyanservice.mapper.AdminMapper;
import com.example.keyanservice.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Resource
    private AdminMapper adminMapper;


    @Override
    public List<Admin> login(Admin admin) {
        return adminMapper.selectList(new QueryWrapper<Admin>().eq("admin_name",admin.getAdminName())
                .eq("admin_password",admin.getAdminPassword()));
    }

    @Override
    public List<Admin> islogin(String adminname) {
        return adminMapper.selectList(new QueryWrapper<Admin>().eq("admin_name",adminname));
    }



}
