package com.feiyi.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.feiyi.domain.Role;
import com.feiyi.domain.User;
import com.feiyi.exceptions.InnertalException;
import com.feiyi.service.TokenService;
import com.feiyi.service.UserService;
import com.feiyi.utils.FileLoad;

import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OSS ossClient;

    @Value("${oss.bucketName}")
    private String bucketName;

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User u = userService.findUser(user);
        JSONObject jsonObject = new JSONObject();
        System.out.println(u);
        if (u == null) {
            jsonObject.put("msg", "账号或密码错误");
            jsonObject.put("code", 500);
        } else {
            if(u.getRoleId() == 1)
                jsonObject.put("role",1);
            else
                jsonObject.put("role",2);
            user = u;
            System.out.println(user);
            String token = tokenService.getToken(user);
            jsonObject.put("token", token);
            jsonObject.put("user", user);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200);
        }
        return jsonObject;
    }

    //添加照片
    @PostMapping("/register")
    public Object register(@RequestParam(value = "file") MultipartFile file, User user){
        JSONObject jsonObject = null;
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName= "users/" + file.getOriginalFilename();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();

            User user1= userService.findUserByName(user.getUsername());
            jsonObject = new JSONObject();
            if(user1==null){
                user.setPic(fileUrl);
                user.setStatus(0);//表示用户注册之后默认为通过
                user.setRoleId(2);//表示注册默认为普通用户
                user.setCreated_date(new Date());//注册日期为当前
                String token = tokenService.getToken(user);
                jsonObject.put("token", token);
                jsonObject.put("user", user);
                jsonObject.put("msg", "注册成功");
                jsonObject.put("code", 200);
                userService.addUser(user);
            }else{
                jsonObject.put("msg", "注册失败，用户名重复");
                jsonObject.put("code", 500);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg","注册失败，请求异常");
            jsonObject.put("code",403);
        }
        return jsonObject;
    }

    @GetMapping("/findAll")
    public PageInfo<User> findAll(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size) {
        System.out.println(currentPage+"...."+size);
        PageInfo<User> pageInfo = userService.findAllUser(currentPage, size);
        List<User> userList = pageInfo.getList();
        int age;
        for(int i = 0;i<userList.size(); i++)
        {
            age = new Date().getYear() - userList.get(i).getBirthday().getYear();
            userList.get(i).setAge(age);
        }
        return pageInfo;
    }
    /*public List<User> findAll(){
        List<User> list = userService.findAllUser();
        int age;
        for(int i = 0;i<list.size(); i++)
        {
            age = new Date().getYear() - list.get(i).getBirthday().getYear();
            list.get(i).setAge(age);
        }
        return list;
    }*/

    @PostMapping("/addUser")
    public Object addUser(@RequestParam(value = "file") MultipartFile file, User user) throws InnertalException {
        JSONObject jsonObject = null;
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName= "users/" + file.getOriginalFilename();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();
            jsonObject = new JSONObject();
                user.setPic(fileUrl);
                user.setCreated_date(new Date());//注册日期为当前
                user.setPassword("123456");
                jsonObject.put("msg", "保存成功");
                jsonObject.put("code", 200);
                userService.addUser(user);

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg","保存失败，请求异常");
            jsonObject.put("code",403);
        }
        return jsonObject;
    }

    @GetMapping("/getRole")
    public List<Role> findAllRole(){

        return userService.findAllRole();
    }

    @GetMapping("/editUser")
    public User editUser(int userId){
        return userService.findById(userId);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) throws InnertalException {
        user.setFinal_date(new Date());
        System.out.println(user);
        userService.updateUserById(user);
        return "保存成功";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody User user){
        userService.deleteById(user.getId());
        return "删除成功";
    }

    @PostMapping("/updatePerson")
    public Object updatePerson(@RequestParam(value = "file") MultipartFile file, User user){
        JSONObject jsonObject = new JSONObject();
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName= "users/" + file.getOriginalFilename();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();

            user.setPic(fileUrl);
            user.setFinal_date(new Date());
            userService.updateFontUserById(user);
            jsonObject.put("user",user);
            jsonObject.put("code",200);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","保存失败");
        }
        return jsonObject;
    }

    //修改密码
    @PostMapping("updatePassword")
    public String updatePassword(@RequestBody User user){
        userService.updatePassword(user);
        System.out.println(user.getPassword());
        return "修改成功！";
    }

    @RequestMapping(value = "/searchUser/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<User> searchUser(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize,String keyword) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<User> pageInfo = userService.searchUser(pageCode, pageSize,keyword);
        List<User> userList = pageInfo.getList();
        int age;
        for(int i = 0;i<userList.size(); i++)
        {
            age = new Date().getYear() - userList.get(i).getBirthday().getYear();
            userList.get(i).setAge(age);
        }
        return pageInfo;
    }
}
