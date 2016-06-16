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

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public class PhoneBookTest {
    
    PhoneBook phoneBook;
    
    @Before
    public void setUp() {
        phoneBook = new PhoneBook();
        phoneBook.add(new Contact("Vasya", "Vasilievich", "Pupkin", "Mobile", "+380 (67) 321-45-67"));
        phoneBook.add(new Contact("Vasya", "Vasilievich", "Pupkin", "Office", "+380 (44) 321-12-12"));
        phoneBook.add(new Contact("Petya", "Vasilievich", "Kopkin", "Office", "+380 (93) 214-54-17"));
        phoneBook.add(new Contact("Gosha", "Vasilievich", "Pupkin", "Mobile", "+380 (67) 123-32-12"));
        phoneBook.add(new Contact("Mitya", "Vasilievich", "Tupkin", "Home", "+380 (44) 872-55-11"));
        phoneBook.add(new Contact("Senya", "Vasilievich", "Pupkin", "Mobile", "+380 (67) 132-23-09"));
    }
    
    @After
    public void tearDown() {
        phoneBook = null;
    }

    @Test
    public void testFindByFirstName() {
        
        List<Contact> foundContact = phoneBook.findByFirstName("Vasya");
        
        assertEquals(2, foundContact.size());
        
    }

    @Test
    public void testFindByFirstNameIgnoreCase() {
        
        List<Contact> foundContact = phoneBook.findByFirstName("vasya");
        
        assertEquals(2, foundContact.size());
        
    }

    @Test
    public void testFindByFirstNamePartial() {
        
        List<Contact> foundContact = phoneBook.findByFirstName("ya");
        
        assertEquals(5, foundContact.size());
        
    }

    @Test
    public void testFindByFirstNameWithinWitespace() {
        
        List<Contact> foundContact = phoneBook.findByFirstName("  Vasya  ");
        
        assertEquals(2, foundContact.size());
        
    }

    @Test
    public void testFindByFirstNameWithinNewLine() {
        
        List<Contact> foundContact = phoneBook.findByFirstName("\nVasya\n");
        
        assertEquals(2, foundContact.size());
        
    }
    
}
