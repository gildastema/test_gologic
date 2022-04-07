package com.gologic.test.Features;

import com.gologic.test.Http.Requests.CreateUsagerRequest;
import com.gologic.test.Http.Requests.UpdateCreditRequest;
import com.gologic.test.Models.Address;
import com.gologic.test.Models.Usager;
import com.gologic.test.Repositories.UsagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

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
        var response =  restTemplate.postForEntity(baseUrl, request, Usager.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Gildas Tema");
        assertThat(response.getBody().getEmail()).isEqualTo("gidas@tema.ca");
        assertThat(response.getBody().getPhone()).isEqualTo("237691131446");
        assertThat(response.getBody().getCredit()).isEqualTo(30);

    }

    @Test
    public void createWithAddress()
    {
        HttpEntity<CreateUsagerRequest> request = new HttpEntity<>(new CreateUsagerRequest("Gildas Tema", "gidas@tema.ca", "237691131446", 30,
                Arrays.asList(new Address("5555 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X"),
                        new Address("6666 Rue DeGaspé", "montreal", "CA", "QC", "X1XX1X"))), headers);
        var response =  restTemplate.postForEntity(baseUrl, request, Usager.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Gildas Tema");
        assertThat(response.getBody().getEmail()).isEqualTo("gidas@tema.ca");
        assertThat(response.getBody().getPhone()).isEqualTo("237691131446");
        assertThat(response.getBody().getCredit()).isEqualTo(30);
        assertThat(response.getBody().getAddresses().size()).isEqualTo(2);
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

    @Test
    public void patch() throws URISyntaxException {
        var usager =  usagerRepository.save(new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 20));
        var request = new HttpEntity<>(new UpdateCreditRequest(30));
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        var TIMEOUT = 30000;
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        restTemplate.getRestTemplate().setRequestFactory(requestFactory);
        var url = new URI(baseUrl+"/"+usager.getId());
        var response = restTemplate.exchange(url, HttpMethod.PATCH, request, Usager.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCredit()).isEqualTo(30);

    }

    @Test
    public void put(){
        var usager =  usagerRepository.save(new Usager("Gildas Tema", "gildas@tema.ca", "237691131446", 20));
        var request = new HttpEntity<>(new CreateUsagerRequest("Elsa Tema", "elsa@tema.ca", "237691131445", 30));

        var response = restTemplate.exchange(baseUrl+"/"+usager.getId(), HttpMethod.PUT, request, Usager.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Elsa Tema");
        assertThat(response.getBody().getPhone()).isEqualTo("237691131445");
        assertThat(response.getBody().getEmail()).isEqualTo("elsa@tema.ca");
        assertThat(response.getBody().getCredit()).isEqualTo(30);
    }

}
