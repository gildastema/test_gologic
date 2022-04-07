package com.gologic.test.Features;

import com.gologic.test.Http.Requests.CreateUsagerRequest;
import com.gologic.test.Http.Requests.UpdateCreditRequest;
import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.UsagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate

public class UsagerManageTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UsagerRepository usagerRepository;

    @LocalServerPort
    int randomServerPort;

    private String baseUrl;

    private HttpHeaders headers = new HttpHeaders();



    @BeforeEach
    public void resetData()
    {
        usagerRepository.deleteAll();

        baseUrl =  "http://localhost:"+randomServerPort+"/api/user";


    }

    @Test
    public void create()
    {
        HttpEntity<CreateUsagerRequest> request = new HttpEntity<>(new CreateUsagerRequest("Gildas Tema", "gidas@tema.ca", "237691131446", 30), headers);
        var result =  restTemplate.postForEntity(baseUrl, request, Usager.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody().getName()).isEqualTo("Gildas Tema");

    }

    @Test
    public void get()
    {
        var usager =  usagerRepository.save(new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 20));
        var response  = restTemplate.getForEntity(baseUrl+"/"+usager.getId(), Usager.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(usager.getName());
        assertThat(response.getBody().getPhone()).isEqualTo(usager.getPhone());
        assertThat(response.getBody().getEmail()).isEqualTo(usager.getEmail());
    }


    public void patch()
    {
        var usager =  usagerRepository.save(new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 20));
        var request = new HttpEntity<UpdateCreditRequest>(new UpdateCreditRequest(30));

        var response = restTemplate.exchange(baseUrl+"/"+usager.getId(), HttpMethod.PATCH, request, Usager.class);


    }

    @Test
    public void put(){
        var usager =  usagerRepository.save(new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 20));
        var request = new HttpEntity<CreateUsagerRequest>(new CreateUsagerRequest("Elsa Tema", "elsa@tema.ca", "237691131445", 30));

        var response = restTemplate.exchange(baseUrl+"/"+usager.getId(), HttpMethod.PUT, request, Usager.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Elsa Tema");
        assertThat(response.getBody().getPhone()).isEqualTo("237691131445");
        assertThat(response.getBody().getEmail()).isEqualTo("elsa@tema.ca");
        assertThat(response.getBody().getCredit()).isEqualTo(30);
    }

}
