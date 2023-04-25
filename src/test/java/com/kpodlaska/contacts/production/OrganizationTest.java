package com.kpodlaska.contacts.production;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @Test
    void getAllFieldsToChange() {
//        //given
//        Organization organization = new Organization("grid","grunwaldzka 472c","333333333");
//        //when
//        organization.ge
    }

    @Test
    void setField() {
    }

    @Test
    void visualizeRecord() {
        //given
        Organization organization = new Organization("grid","Grunwaldzka 472c","333333333");
        //when
        String result = organization.visualizeRecord();
        //then
        assertTrue(result.startsWith("Organization name: grid"));
    }

    @Test
    void getNameForList() {
        //given
        Organization organization = new Organization("grid","Grunwaldzka 472c","333333333");
        //when
        String result = organization.getNameForList();
        //then
        assertEquals("grid",result);
    }

    @Test
    void getRecordAsString() {
        //given
        Organization organization = new Organization("grid","Grunwaldzka 472c","333333333");
        //when
        String result = organization.getRecordAsString();
        //then
        assertEquals("gridgrunwaldzka472c333333333",result);
    }
}