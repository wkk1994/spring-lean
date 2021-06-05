package com.wkk.learn.spring.ioc.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动态（更新）资源 {@link MessageSource} 实现
 * <p>
 * 主要步骤：
 * </p>
 * 1.定位Properties资源文件 <p/>
 * 2.初始化Properties对象<p/>
 * 3.实现 AbstractMessageSource#resolveCode 方法<p/>
 * 4.监听资源文件（Java NIO 2 WatchService）<p/>
 * 5.使用线程池处理文件变化<p/>
 * 6.重新装载 Properties 对象<p/>
 * @author Wangkunkun
 * @date 2021/6/5 22:36
 * @see MessageSource
 * @see AbstractMessageSource
 * @see ResourceLoaderAware
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private String resourceFileName = "msg.properties";

    private String resourcePath = "/META-INF/" + resourceFileName;

    private String encoding = "utf-8";

    private ResourceLoader resourceLoader;

    private Resource messagePropertiesResource;

    private Properties properties;

    private ExecutorService executorService;

    public DynamicResourceMessageSource() {
        this.properties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        // 监听文件事件
        onMessagePropertiesChange();
    }

    /**
     * 加载Properties资源
     *
     * @return
     */
    private Properties loadMessageProperties() {
        if (resourceLoader == null) {
            resourceLoader = getResourceLoader();
        }
        messagePropertiesResource = resourceLoader.getResource(resourcePath);
        EncodedResource encodedResource = new EncodedResource(messagePropertiesResource, encoding);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }

    /**
     * 监听文件变更事件
     */
    private void onMessagePropertiesChange() {
        if (messagePropertiesResource.isFile()) {
            try {
                File propertiesResourceFile = this.messagePropertiesResource.getFile();
                Path propertiesResourcePath = propertiesResourceFile.toPath();
                // 获取当前 OS 文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                // 使用当前Properties文件的父目录作为监听事件的来源
                Path dirPath = propertiesResourcePath.getParent();
                dirPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                // 处理资源文件的变化
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 处理资源文件的变化
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                // take 发生阻塞
                WatchKey watchKey = watchService.take();
                try {
                    // watchKey 是否有效
                    if (watchKey.isValid()) {
                        for (WatchEvent<?> pollEvent : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            System.out.println(watchable);
                            // 目录路径（监听的注册目录）
                            Path dirPath = (Path) watchable;
                            // 事件所关联的对象即注册目录的子文件（或子目录）
                            // 事件发生源是相对路径
                            Path fileRelativePath = (Path) pollEvent.context();
                            if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
                                // 处理为绝对路径
                                Path resolve = dirPath.resolve(fileRelativePath);
                                File file = resolve.toFile();
                                // 重新加载Properties资源，并更新properties属性
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized (this.properties) {
                                    this.properties = properties;
                                }
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        // 重置 WatchKey 必须
                        watchKey.reset();
                    }
                }
            }
        });
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        if (StringUtils.hasText(properties.getProperty(code))) {
            return new MessageFormat(properties.getProperty(code), locale);
        }
        return null;
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();

        for (int i = 0; i < 10000; i++) {
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000L);
        }
    }
}
