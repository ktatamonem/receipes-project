package com.mk.backend.assignement.receipes.service.dto.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper <K,T>{

    T fromDtoToEntity(K dto);

    K fromEntityToDto(T entity);

    List<T> fromDtoListToEntityList(List<K> dtoList);

    List<K> fromEntityListToDtoList(List<T> entityList);

    Page<K> mapPage(Page<T> entityPage);

}
