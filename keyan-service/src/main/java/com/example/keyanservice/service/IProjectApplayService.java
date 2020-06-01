package com.example.keyanservice.service;

import com.example.keyanservice.entity.ProjectApplay;
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
public interface IProjectApplayService extends IService<ProjectApplay> {

    List<ProjectApplay> getlist();

    void insertAppley(ProjectApplay projectApplay);
    List<ProjectApplay> getprojectUser(String Username);
    void updateapplayproject(ProjectApplay projectApplay);
    List<ProjectApplay> getlistByisuse();
}
