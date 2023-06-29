package com.feiyi.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.feiyi.domain.Exhibition;
import com.feiyi.domain.Works;
import com.feiyi.service.ExhibitionService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping("exhibition")
public class ExhibitionController {

    /*@Value("${cos.secret-id}")
    private String cosSecretId;

    @Value("${cos.secret-key}")
    private String cosSecretKey;

    @Value("${cos.bucket-name}")
    private String cosBucketName;

    @Value("${cos.domain}")
    private String cosDomain;*/

    @Autowired
    private OSS ossClient;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Autowired
    ExhibitionService exhibitionService;

    //查询展览馆列表,已经的审核
    @GetMapping("/findAll")
    public PageInfo<Exhibition> findAll(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size){
        System.out.println(currentPage+"...."+size);
        PageInfo<Exhibition> pageInfo = exhibitionService.findAll(currentPage, size);
        return pageInfo;
    }

    //根据id查找
    @GetMapping("/findById")
    public Exhibition findById(@RequestParam int eid){
        return exhibitionService.findById(eid);
    }
    /*public List<Exhibition> findAll(){
        //System.out.println("查找所有");
        return exhibitionService.findAll();
    }*/

    //查询未审核展览馆
    @GetMapping("/findAllByStatus")
    public PageInfo<Exhibition> findAll0(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size, @RequestParam Integer status){
        System.out.println(currentPage+"...."+size);
        PageInfo<Exhibition> pageInfo = exhibitionService.findAllByStatus(currentPage, size, status);
        return pageInfo;
    }

    @GetMapping("/findAllByStatusFont")
    public List<Exhibition> findAllByStatusFont(@RequestParam Integer status){
        return exhibitionService.findAllByStatus(status);
    }

    //添加展览馆
    /*@PostMapping("/addExhibition")
    public String addImage(@RequestBody Exhibition exhibition){
        exhibitionService.addExhibition(exhibition);
        return "提交成功!";
    }*/
    @PostMapping("/addExhibition")
    public ResponseEntity<String> addWorks(@RequestParam(value = "file") MultipartFile file,
                                           Exhibition exhibition){
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName= "exhibition/" + file.getOriginalFilename();

            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();

            exhibition.setUrl(fileUrl);
            exhibitionService.addExhibition(exhibition);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }
    /*public String addImage(@RequestParam(value = "file") MultipartFile file,
                           Exhibition exhibition) throws IOException {
        // 初始化 COS 客户端
        BasicCOSCredentials credentials = new BasicCOSCredentials(cosSecretId, cosSecretKey);
        ClientConfig clientConfig = new ClientConfig();
        Region region = new Region("ap-beijing");
        clientConfig.setRegion(region);
        COSClient cosClient = new COSClient(credentials, clientConfig);

        // 生成文件名，上传文件到 COS
        String fileName = "exhibition/" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        cosClient.putObject(cosBucketName, fileName, inputStream, metadata);

        // 关闭 COS 客户端，返回文件链接地址
        cosClient.shutdown();
        String url = cosDomain + "/" + fileName;
        exhibition.setUrl(url);
        exhibitionService.addExhibition(exhibition);
        return "保存成功";
    }*/

    @PostMapping("/updateExhibition")
    // 更新一条记录
    public String updateExhibition(@RequestBody Exhibition exhibition){
        // 更新影音的信息
        exhibitionService.updateExhibition(exhibition);
        return "更新成功!!";
    }

    // 删除一条记录
    @PostMapping("/deleteExhibition")
    public String deleteById(@RequestBody Exhibition exhibition){
        exhibitionService.deleteExhibition(exhibition.getId());
        return "删除成功";
    }

    //根据tittle查询
    @GetMapping("/findByTitle")
    public List<Exhibition> findByTitle(String title){
        return exhibitionService.findByTitle(title);
    }

    @PostMapping("/updateExhibitionStatus")
    public String updateExhibitionStatus(@RequestBody Exhibition exhibition){
        // 更新影音的信息
        exhibitionService.updateExhibition(exhibition);
        return "更新成功!!";
    }

    @RequestMapping(value = "/searchExhibition/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<Exhibition> searchEhb(@PathVariable(value = "pageCode") int pageCode,
                                               @PathVariable(value = "pageSize") int pageSize,
                                               String keyword) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<Exhibition> pageInfo = exhibitionService.findExhibition(pageCode, pageSize,keyword);
        return pageInfo;
    }
    @RequestMapping(value = "/searchExhibition0/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<Exhibition> searchEhb0(@PathVariable(value = "pageCode") int pageCode,
                                               @PathVariable(value = "pageSize") int pageSize,
                                               String keyword) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<Exhibition> pageInfo = exhibitionService.findExhibition0(pageCode, pageSize,keyword);
        return pageInfo;
    }

    @GetMapping("/findByAll")
    public List<Exhibition> findByAll(@RequestParam String keyword){
            return exhibitionService.findByAll(keyword);
    }
}
