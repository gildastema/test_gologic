package com.gologic.test.Units.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gologic.test.Http.Controllers.UsagerController;
import com.gologic.test.Http.Requests.CreateUsagerRequest;
import com.gologic.test.Http.Requests.UpdateCreditRequest;
import com.gologic.test.Models.Usager;
import com.gologic.test.Services.UsagerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UsagerController.class)
public class UsagerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsagerService usagerService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createUsagerOk() throws Exception
    {
        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, 1);
        Mockito.when(usagerService.createUsager(ArgumentMatchers.any(Usager.class))  )
                .thenReturn(  usager);
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString( new CreateUsagerRequest("john doe", "john@doe.ca", "237691131446", 150)))

        ).andExpect(status().is(HttpStatus.CREATED.value()))
          .andExpect(content().json(mapper.writeValueAsString(usager)));
    }


    @SneakyThrows
    @Test
    public void getUserOK()
    {
        var id = 1;
        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, id);

        Mockito.when(usagerService.findById(id)).thenReturn(Optional.of(usager));

        mockMvc.perform(
                get("/api/user/"+id).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(usager)));


    }

    @SneakyThrows
    @Test
    public void  updateCreditOk()
    {
        var id = 1;
        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, id);
        var responseUsager = new Usager("john doe", "john@doe.ca", "237691131446", 30, id);


        Mockito.when(usagerService.findById(id)).thenReturn(Optional.of(usager));
        Mockito.when(usagerService.updateCredit(usager, 30)).thenReturn(responseUsager);


        mockMvc.perform(
                patch("/api/user/"+id)
                         .contentType(MediaType.APPLICATION_JSON)
                         .content( mapper.writeValueAsString( new UpdateCreditRequest(30)) )
        ).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(responseUsager)));
    }

    @SneakyThrows
    @Test
    public void updateUsagerOk()
    {
        var id = 1;
        var usager = new Usager("john doe", "john@doe.ca", "237691131446", 150, id);
        var responseUsager = new Usager("gildas Tema", "gildas@tema.ca", "237651881356", 30, id);


        Mockito.when(usagerService.findById(id)).thenReturn(Optional.of(usager));
        Mockito.when(usagerService.updateUsager(usager, "gildas Tema", "gildas@tema.ca", "237651881356", 30)).thenReturn(responseUsager);

        mockMvc.perform(
                put("/api/user/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString( new CreateUsagerRequest("gildas Tema", "gildas@tema.ca", "237651881356", 30) ))
        ).andExpect(status().isOk())
         .andExpect(content().json(mapper.writeValueAsString(responseUsager)));
    }
    
}
