package com.weigh.verification.service.impl;

import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyang
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public FileModel upload(FileEntity fileEntity) {
        return null;
    }

    @Override
    public FileModel check(String hash) {
        return null;
    }

    @Override
    public List<FileModel> getList(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public FileModel getInfo(Integer id) {
        return null;
    }
}
