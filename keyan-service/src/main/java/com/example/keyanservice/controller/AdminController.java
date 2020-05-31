package com.example.keyanservice.controller;


import com.example.keyanservice.config.*;
import com.example.keyanservice.entity.Admin;
import com.example.keyanservice.entity.ImgEntity;
import com.example.keyanservice.entity.User;
import com.example.keyanservice.service.impl.AdminServiceImpl;
import com.example.keyanservice.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/keyanservice/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private GetTOken getTOken;
    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        System.out.println(admin);
        List<Admin> adminList = adminService.login(admin);
        if (adminList.size()==1){

            Map<String,Object> map =new HashMap<>();
            map.put("adminName",admin.getAdminName());
            map.put("Date",new Date());
            String createjwt = jwtUtil.createjwt(admin.getAdminName(), map);
            //保存到reids中
            redisTemplate.opsForValue().set(admin.getAdminName(),createjwt);
            redisTemplate.expire(admin.getAdminName(),60, TimeUnit.MINUTES);
            return new Result(ResultCode.SUCCESS,createjwt);
        }else {
            System.out.println("error");
            return new Result(ResultCode.FAIL);
        }


    }

    @GetMapping("/getusername")
    public Result getusername(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        return new Result(ResultCode.SUCCESS,subject);
    }


    @PostMapping("/out")
    public Result LoginOut(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        redisTemplate.delete(subject);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/islogin")
    public Result islogin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        List<Admin> islogin = adminService.islogin(subject);
        if (islogin.size()==1){
            return new Result(ResultCode.SUCCESS);
        }else {
            return new Result(ResultCode.LOGINSX);
        }
    }


    @PostMapping(value = "/loginface",produces = "application/json")
//    public Result getimg(@RequestParam("imagebast64") String imagebast64) {
    public Result getimg(@RequestBody ImgEntity image) {
//        System.out.println(image);
        String imagebast64=image.getImagebast64();
        System.out.println(imagebast64);
        String str2="data:image/jpeg;base64,";

        StringBuffer sb = new StringBuffer(imagebast64);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if (index == -1) {
                break;
            }
            sb.delete(index, index + str2.length());
            delCount++;
        }
        imagebast64=sb.toString();
        System.out.println(imagebast64);
        // 请求url
        String url = " https://aip.baidubce.com/rest/2.0/face/v3/search";
        String result = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imagebast64);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "KeyanSystem");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = getTOken.getAuth();
            System.out.println(accessToken);
            result = HttpUtil.post(url, accessToken, "application/json", param);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        JSONObject object = new JSONObject(result);
        int error_code = object.getInt("error_code");
        String error_msg= object.getString("error_msg");
        System.out.println(error_code+" "+error_msg);
        if (error_code==0){
            int beginindex = result.indexOf("{\"group_id\"");
            int endindex = result.indexOf("]}}");

            String substring = result.substring(beginindex, endindex);

            JSONObject object1 = new JSONObject(substring);
            String user_id= object1.getString("user_id");
            int score = object1.getInt("score");
            System.out.println(user_id+" "+score);
            if (score>=80){
                System.out.println("认证通过");
                //认证通过

                Map<String,Object> map =new HashMap<>();
                map.put("adminName",user_id);
                map.put("Date",new Date());
                String createjwt = jwtUtil.createjwt(user_id, map);
                //保存到reids中
                redisTemplate.opsForValue().set(user_id,createjwt);
                redisTemplate.expire(user_id,60, TimeUnit.MINUTES);


//                    System.out.println(createjwt+"我是生成得token");

                return new Result(ResultCode.FACELOGIN,createjwt);
            }
        }
        //识别失败
        return new Result(ResultCode.FAIL,error_msg);
    }

    @GetMapping("/isfaceinfo")
    public Result IsFaceInfo(){
        //获取当前用户名
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String head = request.getHeader("token");
        System.out.println(head);
        Claims claims = jwtUtil.pasertToken(head);
        String subject = (String) claims.get("adminName");
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";
        String result=null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", "KeyanSystem");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = getTOken.getAuth();

            result = HttpUtil.post(url, accessToken, "application/json", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        if (result.indexOf(subject)!=-1){
            System.out.println("Yes");
            return new Result(ResultCode.SUCCESS,true);
        }
        return  new Result(ResultCode.FAIL,false);

    }


    @PostMapping("/deleteUser")
    public Result DeleteUser(@RequestBody User user){
        userService.DeleteUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/updateUser")
    public Result UpdateUser(@RequestBody User user){

        userService.UpdateUser(user);

        return new Result(ResultCode.SUCCESS);
    }




}
