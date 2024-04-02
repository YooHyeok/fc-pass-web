package com.fc.pass.service.pass;

import com.fc.pass.repository.pass.PassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassModelMapper {
    PassModelMapper INSTANCE = Mappers.getMapper(PassModelMapper.class);

    /**
     * packageName 필드는 PassEntity소속이 아닌 PassEntity의 다대일 연관관계인 PackageEntity로부터 매핑해준다.
     * @param passEntity
     * @return
     */
    @Mapping(target = "packageName", source = "packageEntity.packageName")
    Pass map(PassEntity passEntity);

    List<Pass> map(List<PassEntity> passEntities);

}