package org.geekbang.thinking.in.spring.i18n.my;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
/**
 * 动态（更新）资源 {@link MessageSource} 实现
 * <p>
 * 实现步骤：
 * <p>
 * 1. 定位资源位置（ Properties 文件）
 * 2. 初始化 Properties 对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4. 监听资源文件（Java NIO 2 WatchService）
 * 5. 使用线程池处理文件变化
 * 6. 重新装载 Properties 对象
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MessageSource
 * @see AbstractMessageSource
 * @since
 */
public class MyDynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware{

    private static final String localpath = "/META-INF/msg.properties";

    private static final String ENCODING = "UTF-8";

    private  Properties messagesProperties;

    private ResourceLoader resourceLoader;

    public MyDynamicResourceMessageSource() {

        this.messagesProperties = loadMessagesProperties();
    }

    private Properties loadMessagesProperties()  {

        //保证不报空指针
        ResourceLoader resourceLoader = getResourceLoader();
        // 1. 定位资源位置（ Properties 文件）
        Resource resource = resourceLoader.getResource(localpath);
        EncodedResource encodedResource = new EncodedResource(resource, ENCODING);
        // 2. 初始化 Properties 对象
        Properties properties = new Properties();
        try(Reader reader = encodedResource.getReader()) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

     // 3. 实现 AbstractMessageSource#resolveCode 方法
    @Nullable
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messagesProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)){
            return new MessageFormat(messageFormatPattern,locale);
        }

        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        MyDynamicResourceMessageSource dynamicResourceMessageSource = new MyDynamicResourceMessageSource();
        String name = dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.println(name);

    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader != null ? resourceLoader : new DefaultResourceLoader();
    }
}
