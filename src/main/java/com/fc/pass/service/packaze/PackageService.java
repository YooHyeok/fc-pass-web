package com.fc.pass.service.packaze;

import com.fc.pass.repository.packaze.PackageEntity;
import com.fc.pass.repository.packaze.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages() {
        List<PackageEntity> packageEntites = packageRepository.findAllByOrderByPackageName();
        return PackageModelMapper.INSTANCE.map(packageEntites);
    }
}
