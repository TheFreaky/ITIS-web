package ru.itis.utils;

/**
 * 11.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface Converter<T1, T2> {
    T2 convertToEntity(T1 dto);
    T1 convertToDto(T2 entity);
}
