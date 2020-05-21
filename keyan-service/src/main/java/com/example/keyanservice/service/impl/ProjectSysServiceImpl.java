package com.example.keyanservice.service.impl;

import com.example.keyanservice.entity.ProjectSys;
import com.example.keyanservice.mapper.ProjectSysMapper;
import com.example.keyanservice.service.IProjectSysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ProjectSysMapper projectSysMapper;
    @Override
    public List<ProjectSys> getlist() {
        return projectSysMapper.selectList(null);
    }
}
