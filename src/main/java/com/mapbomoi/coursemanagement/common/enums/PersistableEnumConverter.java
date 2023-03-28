package com.mapbomoi.coursemanagement.common.enums;

import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public abstract class PersistableEnumConverter<T extends Enum<T> & PersistableEnum<V>, V> implements AttributeConverter<T, V> {
    private final Class<T> enumClass;

    @Override
    public V convertToDatabaseColumn(T t) {
        return t == null ? null : t.getValue();
    }

    @Override
    public T convertToEntityAttribute(V v) {
        if (v == null) return null;
        return Stream.of(enumClass.getEnumConstants())
                .filter(e -> e.getValue().equals(v))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
