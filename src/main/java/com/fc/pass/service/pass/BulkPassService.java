package com.fc.pass.service.pass;

import com.fc.pass.controller.admin.BulkPassRequest;
import com.fc.pass.repository.packaze.PackageEntity;
import com.fc.pass.repository.packaze.PackageRepository;
import com.fc.pass.repository.pass.BulkPassEntity;
import com.fc.pass.repository.pass.BulkPassRepository;
import com.fc.pass.repository.pass.BulkPassStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BulkPassService {
    private final BulkPassRepository bulkPassRepository;
    private final PackageRepository packageRepository;

    public BulkPassService(BulkPassRepository bulkPassRepository, PackageRepository packageRepository) {
        this.bulkPassRepository = bulkPassRepository;
        this.packageRepository = packageRepository;
    }

    @Transactional(readOnly = true)
    public List<BulkPass> getAllBulkPasses() {
        List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findAllByOrderByStartedAtDesc();
        return BulkPassModelMapper.INSATNCE.map(bulkPassEntities);
    }

    public void addBulkPass(BulkPassRequest bulkPassRequest) {
        PackageEntity packageEntity = packageRepository.findById(bulkPassRequest.getPackageSeq()).orElseThrow();
        BulkPassEntity bulkPassEntity = BulkPassModelMapper.INSATNCE.map(bulkPassRequest);
        bulkPassEntity.setStatus(BulkPassStatus.READY);
        bulkPassEntity.setCount(packageEntity.getCount()); //이용권 정보의 Count횟수를 초기값으로 저장
        bulkPassEntity.setEndedAt(packageEntity.getPeriod());//이용권 정보의 만료일자를 초기값으로 저장
        bulkPassRepository.save(bulkPassEntity);
    }
}
