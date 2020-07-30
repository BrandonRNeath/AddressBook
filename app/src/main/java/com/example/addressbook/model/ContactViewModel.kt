package com.example.addressbook.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.addressbook.db.ContactDatabase
import com.example.addressbook.repo.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @property repository ContactsRepository is the repo to add users contacts to
 * @property contacts LiveData<List<Contact>> the list of the contacts
 * @constructor
 */
class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactsRepository

    // Users contacts
    val contacts: LiveData<List<Contact>>


    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        // Setting repository of the users contacts
        repository = ContactsRepository(contactDao)
        contacts = repository.contacts
    }

    /**
     * Launching coroutine to add the contact to the repository
     */
    fun addContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.addContact(contact)
    }
}
