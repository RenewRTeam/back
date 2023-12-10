package com.renewr.Collection.mapper;

import com.renewr.Collection.Entity.DataCollection;
import com.renewr.Collection.dto.DataCollectionDto;
import org.mapstruct.Mapper;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DataCollectionMapper {
    List<DataCollectionDto.listResponse> dataCollectionToDataCollectionDto(List<DataCollection> dataCollections);
}
