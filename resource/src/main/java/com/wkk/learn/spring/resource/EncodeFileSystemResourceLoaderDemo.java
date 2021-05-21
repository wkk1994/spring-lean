package com.wkk.learn.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @Description 通过 {@link FileSystemResourceLoader} 加载 {@link FileSystemResource } 示例
 * @Author Wangkunkun
 * @Date 2021/5/20 20:08
 */
public class EncodeFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String filePath = File.separator + System.getProperty("user.dir") + "/resource/src/main/java/com/wkk/learn/spring/resource/EncodeFileSystemResourceDemo.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(filePath);

        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
