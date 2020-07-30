package com.example.addressbook.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.addressbook.model.Contact


@Dao
interface ContactDao {

    /**
     * @return LiveData<List<Contact>> returns the current list of contacts on the users device
     */
    @Query("SELECT * from contacts ORDER BY first_name ASC")
    fun fetchContacts(): LiveData<List<Contact>>

    /**
     * @param contact contact to be inserted to the Data Access Object
     */
    @Insert
    suspend fun insertContact(vararg contact: Contact)

}
