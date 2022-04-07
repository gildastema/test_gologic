package com.gologic.test.Http.Controllers;

import com.gologic.test.Http.Requests.CreateUsagerRequest;
import com.gologic.test.Http.Requests.UpdateCreditRequest;
import com.gologic.test.Models.Usager;
import com.gologic.test.Services.UsagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UsagerController {

    private final UsagerService usagerService;

    public UsagerController(UsagerService usagerService) {
        this.usagerService = usagerService;
    }

    /**
     * Create Usager
     *
     *
     * @param   createUsagerRequest
     * @return usager
     */
    @PostMapping
    public ResponseEntity<Usager> createUsager(@RequestBody  CreateUsagerRequest createUsagerRequest)
    {
       var usager =  usagerService.createUsager( new Usager(createUsagerRequest.getName(), createUsagerRequest.getEmail(),
                                                        createUsagerRequest.getPhone(), createUsagerRequest.getCredit(), createUsagerRequest.getAddresses() ));

        return new ResponseEntity<>(usager, HttpStatus.CREATED);
    }

    /**
     * Retrieve Usage from Id
     *
     *
     * @param id
     * @return usager| Null
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usager> getUsagerById(@PathVariable("id") int id)
    {
        var usager = usagerService.findById(id);
        return usager.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


    /**
     * Update Credit
     *
     *
     * @param id
     * @param  request
     * @return usager | null
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Usager> updateCreditForUsager(@PathVariable("id") int id , @RequestBody UpdateCreditRequest request)
    {
        var usager = usagerService.findById(id);
        return usager.map(value -> ResponseEntity.ok(usagerService.updateCredit(value, request.getCredit()))).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Update Usager
     *
     *
     * @param id
     * @param request
     * @return usager | null
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usager> updateUsager(@PathVariable("id") int id, @RequestBody CreateUsagerRequest request)
    {
        var usager = usagerService.findById(id);
        return usager.map(value -> ResponseEntity.ok(usagerService.updateUsager(value, request.getName(), request.getEmail(), request.getPhone(), request.getCredit())))
                                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
