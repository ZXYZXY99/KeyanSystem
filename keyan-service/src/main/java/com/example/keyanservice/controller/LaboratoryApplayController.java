package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.entity.LaboratoryApplay;
import com.example.keyanservice.entity.ProjectApplay;
import com.example.keyanservice.service.impl.LaboratoryApplayServiceImpl;
import com.example.keyanservice.service.impl.LaboratoryServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LaboratoryServiceImpl laboratoryService;


    @GetMapping("/getlist")
    public Result getlist(){
        List<LaboratoryApplay> laboratoryApplayList = laboratoryApplayService.getlist();
        return new Result(ResultCode.SUCCESS,laboratoryApplayList);
    }

    @PostMapping("/getUserLab")
    public Result getUserLab(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("userName");
        List<LaboratoryApplay> projectApplays = laboratoryApplayService.getUserLab(subject);
        return new Result(ResultCode.SUCCESS,projectApplays);
    }

    @PostMapping("/admin/updateLab")
    public Result UpdateLab(@RequestBody LaboratoryApplay laboratoryApplay){
        laboratoryApplayService.UpdateLab(laboratoryApplay);
        Laboratory laboratory=new Laboratory();
        if (laboratoryApplay.getIspass().equals("yes")){
            laboratory.setLaboratoryIsuse("yes");

        }else {
            laboratory.setLaboratoryIsuse("no");
            laboratory.setLaboratoryUsername("null");
        }

        List<Laboratory> laboratories = laboratoryService.SelectByNum(laboratoryApplay.getApplayLaboratoryNum());


        laboratory.setId(laboratories.get(0).getId());
        laboratory.setLaboratoryNum(laboratoryApplay.getApplayLaboratoryNum());

        laboratoryService.update(laboratory);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/admin/getapplaying")
    public Result GetApplayIng(){

        List<LaboratoryApplay> applayIng = laboratoryApplayService.getApplayIng();
        return new Result(ResultCode.SUCCESS,applayIng);

    }

}
