package com.feiyi.controller;

import com.feiyi.domain.Declarer;
import com.feiyi.domain.Nation;
import com.feiyi.service.DeclarerService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Validated
@RequestMapping("declarer")
public class DeclarerController {

    @Autowired
    DeclarerService declarerService;

    //查询非遗传承人列表
    @GetMapping("/findAll")
    public PageInfo<Declarer> findAll(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size) {
        System.out.println(currentPage+"...."+size);
        PageInfo<Declarer> pageInfo = declarerService.findAll(currentPage, size);
        System.out.println(pageInfo.getSize());
        return pageInfo;
    }
    /*public PageInfo<Declarer> findAll(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<Declarer> pageInfo = declarerService.findAll(pageCode, pageSize);
        System.out.println(pageInfo.getSize());
        return pageInfo;
    }*/
    /*public Object findAll(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size){
        List<Declarer> list = declarerService.findAll(currentPage,size);
        int count = declarerService.countPeople();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        jsonObject.put("total",count);
        return jsonObject;
    }*/

    //添加非遗传承人
    @PostMapping("/addDeclarer")
    public String addDeclarer(@RequestBody Declarer declarer){
        // 保存演员的信息
        declarerService.addDeclarer(declarer);
        return "提交成功!!";
    }

    //编辑非遗
    @GetMapping("/editDeclarer")
    public Map<String,Object> editDeclarer(int declarerId){
        Declarer declarer=declarerService.findById(declarerId);
        List<Nation> nationList=declarerService.findAllNation();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("declarer", declarer);
        dataMap.put("nationList", nationList);
        return dataMap;
    }

    //修改非遗传承人
    @PostMapping("/updateDeclarer")
    public String updateDeclarer(@RequestBody Declarer declarer){
        // 保存演员的信息
        declarerService.updateDeclarer(declarer);
        return "提交成功!!";
    }
    // 删除一条记录
    @PostMapping("/delete")
    public String deleteById(@RequestBody Declarer declarer){
        declarerService.deleteById(declarer.getId());
        return "删除成功";
    }

    @GetMapping("/findAllNation")
    public List<Nation> findAllNation(){
        return declarerService.findAllNation();
    }

    @RequestMapping(value = "/searchDeclarer/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<Declarer> searchDeclarer(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize,String keyword ) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<Declarer> pageInfo = declarerService.searchDeclarer(pageCode, pageSize,keyword);
        return pageInfo;
    }
}
