package com.feiyi.controller;

import com.feiyi.domain.Pcollect;
import com.feiyi.domain.Pexhibition;
import com.feiyi.service.ExhibitionService;
import com.feiyi.service.OwnerService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("owner")
@CrossOrigin
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ExhibitionService exhibitionService;

    //收藏
    @PostMapping("/addCollect")
    public String addCollect(@RequestBody Pcollect pcollect){
        ownerService.addCollect(pcollect);
        return "收藏成功！";
    }

    //取消收藏
    @PostMapping("/delectCollect")
    public String delectCollect(@RequestBody Pcollect pcollect){
        ownerService.deleteCollectById(pcollect);
        return "删除成功!";
    }

    //预约
    @PostMapping("/Book")
    public Object Book(@RequestBody Pexhibition pexhibition){
        JSONObject jsonObject = new JSONObject();
        //System.out.println(pexhibition);
        Integer exhibotionId = pexhibition.getExhibitionId();
        //比较该展馆预约的数量和该展馆的容量
        if(ownerService.countByEid(exhibotionId) < exhibitionService.findById(exhibotionId).getAccommodate())
        {
            ownerService.addBook(pexhibition);
            jsonObject.put("code","200");
            jsonObject.put("msg","收藏成功！");
        }
        else
        {
            jsonObject.put("code","500");
            jsonObject.put("msg","该展馆已约满");
        }
        return jsonObject;
    }

    //取消预约
    @PostMapping("/deleteExhibition")
    public String deleteExhibition(@RequestBody Pexhibition pexhibition){
        System.out.println(pexhibition);
        ownerService.deleteExhibitionById(pexhibition);
        return "删除成功!";
    }



    //个人中心：查看收藏作品
    @GetMapping("/findCollectById")
    public List<Pcollect> findCollectById(@RequestParam Integer userId, @RequestParam Integer categoryId){
        return ownerService.findCollectById(userId,categoryId);
    }

    //个人中心：查看预约的展馆
    @GetMapping("findExhibitionById")
    public List<Pexhibition> findExhibitionById(@RequestParam Integer userId){
        return ownerService.findExhibitionById(userId);
    }

}
