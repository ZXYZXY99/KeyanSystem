package com.example.keyanservice.service.impl;

import com.example.keyanservice.entity.LaboratoryApplay;
import com.example.keyanservice.mapper.LaboratoryApplayMapper;
import com.example.keyanservice.service.ILaboratoryApplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
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
public class LaboratoryApplayServiceImpl extends ServiceImpl<LaboratoryApplayMapper, LaboratoryApplay> implements ILaboratoryApplayService {
@Autowired
private LaboratoryApplayMapper laboratoryApplayMapper;


    @Override
    public List<LaboratoryApplay> getlist() {
        return laboratoryApplayMapper.selectList(null);
    }
}
