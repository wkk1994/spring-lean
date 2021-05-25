package com.wkk.learn.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.Charset;

/**
 * @Description 自定义 {@link URLStreamHandlerFactory} 实现springx协议
 * @Author Wangkunkun
 * @Date 2021/5/26 21:20
 */
public class MySpringXURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private static String PREFIX = "sun.net.www.protocol";
    private static String CUSTOM_PREFIX = "com.wkk.learn.spring.resource";

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        String className = PREFIX + "." + protocol + ".Handler";

        try {
            // 先从默认路径加载，加载不到从自定义路径加载
            Class aClass = Class.forName(className);
            return (URLStreamHandler)aClass.newInstance();
        } catch (ReflectiveOperationException e1) {
            try {
                className = CUSTOM_PREFIX + "." + protocol + ".Handler";
                Class aClass = Class.forName(className);
                return (URLStreamHandler)aClass.newInstance();
            } catch (ReflectiveOperationException e2) {
                throw new InternalError("could not load " + protocol + "system protocol handler", e2);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        URL.setURLStreamHandlerFactory(new MySpringXURLStreamHandlerFactory());
        URL url = new URL("springx:///META-INF/default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
