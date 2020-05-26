package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.ProjectSys;
import com.example.keyanservice.service.impl.ProjectSysServiceImpl;
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
@RequestMapping("/keyanservice/project-sys")
public class ProjectSysController {
    @Autowired
    private ProjectSysServiceImpl projectSysService;
    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/getlist")
    public Result getlist(){

        List<ProjectSys> projectSysList = projectSysService.getlist();

        return new Result(ResultCode.SUCCESS,projectSysList);
    }
    @GetMapping("/getuserproject")
    public Result getUserProject(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String username = (String) claims.get("userName");
        System.out.println(username);
        List<ProjectSys> userProject = projectSysService.getUserProject(username);
        return new Result(ResultCode.SUCCESS,userProject);

    }

    @PostMapping("/updateproject")
    public Result updateProject(@RequestBody ProjectSys projectSys){
        projectSysService.updateProject(projectSys);
        return new Result(ResultCode.SUCCESS);
    }



}
