package com.wp.dto;

import com.wp.validation.MobileNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class ContactDtlDto {
    private int id;
    @NotBlank(message = "Name cannot be blank")
    private String fullName;
    @Email
    private String emailAddress;
    @MobileNumber
    private String mobileNumber;


    public ContactDtlDto() {
    }

    public ContactDtlDto(int id,String fullName, String emailAddress, String mobileNumber) {
        this.id=id;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactDtl [fullName=" + fullName + ", emailAddress=" + emailAddress + ", mobileNumber=" + mobileNumber + "]";
    }

    @Override
    public boolean equals(Object obj) {
        ContactDtlDto other = (ContactDtlDto) obj;
        return Objects.equals(fullName, other.fullName) && Objects.equals(emailAddress, other.emailAddress)
                && Objects.equals(mobileNumber, other.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, emailAddress, mobileNumber);
    }
}
