package com.gologic.test.Services;

import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.AddressRepository;
import com.gologic.test.Repositories.UsagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsagerService {

    private final UsagerRepository usagerRepository;
    private final AddressRepository addressRepository;

    public UsagerService(UsagerRepository usagerRepository, AddressRepository addressRepository) {
        this.usagerRepository = usagerRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Create usager
     *
     *
     * @param usager
     * @return
     */
    public Usager createUsager(Usager usager) {

        var addresses = usager.getAddresses().stream().map(addressRepository::save).collect(Collectors.toList());
        usager.setAddresses(addresses);

        return usagerRepository.save(usager);
    }

    /**
     * Get Usager in DB
     *
     *
     * @param id
     * @return
     */
    public Optional<Usager> findById(int id) {
        return  usagerRepository.findById(id);
    }

    /**
     * Update Credit in Db
     *
     *
     * @param usager
     * @param credit
     * @return
     */
    public Usager updateCredit(Usager usager, int credit) {
        usager.setCredit(credit);
        return  usagerRepository.save(usager);
    }


    /**
     * Update Usager
     *
     *
     * @param usager
     * @param name
     * @param email
     * @param phone
     * @param credit
     * @return
     */
    public Usager updateUsager(Usager usager, String name, String email, String phone, int credit) {
        usager.setName(name);
        usager.setEmail(email);
        usager.setPhone(phone);
        usager.setCredit(credit);
        return  usagerRepository.save(usager);
    }


}
