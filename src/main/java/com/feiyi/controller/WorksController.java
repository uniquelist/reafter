package com.feiyi.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.feiyi.domain.User;
import com.feiyi.domain.Works;
import com.feiyi.service.WorksService;

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
@RequestMapping("works")
public class WorksController {
    @Autowired
    WorksService worksService;
    /*@Value("${upload.dir}")
    private String uploadDir;

    @Value("${cos.secret-id}")
    private String cosSecretId;

    @Value("${cos.secret-key}")
    private String cosSecretKey;

    @Value("${cos.bucket-name}")
    private String cosBucketName;

    @Value("${cos.domain}")
    private String cosDomain;*/
//    阿里云
    @Autowired
    private OSS ossClient;

    @Value("${oss.bucketName}")
    private String bucketName;

    //查询照片列表
    @GetMapping("/findAll")
    /*public List<Works> findAll(Integer categoryId){
        System.out.println(categoryId);
        return worksService.findAll(categoryId);
    }*/
    //分页
    public PageInfo<Works> findAll(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size, Integer categoryId) {
        System.out.println(currentPage+"...."+size+"......."+categoryId);
        PageInfo<Works> pageInfo = worksService.findByPageService(currentPage, size,categoryId);
        return pageInfo;
    }

    @GetMapping("/findAllFont")
    /*public List<Works> findAll(Integer categoryId){
        System.out.println(categoryId);
        return worksService.findAll(categoryId);
    }*/
    //分页
    public List<Works> findAll(@RequestParam Integer categoryId) {
        return worksService.findAll(categoryId);
    }

    //查询照片列表
    @GetMapping("/findAll0")
    public PageInfo<Works> findAll0(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "6") int size, Integer categoryId) {
        System.out.println(currentPage+"...."+size+"......."+categoryId);
        PageInfo<Works> pageInfo = worksService.findAll0(currentPage, size,categoryId);
        return pageInfo;
    }
    /*public List<Works> findAll0(Integer categoryId){
        return worksService.findAll0(categoryId);
    }*/

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    private boolean isImageFile(String fileExtension) {
        return fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg") ||
                fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("gif") || fileExtension.equalsIgnoreCase("webp");
    }

    private boolean isVideoFile(String fileExtension) {
        return fileExtension.equalsIgnoreCase("mp4") || fileExtension.equalsIgnoreCase("avi") ||
                fileExtension.equalsIgnoreCase("mov") || fileExtension.equalsIgnoreCase("mkv");
    }

    //添加照片
    @PostMapping("/addWorks")
//    阿里云
    public ResponseEntity<String> addWorks(@RequestParam(value = "file") MultipartFile file,
                                           Works works){
        String fileName_w = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName_w);
        System.out.println(fileExtension);
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName;
            if(isImageFile(fileExtension)){
                fileName = "images/" + file.getOriginalFilename();
                works.setCategoryId(1);

            }
            else if(isVideoFile(fileExtension))
            {
                fileName = "videos/" + file.getOriginalFilename();
                works.setCategoryId(3);
            }
            else
            {
                fileName = "audios/" + file.getOriginalFilename();
                works.setCategoryId(2);
            }
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();

            works.setUrl(fileUrl);
            worksService.addWorks(works);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }
//    腾讯云
    /*public String addWorks(@RequestParam(value = "file") MultipartFile file,
                           Works works) throws IOException {
        //判断上传文件的类型
        String fileName_w = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName_w);
        System.out.println(fileExtension);
        // 初始化 COS 客户端
        BasicCOSCredentials credentials = new BasicCOSCredentials(cosSecretId, cosSecretKey);
        ClientConfig clientConfig = new ClientConfig();
        Region region = new Region("ap-beijing");
        clientConfig.setRegion(region);
        COSClient cosClient = new COSClient(credentials, clientConfig);


        // 生成文件名，上传文件到 COS
        String fileName;
        if (isImageFile(fileExtension)) {
            fileName = "images/" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            works.setCategoryId(1);
        } else if (isVideoFile(fileExtension)) {
            System.out.println("视频");
            works.setCategoryId(3);
            fileName = "videos/" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        } else {
            works.setCategoryId(2);
            fileName = "audios/" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        }

        InputStream inputStream = file.getInputStream();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        cosClient.putObject(cosBucketName, fileName, inputStream, metadata);

        // 关闭 COS 客户端，返回文件链接地址
        cosClient.shutdown();
        String url = cosDomain + "/" + fileName;
        works.setUrl(url);

        worksService.addWorks(works);
        return "保存成功";
    }*/

    /*@PostMapping("/uploadSliceFile")
    public Object uploadSliceFile(@RequestParam("sFile")MultipartFile sFile,@RequestParam("realFilename") String realFilename, @RequestParam("index") Integer index) throws IOException {

        String md5 = DigestUtils.md5Hex(realFilename);
        System.out.println(realFilename);
        System.out.println(md5);
        System.out.println("分片名: " + sFile.getOriginalFilename());
        File dir = new File("d:/Projects/practice/test-springboot/src/main/resources/file/fragment/" + md5);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File sFileWithIndex = new File("d:/Projects/practice/test-springboot/src/main/resources/file/fragment/" + md5 + "/" + index);
        sFile.transferTo(sFileWithIndex);
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", "ok");
        return data;
    }
    @PostMapping("/mergeFragmentFile")
    public Object mergeFragmentFile(@RequestParam String realFilename) throws IOException {

        System.out.println("-------开始合并文件");

        // 合并的文件
        RandomAccessFile raf = new RandomAccessFile("d:/Projects/practice/test-springboot/src/main/resources/file/" + realFilename, "rw");

        // 获取分片所在文件夹
        String md5 = DigestUtils.md5Hex(realFilename);
        System.out.println(realFilename);
        System.out.println(md5);
        File file = new File("d:/Projects/practice/test-springboot/src/main/resources/file/fragment/" + md5);
        File[] files = file.listFiles();
        int num = files.length;
        System.out.println(num);

        byte[] bytes = new byte[5 * 1024];

        // 合并分片
        for (int i = 0; i < num; i++) {
            File iFile = new File(file, String.valueOf(i));
            // 将每一个分片文件包装为缓冲流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(iFile));
            int len = 0;
            // 将分片文件包装的流写入RandomAccessFile
            while ((len = bis.read(bytes)) != -1) {
                raf.write(bytes, 0, len);
            }
            bis.close();
        }

        // 删除分片所在文件夹的分片文件
        for (File tmpFile : files) {
            tmpFile.delete();
        }
        // 删除分片所在文件夹
        file.delete();

        raf.close();

        HashMap<String, Object> data = new HashMap<>();
        data.put("data", "ok");
        return data;
    }
*/

    @PostMapping("/updateWorks")
    // 更新一条记录
    public String updateWorks(@RequestBody Works works){
        // 更新影音的信息
        worksService.updateWorks(works);
        return "更新成功!!";
    }

    // 删除一条记录
    @PostMapping("/delete")
    public String deleteById(@RequestBody Works works){
        worksService.deleteById(works.getId());
        return "删除成功";
    }

    //根据tittle查询
    @GetMapping("/findByTitle")
    public List<Works> findByTitle(String title){
        return worksService.findByTitle(title);
    }

    @GetMapping("/findAllByStatus")
    public List<Works> findAllByStatus(Integer categoryId){
        return worksService.findAllByStatus(categoryId);
    }

    @GetMapping("/findAllById")
    public Works findAllById(@RequestParam Integer wid){
        return worksService.findAllById(wid);
    }

    @GetMapping("/findAllByUserId")
    public List<Works> findAllByUserId(@RequestParam int userId, @RequestParam int categoryId){
        return worksService.findAllByUserId(userId,categoryId);
    }

    @PostMapping("/updateWorksById")
    public ResponseEntity<String> updateWorksById(@RequestParam(value = "file") MultipartFile file ,Works works){
        String fileName_w = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName_w);
        System.out.println(fileExtension);
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileName;
            if(isImageFile(fileExtension)){
                fileName = "images/" + file.getOriginalFilename();

            }
            else if(isVideoFile(fileExtension))
            {
                fileName = "videos/" + file.getOriginalFilename();
            }
            else
            {
                fileName = "audios/" + file.getOriginalFilename();
            }
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            PutObjectResult result = ossClient.putObject(request);

            // 设置URL过期时间为1年 url签名
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365);
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            urlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(urlRequest);
            String fileUrl = url.toString();

            works.setUrl(fileUrl);
            System.out.println(works);
            worksService.updateWorksById(works);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }

    //根据输入的内容查询
    @RequestMapping(value = "/findByTitle/{pageCode}/{pageSize}/{categoryId}",method = RequestMethod.GET)
    public PageInfo<Works> findByTitle(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize,@PathVariable(value = "categoryId") int categoryId, String keyword) {
        System.out.println(pageCode+"...."+pageSize+"....."+keyword+"......."+categoryId);
        PageInfo<Works> pageInfo = worksService.findByTitle(pageCode, pageSize,categoryId,keyword);
        return pageInfo;
    }

    @RequestMapping(value = "/findByTitle0/{pageCode}/{pageSize}/{categoryId}",method = RequestMethod.GET)
    public PageInfo<Works> findByTitle0(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize,@PathVariable(value = "categoryId") int categoryId, String keyword) {
        System.out.println(pageCode+"...."+pageSize+"....."+keyword+"......."+categoryId);
        PageInfo<Works> pageInfo = worksService.findByTitle0(pageCode, pageSize,categoryId,keyword);
        return pageInfo;
    }

    @GetMapping("/findByAll")
    public List<Works> findByAll(@RequestParam String keyword){
        System.out.println(keyword);
        return worksService.findByAll(keyword);
    }
}

