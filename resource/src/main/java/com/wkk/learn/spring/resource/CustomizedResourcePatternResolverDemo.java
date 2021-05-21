package com.wkk.learn.spring.resource;

import com.wkk.learn.spring.resource.util.ResourceUtil;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Description 扩展 {@link ResourcePatternResolver} 示例
 * @Author Wangkunkun
 * @Date 2021/5/20 21:30
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String filePath = "/" +System.getProperty("user.dir") + "/resource/src/main/java/com/wkk/learn/spring/resource/";
        filePath += "*.java";
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        // 设置自定义的PathMatcher
        pathMatchingResourcePatternResolver.setPathMatcher(new ClassFilePathMatcher());

        Resource[] resources = pathMatchingResourcePatternResolver.getResources(filePath);
        Stream.of(resources).map(ResourceUtil::getContext).forEach(System.out::println);
    }

    static class ClassFilePathMatcher implements PathMatcher {

        /**
         * 判断要加载的路径是否是一个通配路径
         * @param path
         * @return
         */
        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        /**
         * 判断当前读取的路径path是否符合通配路径pattern
         * @param pattern
         * @param path
         * @return
         */
        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
