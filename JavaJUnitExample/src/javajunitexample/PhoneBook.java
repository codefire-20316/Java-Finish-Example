/*
 * Copyright (C) 2016 CodeFireUA <edu@codefire.com.ua>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package javajunitexample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public class PhoneBook implements Iterable<Contact>, ContactSearch {

    private List<Contact> contactList;

    public PhoneBook() {
        this.contactList = new ArrayList<>();
    }

    public boolean add(Contact contact) {
        return contactList.add(contact);
    }

    @Override
    public Iterator<Contact> iterator() {
        return contactList.iterator();
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        String searchTerm = firstName.trim().toLowerCase();
        List<Contact> foundContacts = new ArrayList<>();
        
        for (Contact contact : contactList) {
            if (contact.getFirstName().toLowerCase().contains(searchTerm)) {
                foundContacts.add(contact);
            }
        }
        
        return foundContacts;
    }

    @Override
    public List<Contact> findByMiddleName(String middleName) {
        String searchTerm = middleName.trim().toLowerCase();
        List<Contact> foundContacts = new ArrayList<>();
        
        for (Contact contact : contactList) {
            if (contact.getMiddleName().toLowerCase().contains(searchTerm)) {
                foundContacts.add(contact);
            }
        }
        
        return foundContacts;
    }

    @Override
    public List<Contact> findByLastName(String lastName) {
        String searchTerm = lastName.trim().toLowerCase();
        List<Contact> foundContacts = new ArrayList<>();
        
        for (Contact contact : contactList) {
            if (contact.getLastName().toLowerCase().contains(searchTerm)) {
                foundContacts.add(contact);
            }
        }
        
        return foundContacts;
    }

    @Override
    public List<Contact> findByPhoneType(String phoneType) {
        List<Contact> foundContacts = new ArrayList<>();
        
        for (Contact contact : contactList) {
            if (contact.getPhoneType().equalsIgnoreCase(phoneType)) {
                foundContacts.add(contact);
            }
        }
        
        return foundContacts;
    }

    @Override
    public List<Contact> findByPhoneNumber(String phoneNumber) {
        String searchTerm = phoneNumber.trim();
        List<Contact> foundContacts = new ArrayList<>();
        
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber().contains(searchTerm)) {
                foundContacts.add(contact);
            }
        }
        
        return foundContacts;
    }

}
