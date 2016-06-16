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

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public interface ContactSearch {

    /**
     * Search contact by {@link Contact#firstName}.
     * @param firstName 
     */
    List<Contact> findByFirstName(String firstName);

    /**
     * Search contact by {@link Contact#middleName}.
     * @param middleName 
     */
    List<Contact>  findByMiddleName(String middleName);

    /**
     * Search contact by {@link Contact#lastName}.
     * @param lastName 
     */
    List<Contact> findByLastName(String lastName);

    /**
     * Search contact by {@link Contact#phoneType}.
     * @param phoneType 
     */
    List<Contact> findByPhoneType(String phoneType);

    /**
     * Search contact by {@link Contact#phoneNumber}.
     * @param phoneNumber 
     */
    List<Contact> findByPhoneNumber(String phoneNumber);

}
