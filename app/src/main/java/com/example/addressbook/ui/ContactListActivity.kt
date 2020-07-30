package com.example.addressbook.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.addressbook.R
import com.example.addressbook.adapters.ContactsAdapter
import com.example.addressbook.model.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ContactListActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel


    companion object {
        // Intent of Contact List Activity
        fun getIntent(from: Context) = Intent(from, ContactListActivity::class.java)
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        setupUI()
    }

    /**
     * All Contacts fetched from the Room Contact repository is loaded to the recycler view
     *
     * @param adapter ContactsAdapter
     */
    private fun loadContacts(adapter: ContactsAdapter) {
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.contacts.observe(this, Observer { contacts ->

            contacts.let {
                if (contacts.isEmpty()) {
                    showNoContacts()
                }
                adapter.addContactsToList(it)
            }
        })
    }

    /**
     * If the user has no contacts the no contacts placeholders are displayed
     */
    private fun showNoContacts() {
        no_contacts_iv.visibility = View.VISIBLE
        no_contacts_tv.visibility = View.VISIBLE
        add_contact_instructions_tv.visibility = View.VISIBLE
    }

    private fun setupUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.list_of_contacts_recycler)
        val adapter = ContactsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Adds a divider
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        loadContacts(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Load Add Contact activity
        if (item.itemId == R.id.add_contact) {
            startActivity(AddContactActivity.getIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }
}

