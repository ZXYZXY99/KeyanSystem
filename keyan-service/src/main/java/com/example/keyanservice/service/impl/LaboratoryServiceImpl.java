package com.example.keyanservice.service.impl;

import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.mapper.LaboratoryMapper;
import com.example.keyanservice.service.ILaboratoryService;
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
public class LaboratoryServiceImpl extends ServiceImpl<LaboratoryMapper, Laboratory> implements ILaboratoryService {

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Override
    public List<Laboratory> getlist() {
        return laboratoryMapper.selectList(null);
    }
}
