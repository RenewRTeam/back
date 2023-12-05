package com.renewr.collect.mapper;

import com.renewr.collect.dto.CollectDto;
import com.renewr.collect.entity.Collect;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectMapper {

    Collect collectPostDtoToCollect(CollectDto.Post post);
    Collect collectPatchDtoToCollect(CollectDto.Patch patch);
    CollectDto.Response collectToCollectResponseDto(Collect collect);
    List<CollectDto.listResponse> collectToCollectListResponseDto(List<Collect> collect);
}
