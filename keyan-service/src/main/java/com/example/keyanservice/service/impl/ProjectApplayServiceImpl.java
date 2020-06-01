package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.ProjectApplay;
import com.example.keyanservice.entity.User;
import com.example.keyanservice.mapper.ProjectApplayMapper;
import com.example.keyanservice.service.IProjectApplayService;
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
public class ProjectApplayServiceImpl extends ServiceImpl<ProjectApplayMapper, ProjectApplay> implements IProjectApplayService {

    @Resource
    private ProjectApplayMapper projectApplayMapper;

    @Override
    public List<ProjectApplay> getlist() {
        return projectApplayMapper.selectList(null);
    }

    @Override
    public void insertAppley(ProjectApplay projectApplay) {
        projectApplayMapper.insert(projectApplay);
    }

    @Override
    public List<ProjectApplay> getprojectUser(String Username) {
        return projectApplayMapper.selectList(new QueryWrapper<ProjectApplay>().eq("apply_user",
                Username));
    }

    @Override
    public void updateapplayproject(ProjectApplay projectApplay) {
        projectApplayMapper.updateById(projectApplay);
    }

    @Override
    public List<ProjectApplay> getlistByisuse() {
        return projectApplayMapper.selectList(new QueryWrapper<ProjectApplay>().isNull("ispass"));
    }
}
