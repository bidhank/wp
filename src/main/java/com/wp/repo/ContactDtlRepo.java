package com.wp.repo;

import com.wp.dto.ContactDtlDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ContactDtlRepo {

    Page<ContactDtlDto> getAll(int pageNo, int pageSize);
     int add(ContactDtlDto dto);
     int update(ContactDtlDto dto);
     boolean delete(int id);
    Optional<ContactDtlDto> getByMobileNumber(String mobileNumber );

    ContactDtlDto searchByName(String name);
}
