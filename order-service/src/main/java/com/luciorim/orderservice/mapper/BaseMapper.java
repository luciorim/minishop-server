package com.luciorim.orderservice.mapper;

public interface BaseMapper<E, D> {

    D toDto(E e);

}
