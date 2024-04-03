package com.fc.pass.service.pass;

import com.fc.pass.controller.admin.BulkPassRequest;
import com.fc.pass.repository.pass.BulkPassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BulkPassModelMapper {
    BulkPassModelMapper INSATNCE = Mappers.getMapper(BulkPassModelMapper.class);

    List<BulkPass> map(List<BulkPassEntity> bulkPassEntities);

    /**
     * Request 값을 바인딩 초기화 하여 Entity를 생성한다. <br/>
     * 초기화 외의 필드의 값들을 따로 초기화 하여 DataBase에 저장할 때 사용한다.
     * @param bulkPassRequest
     * @return
     */
    BulkPassEntity map(BulkPassRequest bulkPassRequest);
}
