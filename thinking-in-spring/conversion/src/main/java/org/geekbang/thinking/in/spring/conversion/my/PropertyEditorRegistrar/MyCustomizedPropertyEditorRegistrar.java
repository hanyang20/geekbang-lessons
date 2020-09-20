package org.geekbang.thinking.in.spring.conversion.my.PropertyEditorRegistrar;

import org.geekbang.thinking.in.spring.conversion.my.PropertyEditor.MySpringCustomizedPropertyEditor;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Properties;

public class MyCustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(User.class, "context", new MySpringCustomizedPropertyEditor());
    }
}
