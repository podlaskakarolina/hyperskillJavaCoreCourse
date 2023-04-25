package com.kpodlaska.contacts.production;

import org.junit.jupiter.api.Test;

import static com.kpodlaska.contacts.production.Person.checkBirthDate;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

//    @Test
//    void checkBirthDateIsValid() {
//        //given
//        String givenBirthDate = "01.01.1986";
//        //when
//        boolean result = checkBirthDate(givenBirthDate);
//        //then
//        assertEquals(true,result);
//
//
//
//    }

    @Test
    void getSurname() {
        //given
        Person person = new Person("test", "111-111-111", "kowalski", 'f', "03-03-1980");
        //when
        String result = person.getSurname();
        //then
        assertEquals("kowalski",result);
    }



    @Test
    void setSurname() {
        //given
        Person person = new Person("test", "111-111-111", "kowalski", 'f', "03-03-1980");
        //when
        person.setSurname("test");
        String result = person.getSurname();
        //then
        assertEquals("test",result);

    }

    @Test
    void setBirthDate() {
        //given
        Person person = new Person("test", "111-111-111", "kowalski", 'f', "03-03-1980");
        //when
        person.setBirthDate("01-01-2001");
        String result = person.getBirthDate();
        //then
        assertEquals("01-01-2001",result);
    }

    @Test
    void setGender() {
        //given
        Person person = new Person("test", "111-111-111", "kowalski", 'f', "03-03-1980");
        //when
        person.setGender('m');
        char result = person.getGender();
        //then
        assertEquals('m',result);
    }

}