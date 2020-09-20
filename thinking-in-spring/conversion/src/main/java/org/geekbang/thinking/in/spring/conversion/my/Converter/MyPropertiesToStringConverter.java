package org.geekbang.thinking.in.spring.conversion.my.Converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MyPropertiesToStringConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType()) && String.class.equals(targetType.getObjectType());
    }

    @Nullable
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties) source;

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Object, Object> en : properties.entrySet()) {
            stringBuilder.append(en.getKey()).append("=").append(en.getValue());
        }

        return stringBuilder.toString();    }
}
