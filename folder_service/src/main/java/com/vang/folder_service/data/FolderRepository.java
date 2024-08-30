package com.vang.folder_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<Folders, String> {

    @Query(value = "select count(f.foldername) from folders f where f.foldername = ?1 and f.userid = ?2", nativeQuery = true)
    long countByFolderName(String folderName, String userId);

    @Query(value = "select f.fileinfolder from folders f where f.folder = ?1 and f.userid = ?2", nativeQuery = true)
    int getFileInFolder(String folderName, String userId);
}