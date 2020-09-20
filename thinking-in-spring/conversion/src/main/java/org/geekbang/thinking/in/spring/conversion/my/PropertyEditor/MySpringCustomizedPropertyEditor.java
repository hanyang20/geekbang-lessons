package org.geekbang.thinking.in.spring.conversion.my.PropertyEditor;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class MySpringCustomizedPropertyEditor extends PropertyEditorSupport {

    //1.重写setAsText方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //2.把String变为Properties
        Properties properties = new Properties();
        try {
            //3.load Reader流
            properties.load(new StringReader(text));

            //4.设置临时存储值properties
            setValue(properties);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Object, Object> en : properties.entrySet()) {
            stringBuilder.append(en.getKey()).append("=").append(en.getValue());
        }

        return stringBuilder.toString();
    }
}
