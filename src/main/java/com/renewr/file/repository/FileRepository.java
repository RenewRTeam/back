package com.renewr.file.repository;

import com.renewr.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
    File findByCollectId(Long id);
}
