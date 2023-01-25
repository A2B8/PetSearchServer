package com.base.PetSearchServer.repository;

import com.base.PetSearchServer.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String filename);
}
