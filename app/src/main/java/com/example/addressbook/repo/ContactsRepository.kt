package com.example.addressbook.repo

import androidx.lifecycle.LiveData
import com.example.addressbook.dao.ContactDao
import com.example.addressbook.model.Contact

class ContactsRepository(private val contactDao: ContactDao) {

    // List of contacts that is LiveData has is observed which notifies the activity whenever
    // a change is made to the list of contacts
    val contacts: LiveData<List<Contact>> = contactDao.fetchContacts()

    /**
     * Adds the contact to the Data Access Object
     *
     * @param contact Contact that is to be added
     */
    suspend fun addContact(contact: Contact) {
        contactDao.insertContact(contact)
    }
}
