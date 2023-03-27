package com.wp.repo;

import com.wp.dto.ContactDtlDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class ContactDtlRepoImpl implements ContactDtlRepo{

    private ConcurrentMap<Integer,ContactDtlDto> contacts = new ConcurrentHashMap<>();

    @Override
    public Page<ContactDtlDto> getAll(int pageNo, int pageSize) {
        List<ContactDtlDto> allContacts = new ArrayList<>(contacts.values());
        int totalElements = allContacts.size();

        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, totalElements);

        List<ContactDtlDto> contactsOnPage = allContacts.subList(start, end);

        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        return new PageImpl<>(contactsOnPage, pageable, totalElements);
    }

    @Override
    public int add(ContactDtlDto dto) {
        int newId=contacts.size()+1;
        dto.setId(newId);
        contacts.put(newId, dto);
        return newId;
    }

    @Override
    public int update(ContactDtlDto dto) {
        contacts.put(dto.getId(),dto);
        return dto.getId();
    }

    @Override
    public boolean delete(int id) {
        contacts.remove(id);
        return true;
    }

    @Override
    public Optional<ContactDtlDto> getByMobileNumber(String mobileNumber) {

        Optional<ContactDtlDto> contact = contacts.values().stream()
                .filter(c -> c.getMobileNumber().equals(mobileNumber))
                .findFirst();

        return contact;
    }

    @Override
    public ContactDtlDto searchByName(String name) {
        try {
            return contacts.values().stream().filter(c -> c.getFullName().equals(name)).findFirst().get();
        }catch (NoSuchElementException e){
            return null;
        }

    }
}
