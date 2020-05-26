package com.example.keyanservice.service;

import com.example.keyanservice.entity.ProjectSys;
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
public interface IProjectSysService extends IService<ProjectSys> {

    List<ProjectSys> getlist();

    List<ProjectSys> getUserProject(String username);
    void updateProject(ProjectSys projectSys);

}
