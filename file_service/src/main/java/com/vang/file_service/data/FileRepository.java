package com.vang.file_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FileRepository extends JpaRepository<Files, String> {

    @Query(value = "select f.fileid,f.filename,f.fileurl,f.filetype,f.userid,f.userinformation,f.folderid,f.folderinformation,f.status,f.createddate,f.lastmodified from files f where f.userid = ?1 and f.folderid = ?2 and f.status = 1", nativeQuery = true)
    List<Files> findByUserId(String userId, String folderId);

    @Query(value = "select f.fileid,f.filename,f.fileurl,f.filetype,f.userid,f.userinformation,f.folderid,f.folderinformation,f.status,f.createddate,f.lastmodified from files f where f.userid = ?1 and f.status = 0", nativeQuery = true)
    List<Files> findAllByDeleteStatusAndUserId(String userId);

    @Modifying
    @Query(value = "delete from files where folderid = ?1 and userid = ?2 and status = 0", nativeQuery = true)
    int deleteAllByFolderIdAndUserId(String folderId, String userId);

    @Modifying
    @Query(value = "update files set status = ?3 where folderid = ?1 and userid = ?2", nativeQuery = true)
    int updateAllByFolderIdAndUserId(String folderId, String userId, int status);
}