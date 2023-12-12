package com.renewr.collection.mapper;

import com.renewr.collection.Entity.DataCollection;
import com.renewr.collection.dto.DataCollectionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DataCollectionMapper {
    List<DataCollectionDto.listResponse> dataCollectionToDataCollectionDto(List<DataCollection> dataCollections);
}
