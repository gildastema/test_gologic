package com.gologic.test.Repositories;

import com.gologic.test.Models.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsagerRepository extends JpaRepository<Usager, Integer> {

    Optional<Usager> findById(int id);
}
