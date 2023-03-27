package com.wp.controller.rest;

import com.wp.dto.ContactDtlDto;
import com.wp.service.ContactDtlService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/contacts")
public class ContactDtlController {

    private final ContactDtlService cds;

    public ContactDtlController(ContactDtlService cds) {
        this.cds = cds;
    }


    @GetMapping()
    public ResponseEntity<Page<ContactDtlDto>> getAllContacts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                       @RequestParam(defaultValue = "1") Integer pageSize) {
        Page<ContactDtlDto> contacts = cds.getAll(pageNo, pageSize);
        return ResponseEntity.ok().body(contacts);
    }

    @PutMapping()
    public ResponseEntity<Integer> updateContact(@RequestBody ContactDtlDto cdd){
        return cds.update(cdd)>0?ResponseEntity.ok().body(cdd.getId()):ResponseEntity.badRequest().build();
    }

    @PostMapping()
    public ResponseEntity<Object> createContact(@RequestBody @Valid ContactDtlDto cdd){
        return cds.add(cdd)>0?ResponseEntity.ok().build():ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable int id){
        return cds.delete(id)?ResponseEntity.ok().body(id):ResponseEntity.badRequest().body("Invalid client request");
    }

    @GetMapping("/search")
    public ResponseEntity<ContactDtlDto> searchContactsByName(@RequestParam String name) {
        ContactDtlDto contacts = cds.searchByName(name);
        return contacts==null?ResponseEntity.notFound().build(): ResponseEntity.ok().body(contacts);
    }
}
