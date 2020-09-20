package org.geekbang.thinking.in.spring.conversion.my.PropertyEditor;

import java.beans.PropertyEditor;

public class MyPropertyEditorDemo {

    public static void main(String[] args) {
        String text = "name=小马哥";

        PropertyEditor propertyEditor = new MySpringCustomizedPropertyEditor();
        propertyEditor.setAsText(text);

        Object value = propertyEditor.getValue();
        System.out.println(value);
        System.out.println(propertyEditor.getAsText());
    }
}
