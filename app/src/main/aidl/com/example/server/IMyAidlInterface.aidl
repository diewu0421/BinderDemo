// IMyAidlInterface.aidl
package com.example.server;

// Declare any non-default types here with import statements
import com.example.server.Person;

interface IMyAidlInterface {

    void addPerson(in Person person);

    List<Person> getPersonList();
}