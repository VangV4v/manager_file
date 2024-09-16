package com.vang.folder_service.command.sharedata;

import com.vang.folder_service.command.model.FolderResponseModel;

public class SharedData {

    private static FolderResponseModel instance = null;

    private SharedData() {

    }

    public static FolderResponseModel getInstance() {

        return instance;
    }

    public static void setInstance(FolderResponseModel instance) {
        SharedData.instance = instance;
    }
}