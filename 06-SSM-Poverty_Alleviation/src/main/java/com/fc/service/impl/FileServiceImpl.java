package com.fc.service.impl;

import com.fc.service.FileService;
import com.fc.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public ResultVo fileUpload(MultipartFile file, String type) {
        //上传路径
        String path = "D:/server/apache-tomcat-8.5.37/webapps/upload/Poverty-Alleviation/" + type;

        File pathFile = new File(path);

        //如果路径不存在
        if (!pathFile.exists()) {
            //创建多级路径
            pathFile.mkdirs();
        }

        //获取文件名
        String fileName = file.getOriginalFilename();

        //获取格式化器
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        //获取格式化后的日期格式字符串
        String formatDate = formatter.format(new Date());

        //获取文件后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        //获取新的文件名
        fileName = formatDate + suffix;

        ResultVo resultVo = new ResultVo();

        try {
            //文件上传操作
            file.transferTo(new File(pathFile,fileName));

            resultVo.setCode(200);
            resultVo.setMessage("文件上传成功");
            resultVo.setSuccess(true);

            HashMap<String, Object> map = new HashMap<>();
            map.put("imgurl", path + fileName);

            resultVo.setData(map);
        } catch (IOException e) {
            e.printStackTrace();
            resultVo.setCode(-1000);
            resultVo.setMessage("文件上传失败");
            resultVo.setSuccess(false);
            resultVo.setData(null);
        }
        return resultVo;
    }
}
