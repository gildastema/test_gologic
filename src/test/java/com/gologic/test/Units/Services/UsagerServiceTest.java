package com.gologic.test.Units.Services;

import com.gologic.test.Models.Address;
import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.UsagerRepository;
import com.gologic.test.Services.UsagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsagerServiceTest {

    @Autowired
    private UsagerRepository usagerRepository;

    @Autowired
    private UsagerService usagerService;

    @BeforeEach
    public void beforeEach()
    {
        usagerRepository.deleteAll();
    }

    @Test
    public void createUsager()
    {
        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, 1 ,
                Arrays.asList(new Address("5555 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X"),
                        new Address("6666 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X")));

        var createUsager = usagerService.createUsager(usager);
          assertThat(createUsager).isNotNull();
          assertThat(createUsager.getId()).isNotNull();
          assertThat(createUsager.getName()).isNotNull();
          assertThat(createUsager.getCredit()).isNotNull();
          assertThat(createUsager.getPhone()).isNotNull();
          assertThat((long) createUsager.getAddresses().size()).isEqualTo(2);

    }

    @Test
    public void findById()
    {

        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, 1 ,
                Arrays.asList(new Address("5555 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X"),
                        new Address("6666 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X")));
      var createUsager = usagerService.createUsager(usager);


      assertThat(usagerService.findById(createUsager.getId()).isPresent()).isTrue();

    }

    @Test
    public void updateCredit()
    {
        var usager = usagerRepository.save(new Usager("john doe", "john@doe.ca", "237691131446", 150 ));
        var newUsager = usagerService.updateCredit(usager, 50);
        assertThat(newUsager.getCredit()).isEqualTo(50);
    }

    @Test
    public void UpdateUsager()
    {
        var usager = usagerRepository.save( new Usager("john doe", "john@doe.ca", "237691131446", 150 ));
        var newUsager =  usagerService.updateUsager(usager,"jane doe", "jane@doe.ca", "237691131445", 200 );

        assertThat(newUsager.getCredit()).isEqualTo(200);
        assertThat(newUsager.getPhone()).isEqualTo("237691131445");
        assertThat(newUsager.getName()).isEqualTo("jane doe");
        assertThat(newUsager.getEmail()).isEqualTo("jane@doe.ca");

    }


}
