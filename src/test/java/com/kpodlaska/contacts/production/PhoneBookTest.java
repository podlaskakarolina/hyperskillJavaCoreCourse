package com.kpodlaska.contacts.production;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    @Test
    void addRecordForPerson() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        int valueZero = phoneBook.countRecords();
        phoneBook.addRecord(person);
        int valueOne = phoneBook.countRecords();
        assertEquals(0,valueZero);
        assertEquals(1,valueOne);

    }

    @Test
    void removeRecord() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "1111", "kowalski", 'f', "03-03-1980");
        phoneBook.addRecord(person);
        int startedValue = phoneBook.countRecords();
        phoneBook.removeRecord(1);
        int valueAfterRemove = phoneBook.countRecords();
        assertEquals(1,startedValue);
        assertEquals(0,valueAfterRemove);
    }

    @Test
    void countRecords() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        Person anotherPerson = new Person("test", "", "kowalski", 'f', "03-03-1980");
        int valueZero = phoneBook.countRecords();
        phoneBook.addRecord(person);
        phoneBook.addRecord(anotherPerson);
        int valueTwo = phoneBook.countRecords();
        assertEquals(0,valueZero);
        assertEquals(2,valueTwo);
    }

    @Test
    void getInISTrue() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        assertEquals(true, phoneBook.getIn() instanceof StandardInputHandler);

    }

    @Test
    void setIn() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        phoneBook.setIn(standardInputHandler);
        StandardInputHandler result = phoneBook.getIn();
        assertNotNull(result);
    }

    @Test
    void testToString() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        Person anotherPerson = new Person("testowy", "", "kowalski", 'f', "03-03-1980");
        phoneBook.addRecord(person);
        phoneBook.addRecord(anotherPerson);
        String result = phoneBook.toString();
        assertEquals("1. test kowalski\n" +
                "2. testowy kowalski\n",result);
    }

    @Test
    void getRecordByIndex() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        Person anotherPerson = new Person("testowy", "", "kowalski", 'f', "03-03-1980");
        phoneBook.addRecord(person);
        phoneBook.addRecord(anotherPerson);
        Record result = phoneBook.getRecordByIndex(0);
        assertTrue(person.equals(result));
    }

    @Test
    void getIndexByRecord() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        Person anotherPerson = new Person("testowy", "", "kowalski", 'f', "03-03-1980");
        phoneBook.addRecord(person);
        phoneBook.addRecord(anotherPerson);
        int result = phoneBook.getIndexByRecord(person);
        assertEquals(0,result);
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
        StandardInputHandler standardInputHandler = new StandardInputHandler();
        PhoneBook phoneBook = new PhoneBook(standardInputHandler,"test.txt");
        Person person = new Person("test", "", "kowalski", 'f', "03-03-1980");
        Person anotherPerson = new Person("testowy", "", "kowalski", 'f', "03-03-1980");
        phoneBook.addRecord(person);
        phoneBook.addRecord(anotherPerson);
        phoneBook.remove(1);
        int result = phoneBook.countRecords();
        assertEquals(1,result);
    }

    @Test
    void edit() {

    }



    @Test
    void list() {
    }

    @Test
    void info() {
    }

    @Test
    void search() {
    }
}