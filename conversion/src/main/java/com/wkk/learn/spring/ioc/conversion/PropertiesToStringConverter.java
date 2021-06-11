package com.wkk.learn.spring.ioc.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * {@link Properties } -> String
 * @author Wangkunkun
 * @date 2021/6/11 17:00
 * @see ConditionalGenericConverter
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {


    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType())
                && String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Properties properties = (Properties) source;
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            text.append(entry.getKey()).append("=").append(entry.getValue()).append(System.lineSeparator());
        }
        return text.toString();
    }
}
