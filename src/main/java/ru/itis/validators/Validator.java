package ru.itis.validators;

import java.util.List;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface Validator<T> {
    List<String> validate(T model);
}
