package com.fc.pass.service.pass;

import com.fc.pass.repository.pass.PassEntity;
import com.fc.pass.repository.pass.PassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<Pass> getPasses(final String userId) {
        final List<PassEntity> passEntites = passRepository.findByUserId(userId);
        for (PassEntity passEntite : passEntites) {
            System.out.println("passEntite = " + passEntite);
        }
        return PassModelMapper.INSTANCE.map(passEntites);
    }
}
