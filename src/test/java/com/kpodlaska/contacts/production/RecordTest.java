package com.kpodlaska.contacts.production;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.kpodlaska.contacts.production.Record.checkValidityOfPhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    @Test
    void getAllFieldsToChange() {
    }


    @Test
    void getName() {
        //given
        Person person = new Person("karolina", "", "Podlaska", 'f', "03-03-1980");
        //when
        String result = person.getName();
        //then
        assertEquals("karolina", result);
    }

    @Test
    void setName() {
        //given
        Person person = new Person("karolina", "", "Podlaska", 'f', "03-03-1980");
        String name = "Paulina";
        //when
        person.setName(name);
        String result = person.getName();
        //then
        assertEquals("Paulina", result);

    }

    @Test
    void setPhoneNumber() {
        //given
        Person person = new Person("karolina", "", "Podlaska", 'f', "03-03-1980");
        String phone_number = "123456789";
        //when
        person.setPhoneNumber(phone_number);
        String result = person.getPhoneNumber();
        //then
        assertEquals("123456789", result);
    }

    @Test
    void getPhoneNumber() {
        //given
        Person person = new Person("karolina", "222000444", "Podlaska", 'f', "03-03-1980");
        //when
        String result = person.getPhoneNumber();
        //then
        assertEquals("222000444", result);
    }

    @Test
    void setLastEdit() {
        //given
        Person person = new Person("karolina", "222000444", "Podlaska", 'f', "03-03-1980");
        //when
        person.setLastEdit();
        LocalDateTime result = person.getLastEdit();
        person.setLastEdit();
        LocalDateTime result2 = person.getLastEdit();

        //then
        assertNotNull(result);
        assertNotEquals(result2, result);


    }

    @Test
    void getLastEdit() {
        //given
        Person person = new Person("karolina", "222000444", "Podlaska", 'f', "03-03-1980");
        //when
        person.setLastEdit();
        LocalDateTime result = person.getLastEdit();
        person.setLastEdit();
        LocalDateTime result2 = person.getLastEdit();
        //then
        assertNotNull(result);
        assertTrue(result2.isAfter(result));
    }


    @Test
    void isEmptyForPerson_if_phoneNumberIsEmpty_True() {
        //given
        Person person = new Person("karolina", "", "Podlaska", 'f', "03-03-1980");
        //when
        boolean result = person.isEmpty();
        //then
        assertTrue(result);

    }

    @Test
    void isEmptyForOrganization_if_phoneNumberIsEmpty_True() {
        //given
        Organization organization = new Organization("Test", "Sobieskiego 80, 80-300 Torun", "");
        //when
        boolean result = organization.isEmpty();
        //then
        assertTrue(result);

    }

    @Test
    void checkValidityOfPhoneNumber_isWorking() {
        //given
        String phoneNumber = "123456789";
        //when
        boolean result = checkValidityOfPhoneNumber(phoneNumber);
        //then
        assertTrue(result);
    }

    @Test
    void checkValidityOfPhoneNumber_isNotWorking() {
        //given
        String phoneNumber = "pupa";
        //when
        boolean result = checkValidityOfPhoneNumber(phoneNumber);
        //then
        assertFalse(result);
    }

    @Test
    void visualizeRecord_twoRegularWords_oneCombinedLowercaseWord() {
        //given
        Person person = new Person("Karolina", "", "Podlaska", 'f', "03-03-1980");
        //then
        String result = person.getRecordAsString();
        //when
        assertEquals("karolinapodlaska", result);
    }

    @Test
    void visualizeRecord_twoUPPERCASEWords_oneCombinedLowercaseWord() {
        //given
        Person person = new Person("KAROLINA", "", "PODLASKA", 'f', "03-03-1980");
        //then
        String result = person.getRecordAsString();
        //when
        assertEquals("karolinapodlaska", result);
    }

    @Test
    void getNameForList() {
        //given
        Person person = new Person("KAROLINA", "", "PODLASKA", 'f', "03-03-1980");
        //when
        String result = person.getNameForList();
        //then
        assertEquals("KAROLINA PODLASKA", result);

    }

    @Test
    void getRecordAsStringForPerson_oneLowerCaseString() {
        //given
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        //when
        String result = person.getRecordAsString();
        //then
        assertEquals("testkowalski", result);
    }

    @Test
    void getAllFieldsToChangeForPerson_Success() {
        //given
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        //when
        String[] result = person.getAllFieldsToChange();
        List<String> expectedList = Arrays.asList(result);
        //given
        String[] given = new String[]{"surname", "birth", "gender"};
        List<String> givenList = Arrays.asList(given);
        assertEquals(givenList, expectedList);
    }


    @Test
    void getAllFieldsToChangeForOrganization_Success() {
        //given
        Organization organization = new Organization("Test", "Sobieskiego 80, 80-300 Torun", "");
        //when
        String[] result = organization.getAllFieldsToChange();
        List<String> expectedList = Arrays.asList(result);
        //given
        String[] given = new String[]{"address"};
        List<String> givenList = Arrays.asList(given);
        assertEquals(givenList, expectedList);
    }

    @Test
    public void setFieldworkPersonSetName_updatedName() {
        //given
        Person person = new Person("test", "111-111-111", "kowalski", 'f', "03-03-1980");
        //when
        person.setField("name", "Paulina");
        //then
        assertEquals("Paulina", person.getName());

    }
}