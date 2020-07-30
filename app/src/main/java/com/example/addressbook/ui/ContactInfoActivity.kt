package com.example.addressbook.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.addressbook.R
import kotlinx.android.synthetic.main.activity_contact_info.*

class ContactInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
        supportActionBar?.title = intent.getStringExtra("firstName")

        displayContactInfo()

    }

    /**
     * Users contact info that was passed through the intent is displayed on the text views of
     * this Activity
     */
    private fun displayContactInfo() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val firstName = "First Name: " + intent.getStringExtra("firstName")
        val lastName = "Last Name: " + intent.getStringExtra("lastName")
        val emailAddress = "Email Address: " + intent.getStringExtra("emailAddress")
        val phoneNumber = "Phone Number: " + intent.getStringExtra("phoneNumber")
        val address = "Address: " + intent.getStringExtra("address")
        contact_info_first_name.text = firstName
        contact_info_last_name.text = lastName
        contact_info_email_address.text = emailAddress
        contact_info_phone_number.text = phoneNumber
        contact_info_address.text = address
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Return back to contact screen
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
