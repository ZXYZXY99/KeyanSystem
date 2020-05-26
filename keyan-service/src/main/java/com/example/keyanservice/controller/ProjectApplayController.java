package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.ProjectApplay;
import com.example.keyanservice.service.impl.ProjectApplayServiceImpl;
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
@RequestMapping("/keyanservice/project-applay")
public class ProjectApplayController {

    @Autowired
    private ProjectApplayServiceImpl projectApplayService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getlist")
    public Result getlist(){
        List<ProjectApplay> getlist = projectApplayService.getlist();

        return new Result(ResultCode.SUCCESS,getlist);
    }

    @PostMapping("/applayproject")
    public Result ApplayProject(@RequestBody ProjectApplay projectApplay){
        System.out.println(projectApplay);
        projectApplayService.insertAppley(projectApplay);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/applayprojectuser")
    public Result applayProjectUser(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("userName");
        List<ProjectApplay> projectApplays = projectApplayService.getprojectUser(subject);

        return new Result(ResultCode.SUCCESS,projectApplays);

    }





}
