package com.vang.public_store_service.service;

import com.vang.public_store_service.common.ServiceCommon;
import com.vang.public_store_service.data.PublicStoreRepository;
import com.vang.public_store_service.data.PublicStores;
import com.vang.public_store_service.model.PublicStoreModel;
import com.vang.public_store_service.model.UploadImageRequestModel;
import com.vang.public_store_service.model.UploadImageResponseModel;
import io.minio.*;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

@Service
public class PublicStoreServiceImpl implements PublicStoreService {

    private final MinioClient minioClient;
    private final PublicStoreRepository publicStoreRepository;

    @Autowired
    public PublicStoreServiceImpl(MinioClient minioClient, PublicStoreRepository publicStoreRepository) {
        this.minioClient = minioClient;
        this.publicStoreRepository = publicStoreRepository;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<UploadImageResponseModel> uploadImage(UploadImageRequestModel requestModel) {

        UploadImageResponseModel responseModel = new UploadImageResponseModel();
        if (requestModel.getFileData() != null) {

            MultipartFile file = requestModel.getFileData();
            String fileName = (System.currentTimeMillis()) + file.getOriginalFilename();
            String urlImage = ServiceCommon.END_POINT+"/"+ServiceCommon.BUCKET+"/"+fileName;
            PublicStoreModel model = new PublicStoreModel();
            PublicStores storesEntity = new PublicStores();
            //upload image
            PutObjectArgs uploadObject = PutObjectArgs.builder()
                    .bucket(ServiceCommon.BUCKET)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build();
            minioClient.putObject(uploadObject);
            //create data to insert into database
            model.setId((int) (System.currentTimeMillis()+(new Random().nextInt(0, 10))));
            model.setUrl(urlImage);
            model.setCreatedDate(ServiceCommon.getCurrentDate());
            model.setFileName(fileName);
            BeanUtils.copyProperties(model, storesEntity);
            publicStoreRepository.save(storesEntity);
            //create response data
            responseModel.setSuccess(true);
            responseModel.setUrl(urlImage);
            responseModel.setFileName(fileName);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {

            responseModel.setSuccess(false);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}