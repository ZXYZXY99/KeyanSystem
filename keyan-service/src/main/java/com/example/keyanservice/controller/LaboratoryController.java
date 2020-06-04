package com.example.keyanservice.controller;


import com.example.keyanservice.config.JwtUtil;
import com.example.keyanservice.config.Result;
import com.example.keyanservice.config.ResultCode;
import com.example.keyanservice.entity.Laboratory;
import com.example.keyanservice.entity.LaboratoryApplay;
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
@RequestMapping("/keyanservice/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryServiceImpl laboratoryService;
    @Autowired
    private LaboratoryApplayServiceImpl laboratoryApplayService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getlist")
    public Result getlist(){
        List<Laboratory> laboratoryList = laboratoryService.getlist();
        return new Result(ResultCode.SUCCESS,laboratoryList);
    }
    @GetMapping("/getAll")
    public Result GetAll(){
        List<Laboratory> all = laboratoryService.getAll();
        return new Result(ResultCode.SUCCESS,all);


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


    @PostMapping("/admin/addLab")
    public Result AddLab(@RequestBody Laboratory laboratory){
        laboratoryService.AddLab(laboratory);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/admin/deleteLab")
    public Result DeleteLab(@RequestBody Laboratory laboratory){
        laboratoryService.deleteLab(laboratory);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/getmyLab")
    public Result GetMyLab(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("userName");
        List<Laboratory> laboratories = laboratoryService.GetMyLab(subject);
        return new Result(ResultCode.SUCCESS,laboratories);
    }

    @PostMapping("/returnLab")
    public Result ReturnLab(@RequestBody Laboratory laboratory){
        laboratory.setLaboratoryIsuse("no");
        laboratory.setLaboratoryUsername("null");
        laboratoryService.ReturnLab(laboratory);

        return new Result(ResultCode.SUCCESS);
    }

}
