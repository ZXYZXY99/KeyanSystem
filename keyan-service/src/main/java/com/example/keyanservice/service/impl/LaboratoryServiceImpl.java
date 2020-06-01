package com.example.keyanservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.mapper.LaboratoryMapper;
import com.example.keyanservice.service.ILaboratoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class LaboratoryServiceImpl extends ServiceImpl<LaboratoryMapper, Laboratory> implements ILaboratoryService {

    @Resource
    private LaboratoryMapper laboratoryMapper;

    @Override
    public List<Laboratory> getlist() {
        return laboratoryMapper.selectList(new QueryWrapper<Laboratory>().eq("laboratory_isuse","no"));
    }

    @Override
    public void update(Laboratory laboratory) {
        laboratoryMapper.updateById(laboratory);
    }

    @Override
    public Laboratory selectone(int id) {
        return laboratoryMapper.selectById(id);
    }

    @Override
    public void AddLab(Laboratory laboratory) {
        laboratoryMapper.insert(laboratory);
    }

    @Override
    public List<Laboratory> getAll() {
        return laboratoryMapper.selectList(null);
    }

    @Override
    public void deleteLab(Laboratory laboratory) {
        laboratoryMapper.deleteById(laboratory.getId());
    }

    @Override
    public List<Laboratory> SelectByNum(String num) {
        return laboratoryMapper.selectList(new QueryWrapper<Laboratory>().eq("laboratory_num",num));
    }


}
