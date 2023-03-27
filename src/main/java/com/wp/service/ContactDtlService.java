package com.wp.service;

import com.wp.dto.ContactDtlDto;
import org.springframework.data.domain.Page;



public interface ContactDtlService {
    Page<ContactDtlDto> getAll(int pageNo, int pageSize);
    int add(ContactDtlDto dto);
    int update(ContactDtlDto dto);
    boolean delete(int id);

    ContactDtlDto searchByName(String name);
}
