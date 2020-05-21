package com.example.keyanservice.service;

import com.example.keyanservice.entity.LaboratoryApplay;
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
public interface ILaboratoryApplayService extends IService<LaboratoryApplay> {

    List<LaboratoryApplay> getlist();

}
