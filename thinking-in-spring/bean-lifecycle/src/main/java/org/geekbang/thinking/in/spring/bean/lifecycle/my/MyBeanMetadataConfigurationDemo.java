package org.geekbang.thinking.in.spring.bean.lifecycle.my;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
/**
 * Bean 元信息配置示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyBeanMetadataConfigurationDemo {

    //Properties方式
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
//        String location = "classpath:/META-INF/user.properties";
        //默认情况下读取是ASCII码读取,所以我们用UTF-8编码读取会乱码
//        int n = beanDefinitionReader.loadBeanDefinitions(location);

        String location = "/META-INF/user.properties";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int n = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition 数量: "+ n);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = "+user);
    }

    //Xml方式
//    public static void main(String[] args) {
//        // 创建 BeanFactory 容器
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        // XML 配置文件 ClassPath 路径
//        String location = "classpath:/META-INF/dependency-lookup-context.xml";
//        // 加载配置
//        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
//        System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);
//    }
}
