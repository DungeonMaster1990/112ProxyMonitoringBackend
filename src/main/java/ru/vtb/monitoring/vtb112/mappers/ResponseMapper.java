package ru.vtb.monitoring.vtb112.mappers;

public interface ResponseMapper<T, K> {
    T mapToResponse(K model);
}
