package com.pktech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pktech.entity.User;
import com.pktech.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping("/upload")
	 public Map<String, Object> handleFileUpload(@RequestParam MultipartFile file) {    
			//定义文件保存的本地路径
		Map<String, Object> map=new HashMap<>();
	      	String localPath="D:\\File\\";
        if (!file.isEmpty()) {    
            try {    
                /*   
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到   
                 * d:/files大家是否能实现呢？ 等等;   
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；   
                 * 3、文件格式; 4、文件大小的限制;   
                 */    
            	//生成uuid
            	
            	String uuid=UUID.randomUUID().toString().replaceAll("-", "");
            	//获取图片类型
            	String fileType=file.getContentType();
            	System.out.println("fileType"+fileType+"--------------------");
            	//获取图片后缀名
            	String suffixName=fileType.substring(fileType.indexOf("/")+1);
            	System.out.println("suffixName"+suffixName+"--------------------");
            	//组成新的图片名称
            	String fileName=uuid+"."+suffixName;
            	System.out.println("fileName"+fileName+"--------------------");
            	//保存图片到本地文件
            	System.out.println(localPath+fileName);
            	file.transferTo(new File(localPath+fileName));
            	
            	User user=userService.findUserById("12");
            	
            	user.setImageUrl("/image/"+fileName);
            	
            	userService.addimage(user);
            	
            	map.put("data", user);
            	
                //BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename()))); 
            	
               // System.out.println(file.getName()+"-----------");  
               // out.write(file.getBytes());    
                //out.flush();    
               // out.close();    
            } catch (FileNotFoundException e) {    
                e.printStackTrace();
                map.put("code", "0001");
                map.put("msg", "上传失败," + e.getMessage());
                return map;
            } catch (IOException e) {    
                e.printStackTrace();  
                map.put("code", "0001");
                map.put("msg", "上传失败," + e.getMessage());
                return map;   
            }
            map.put("code", "0000");
            map.put("msg", "上传成功");
            return map;
    
        } else {  
        	map.put("code", "0002");
        	map.put("msg", "上传失败，因为文件是空的.");
            return map;    
        }
	}
	 	@RequestMapping(value = "/imgUpload")
	    public Map<String, Object> imgUpload(HttpServletRequest req, MultipartHttpServletRequest multiReq)
	            throws IOException {
	        //System.out.println("---" + fileUploadPath);//我这里用的springboot 在application.properties中配置，使用@Value 获取的文件上传目录
	 		Map<String, Object> map=new HashMap<>();
	 		String localPath="D:\\File\\";
	        MultipartFile file = multiReq.getFile("file");
	        //String originalFilename = file.getOriginalFilename();
	       // String suffix = originalFilename.substring(originalFilename.indexOf("."));
	        //String localPath = file.getInputStream() + suffix;
	        //File localFile = new File(fileUploadPath + localPath);
	        if (!file.isEmpty()) {    
	            try {    
	                /*   
	                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到   
	                 * d:/files大家是否能实现呢？ 等等;   
	                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；   
	                 * 3、文件格式; 4、文件大小的限制;   
	                 */    
	            	//生成uuid
	            	
	            	String uuid=UUID.randomUUID().toString().replaceAll("-", "");
	            	//获取图片类型
	            	String fileType=file.getContentType();
	            	System.out.println("fileType"+fileType+"--------------------");
	            	//获取图片后缀名
	            	String suffixName=fileType.substring(fileType.indexOf("/")+1);
	            	System.out.println("suffixName"+suffixName+"--------------------");
	            	//组成新的图片名称
	            	String fileName=uuid+"."+suffixName;
	            	System.out.println("fileName"+fileName+"--------------------");
	            	//保存图片到本地文件
	            	System.out.println(localPath+fileName);
	            	file.transferTo(new File(localPath+fileName));
	            	
	            	User user=userService.findUserById("12");
	            	
	            	user.setImageUrl("/image/"+fileName);
	            	
	            	userService.addimage(user);
	            	
	            	map.put("data", user);
	            	
	                //BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename()))); 
	            	
	               // System.out.println(file.getName()+"-----------");  
	               // out.write(file.getBytes());    
	                //out.flush();    
	               // out.close();    
	            } catch (FileNotFoundException e) {    
	                e.printStackTrace();
	                map.put("code", "0001");
	                map.put("msg", "上传失败," + e.getMessage());
	                return map;
	            } catch (IOException e) {    
	                e.printStackTrace();  
	                map.put("code", "0001");
	                map.put("msg", "上传失败," + e.getMessage());
	                return map;   
	            }
	            map.put("code", "0000");
	            map.put("msg", "上传成功");
	            return map;
	    
	        } else {  
	        	map.put("code", "0002");
	        	map.put("msg", "上传失败，因为文件是空的.");
	            return map;    
	        }
	    }
}
