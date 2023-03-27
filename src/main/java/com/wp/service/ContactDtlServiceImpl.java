package com.wp.service;

import com.wp.dto.ContactDtlDto;
import com.wp.repo.ContactDtlRepo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ContactDtlServiceImpl implements ContactDtlService{


    private final ContactDtlRepo cdr;

    public ContactDtlServiceImpl(ContactDtlRepo cdr) {
        this.cdr = cdr;
    }


    @Override
    public Page<ContactDtlDto> getAll(int pageNo, int pageSize) {
        return cdr.getAll(pageNo, pageSize);
    }

    @Override
    public int add(ContactDtlDto dto) {
        return cdr.add(dto);
    }

    @Override
    public int update(ContactDtlDto dto) {
        return cdr.update(dto);
    }

    @Override
    public boolean delete(int id) {
        return cdr.delete(id);
    }


    @Override
    public ContactDtlDto searchByName(String name) {
        return cdr.searchByName(name);
    }
}
