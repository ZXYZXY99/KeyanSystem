package com.example.keyanservice.service.impl;

import com.example.keyanservice.entity.ProjectApplay;
import com.example.keyanservice.mapper.ProjectApplayMapper;
import com.example.keyanservice.service.IProjectApplayService;
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
public class ProjectApplayServiceImpl extends ServiceImpl<ProjectApplayMapper, ProjectApplay> implements IProjectApplayService {

    @Autowired
    private ProjectApplayMapper projectApplayMapper;

    @Override
    public List<ProjectApplay> getlist() {
        return projectApplayMapper.selectList(null);
    }
}
