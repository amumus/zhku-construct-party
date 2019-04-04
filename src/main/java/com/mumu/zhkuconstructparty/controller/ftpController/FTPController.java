package com.mumu.zhkuconstructparty.controller.ftpController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mumu.zhkuconstructparty.bean.FtpConfig;
import com.mumu.zhkuconstructparty.common.ResultObject;
import com.mumu.zhkuconstructparty.util.FtpUtil;
import com.mumu.zhkuconstructparty.util.JsonUtils;
import com.mumu.zhkuconstructparty.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FTPController {
	@Autowired
	private FtpConfig ftpConfig;
	/**
	 * 上传多个图片
	 * @param files
	 * @return
	 */
	@RequestMapping("/portal/uploadFiles")
	@ResponseBody
	public ResultObject uploadFiles( MultipartFile[] files) {
		List<String> imageURLs = new ArrayList<>();
		try {
			// 循环上传多个图片
			for (MultipartFile file : files) {
				String oldName = file.getOriginalFilename();
				String picNewName = UploadUtils.generateRandonFileName(oldName);// 通过工具类产生新图片名称，防止重名
				String picSavePath = UploadUtils.generateRandomDir(picNewName);// 通过工具类把图片目录分级

				// 上传到图片服务器的操作
				imageURLs.add(FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResultObject.failResult();
		}
		// 添加到数据库
		// iPhotoService.savePhotoList(photoList);//调用service层方法
		ResultObject result = ResultObject.successResult();
		Map m = new HashMap<>();
		m.put("url", imageURLs);
		result.setData(m);
		return result;// 上传成功做的操作,我这里返回上传成功的信号
	}

	/**
	 * 上传单个图片
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/portal/uploadImage")
	@ResponseBody
	public String uploadImage(MultipartFile file) {
		String imageURL = "";
		try {
			String oldName = file.getOriginalFilename();
			String picNewName = UploadUtils.generateRandonFileName(oldName);// 通过工具类产生新图片名称，防止重名
			String picSavePath = UploadUtils.generateRandomDir(picNewName);// 通过工具类把图片目录分级
			// 上传到图片服务器的操作
			imageURL = FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message","上传出错啦！");
			return JsonUtils.beanToJson(result);
		}
		Map result = new HashMap<>();
		result.put("error", 0);
		result.put("url",imageURL);
		return JsonUtils.beanToJson(result);
	}

	/**
	 * 上传单个图片
	 * @return
	 */
	@RequestMapping("/portal/uploadImage2")
	@ResponseBody
	public String uploadImage2(HttpServletRequest request) {
//		MultipartFile file =  new MockMultipartFile("temp.jpg","temp.jpg","", request.getPart("img").getInputStream());
		StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
		List list =  multipartHttpServletRequest.getMultiFileMap().get("img");MultipartFile file = (MultipartFile) list.get(0);
		String imageURL = "";
		try {
			String oldName = file.getOriginalFilename();
			String picNewName = UploadUtils.generateRandonFileName(oldName);// 通过工具类产生新图片名称，防止重名
			String picSavePath = UploadUtils.generateRandomDir(picNewName);// 通过工具类把图片目录分级
			// 上传到图片服务器的操作
			imageURL = FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message","上传出错啦！");
			return JsonUtils.beanToJson(result);
		}
		Map result = new HashMap<>();
		result.put("error", 0);
		result.put("url",imageURL);
		return JsonUtils.beanToJson(result);
	}
}