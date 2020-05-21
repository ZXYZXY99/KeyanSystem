package com.example.keyanservice.controller;


import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.LaboratoryApplay;
import com.example.keyanservice.service.impl.LaboratoryApplayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/keyanservice/laboratory-applay")
public class LaboratoryApplayController {
    @Autowired
    private LaboratoryApplayServiceImpl laboratoryApplayService;


    @GetMapping("/getlist")
    public Result getlist(){
        List<LaboratoryApplay> laboratoryApplayList = laboratoryApplayService.getlist();

        return new Result(ResultCode.SUCCESS,laboratoryApplayList);
    }


}
