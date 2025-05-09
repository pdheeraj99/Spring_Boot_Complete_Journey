package com.springbootquest.basic_crud_project_1.util;

import java.util.UUID;

public class IDGeneratorUtil {

    public static String generateId() {
        String id = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        return id;
    }

}
