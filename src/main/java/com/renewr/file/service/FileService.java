package com.renewr.file.service;

import com.renewr.file.entity.File;
import com.renewr.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(File file) {
        fileRepository.save(file);
    }

    public void delete(File file) {
        fileRepository.delete(file);
    }
}
