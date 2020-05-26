package com.example.keyanservice.service;

import com.example.keyanservice.entity.Laboratory;
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
public interface ILaboratoryService extends IService<Laboratory> {


    List<Laboratory> getlist();

    void update(Laboratory laboratory);
    Laboratory selectone(int id);
}
