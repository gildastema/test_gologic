package com.gologic.test.Services;

import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.UsagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsagerService {

    private final UsagerRepository usagerRepository;

    public UsagerService(UsagerRepository usagerRepository) {
        this.usagerRepository = usagerRepository;
    }

    public Usager createUsager(Usager usager) {
        return usagerRepository.save(usager);
    }

    public Optional<Usager> findById(int id) {
        return  usagerRepository.findById(id);
    }

    public Usager updateCredit(Usager usager, int credit) {
        usager.setCredit(credit);
        return  usagerRepository.save(usager);
    }



    public Usager updateUsager(Usager usager, String name, String email, String phone, int credit) {
        usager.setName(name);
        usager.setEmail(email);
        usager.setPhone(phone);
        usager.setCredit(credit);
        return  usagerRepository.save(usager);
    }
}
