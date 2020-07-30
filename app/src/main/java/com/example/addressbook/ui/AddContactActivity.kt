package com.example.addressbook.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.addressbook.R
import com.example.addressbook.model.Contact
import com.example.addressbook.model.ContactViewModel
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    companion object {
        // Intent of Add Contact Activity
        fun getIntent(from: Context) = Intent(from, AddContactActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupUI()
    }

    /**
     *  Sets up on click listener for the save contact button
     */
    private fun setupUI() {
        save_contact_btn.setOnClickListener {
            var isCompleted = true
            // Validating users save to check if all edit texts have been filled in else
            // user is notified that a field has not been completed
            if (first_name_tv.text.isBlank()) {
                isCompleted = false
                first_name_tv.setHintTextColor(Color.RED)
            }
            if (last_name_tv.text.isBlank()) {
                isCompleted = false
                last_name_tv.setHintTextColor(Color.RED)
            }
            if (email_address_tv.text.isBlank()) {
                isCompleted = false
                email_address_tv.setHintTextColor(Color.RED)
            }
            if (phone_number_tv.text.isBlank()) {
                isCompleted = false
                phone_number_tv.setHintTextColor(Color.RED)
            }
            if (address_tv.text.isBlank()) {
                isCompleted = false
                address_tv.setHintTextColor(Color.RED)
            }
            if (isCompleted) {
                // Contact info to add to Room Database
                val contact = Contact(
                    first_name_tv.text.toString(),
                    last_name_tv.text.toString(), email_address_tv.text.toString(),
                    phone_number_tv.text.toString().toLong(), address_tv.text.toString()
                )
                addContact(contact)
                startActivity(ContactListActivity.getIntent(this))
            } else {
                Toast.makeText(this,"Missing Fields",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Adds contact to the Room database
     *
     * @param contact Contact that is added to the users contact list
     */
    private fun addContact(contact: Contact) {
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.addContact(contact)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Return back to contact screen
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
