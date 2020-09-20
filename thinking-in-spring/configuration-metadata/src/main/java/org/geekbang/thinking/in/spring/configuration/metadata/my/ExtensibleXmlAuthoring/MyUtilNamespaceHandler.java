package org.geekbang.thinking.in.spring.configuration.metadata.my.ExtensibleXmlAuthoring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyUtilNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserSimpleBeanDefinitionParser());
    }
}
