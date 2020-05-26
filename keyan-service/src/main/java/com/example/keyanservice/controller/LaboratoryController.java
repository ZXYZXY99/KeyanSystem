package com.example.keyanservice.controller;


import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.entity.LaboratoryApplay;
import com.example.keyanservice.service.impl.LaboratoryApplayServiceImpl;
import com.example.keyanservice.service.impl.LaboratoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/keyanservice/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryServiceImpl laboratoryService;
    @Autowired
    private LaboratoryApplayServiceImpl laboratoryApplayService;

    @GetMapping("/getlist")
    public Result getlist(){

        List<Laboratory> laboratoryList = laboratoryService.getlist();

        return new Result(ResultCode.SUCCESS,laboratoryList);

    }

    @PostMapping("/applayupdate")
    public Result applayupdate(@RequestParam Integer id ,@RequestParam String username){
        Laboratory laboratory = laboratoryService.selectone(id);
        laboratory.setLaboratoryIsuse("yes");
        laboratoryService.update(laboratory);

        LaboratoryApplay laboratoryApplay = new LaboratoryApplay();
        laboratoryApplay.setApplayLaboratoryNum(laboratory.getLaboratoryNum());
        laboratoryApplay.setApplayLaboratoryUser(username);
        System.out.println(laboratoryApplay);
        laboratoryApplayService.addapplay(laboratoryApplay);

        return new Result(ResultCode.SUCCESS);

    }



}
