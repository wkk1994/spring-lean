package com.wkk.learn.spring.resource.springx;


import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Description 继承 {@link sun.net.www.protocol.x.Handler} 类，实现springx://协议扩展
 * @Author Wangkunkun
 * @Date 2021/5/26 21:05
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    // 需要在启动前添加 -Djava.protocol.handler.pkgs=com.wkk.learn.spring.resource
    public static void main(String[] args) throws Exception {
        URL url = new URL("springx:///META-INF/default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
