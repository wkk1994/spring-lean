package com.wkk.learn.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * @Description 带有字符编码的 {@link FileSystemResource} 示例
 * @Author Wangkunkun
 * @Date 2021/5/20 20:08
 */
public class EncodeFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String filePath = System.getProperty("user.dir") + "/resource/src/main/java/com/wkk/learn/spring/resource/EncodeFileSystemResourceDemo.java";
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "utf-8");
        System.out.println(IOUtils.toString(encodedResource.getReader()));
    }
}
