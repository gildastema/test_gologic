package com.gologic.test.Units.Repositories;

import com.gologic.test.Models.Address;
import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.UsagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UsagerRepositoyTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsagerRepository usagerRepository;

    @Test
    public void findByIdOk()
    {
        var usager = new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 200);
        var address1 = new Address("5555 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X");
        var address2 = new Address("6666 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X");
        entityManager.persist(address1);
        entityManager.persist(address2);
        usager.setAddresses(Arrays.asList(address1, address2));
        entityManager.persist(usager);

        entityManager.flush();

        var usagerFound = usagerRepository.findById(1);
        assertThat(usagerFound.isPresent()).isTrue();
        assertThat(usagerFound.get().getName() ).isEqualTo(usager.getName());
        assertThat(usagerFound.get().getEmail() ).isEqualTo(usager.getEmail());
        assertThat(usagerFound.get().getPhone() ).isEqualTo(usager.getPhone());
        assertThat(usagerFound.get().getCredit() ).isEqualTo(usager.getCredit());
        assertThat(usagerFound.get().getAddresses().size()).isEqualTo(2);
    }

}
