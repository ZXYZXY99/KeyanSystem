package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.ProjectSys;
import com.example.keyanservice.mapper.ProjectSysMapper;
import com.example.keyanservice.service.IProjectSysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
public class ProjectSysServiceImpl extends ServiceImpl<ProjectSysMapper, ProjectSys> implements IProjectSysService {

    @Resource
    private ProjectSysMapper projectSysMapper;
    @Override
    public List<ProjectSys> getlist() {
        return projectSysMapper.selectList(null);
    }

    @Override
    public List<ProjectSys> getUserProject(String username) {
        return projectSysMapper.selectList(new QueryWrapper<ProjectSys>().eq("project_user",username));
    }

    @Override
    public void updateProject(ProjectSys projectSys) {
        projectSysMapper.updateById(projectSys);
    }

    @Override
    public void addProject(ProjectSys projectSys) {
        projectSysMapper.insert(projectSys);
    }
}
