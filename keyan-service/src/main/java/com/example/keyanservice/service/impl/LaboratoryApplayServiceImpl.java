package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.LaboratoryApplay;
import com.example.keyanservice.entity.User;
import com.example.keyanservice.mapper.LaboratoryApplayMapper;
import com.example.keyanservice.service.ILaboratoryApplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
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
public class LaboratoryApplayServiceImpl extends ServiceImpl<LaboratoryApplayMapper, LaboratoryApplay> implements ILaboratoryApplayService {
@Resource
private LaboratoryApplayMapper laboratoryApplayMapper;


    @Override
    public List<LaboratoryApplay> getlist() {
        return laboratoryApplayMapper.selectList(null);
    }

    @Override
    public void addapplay(LaboratoryApplay laboratoryApplay) {
        laboratoryApplayMapper.insert(laboratoryApplay);
    }

    @Override
    public List<LaboratoryApplay> getUserLab(String Username) {
        return laboratoryApplayMapper.selectList(new QueryWrapper<LaboratoryApplay>().eq(
                "applay_laboratory_user", Username));
    }

    @Override
    public void UpdateLab(LaboratoryApplay laboratoryApplay) {
        laboratoryApplayMapper.updateById(laboratoryApplay);
    }

    @Override
    public List<LaboratoryApplay> getApplayIng() {
        return laboratoryApplayMapper.selectList(new QueryWrapper<LaboratoryApplay>()
                .isNull("ispass"));
    }

    @Override
    public List<LaboratoryApplay> getApplayLog() {
        return laboratoryApplayMapper.selectList(new QueryWrapper<LaboratoryApplay>().isNotNull("ispass"));
    }
}
