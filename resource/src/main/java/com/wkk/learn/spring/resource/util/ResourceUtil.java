package com.wkk.learn.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @Description {@link Resource} 工具类
 * @Author Wangkunkun
 * @Date 2021/5/20 21:34
 */
public interface ResourceUtil {


    static String getContext(Resource resource) {
        try {
            return getContext(resource, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getContext(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }
}
