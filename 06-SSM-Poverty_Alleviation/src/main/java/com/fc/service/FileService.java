package com.fc.service;

import com.fc.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResultVo fileUpload(MultipartFile file, String type);
}
