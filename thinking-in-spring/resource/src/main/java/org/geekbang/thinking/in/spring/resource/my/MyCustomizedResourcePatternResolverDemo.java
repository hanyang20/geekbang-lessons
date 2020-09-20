package org.geekbang.thinking.in.spring.resource.my;

import org.apache.commons.io.IOUtils;
import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 * @since
 */
public class MyCustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
//        String localPackagPath = System.getProperty("user.dir") + "/resource/src/main/java/org/geekbang/thinking/in/spring/resource/my/";
        String localPackagPath = System.getProperty("user.dir") + "/resource/src/main/resources/META-INF/";
        String localPatterPath = localPackagPath + "*.properties";

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        //重置PathMatcher
        patternResolver.setPathMatcher(new JavaFilePathMatcher());

        for (Resource resource : patternResolver.getResources(localPatterPath)) {
            System.out.println(resource);
            EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

            try (Reader reader = encodedResource.getReader()) {
                System.out.println(IOUtils.toString(reader));
            }
        }
//        Resource[] resources = patternResolver.getResources(localPatterPath);
//        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class JavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

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
