package com.vang.file_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FileRepository extends JpaRepository<Files, String> {

    @Query(value = "select f.fileid,f.filename,f.fileurl,f.filetype,f.userid,f.userinformation,f.folderid,f.folderinformation,f.status,f.createddate,f.lastmodified from files f where f.userid = ?1 and f.folderid = ?2", nativeQuery = true)
    List<Files> findByUserId(String userId, String folderId);
}