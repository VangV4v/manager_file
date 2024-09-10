package com.vang.api_gateway.sahred;

public class StoreData {

    private static String instance;

    private StoreData() {
        instance = new String();
    }

    public static void setInstance(String instance) {
        StoreData.instance = instance;
    }

    public static String getInstance() {
        return instance;
    }

}