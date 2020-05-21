package com.example.keyanservice.controller;


import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.service.impl.LaboratoryServiceImpl;
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
@RequestMapping("/keyanservice/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryServiceImpl laboratoryService;

    @GetMapping("/getlist")
    public Result getlist(){

        List<Laboratory> laboratoryList = laboratoryService.getlist();

        return new Result(ResultCode.SUCCESS,laboratoryList);

    }


}
