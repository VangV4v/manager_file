package com.vang.folder_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folders, String> {

    @Query(value = "select count(f.folderId) from folders f where (f.foldername = ?1 or f.foldername like %?3%) and f.userid = ?2", nativeQuery = true)
    long countByFolderName(String folderName, String userId, String folderWithExt);

    @Query(value = "select f.fileinfolder from folders f where f.folder = ?1 and f.userid = ?2", nativeQuery = true)
    int getFileInFolder(String folderName, String userId);

    @Query(value = "select f.folderid,f.foldername,f.folderpath,f.fileinfolder,f.userid,f.userinformation,f.status,f.createddate,f.lastmodified, f.fileintrash from folders f where f.userid = ?1 and f.status = 1", nativeQuery = true)
    List<Folders> findByUserId(String userId);

    @Modifying
    @Query(value = "update folders set fileinfolder = fileinfolder + 1 where folderid = ?1", nativeQuery = true)
    int updateIncreamentFileInFolder(String folderId);

    @Modifying
    @Query(value = "update folders set fileinfolder = fileinfolder - 1, fileintrash = fileintrash + 1 where folderid = ?1", nativeQuery = true)
    int updateDecreamentFileInFolder(String folderId);

    @Modifying
    @Query(value = "update folders set fileintrash = fileintrash - 1 where folderId = ?1", nativeQuery = true)
    int updateTrashByFolderId(String folderId);

    @Modifying
    @Query(value = "delete from folders where folderId = ?1 and status = 0 and fileintrash = 0 and fileinfolder = 0", nativeQuery = true)
    int deleteAllByFolderIdAndUserId(String folderId);

    @Modifying
    @Query(value = "update folders set fileinfolder = fileinfolder + 1, fileintrash = fileintrash - 1 where folderid = ?1", nativeQuery = true)
    int updateFolderRestore(String folderId);

}