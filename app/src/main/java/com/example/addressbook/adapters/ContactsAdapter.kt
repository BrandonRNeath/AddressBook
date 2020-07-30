package com.example.addressbook.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.addressbook.R
import com.example.addressbook.model.Contact
import com.example.addressbook.ui.ContactInfoActivity
import com.example.addressbook.ui.ContactListActivity

class ContactsAdapter() : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    // Setting up item click
    private var onItemClick: ((Contact) -> Unit)? = null

    // Is the list which the users contacts fetched from Room to be added
    private var contactsList = emptyList<Contact>()

    private val inflater: LayoutInflater = LayoutInflater.from(ContactListActivity.context)


    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstNameText: TextView = view.findViewById(R.id.contact_list_first_name)


        //On click listener to display the contacts info when name of the contact has been selected
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(contactsList[adapterPosition])
                // Passing all the selected contact info across to the Contact Info activity
                val intent =
                    Intent(ContactListActivity.context, ContactInfoActivity::class.java).apply {
                        putExtra("firstName",
                            contactsList[adapterPosition].firstName.toString())
                        putExtra("lastName",
                            contactsList[adapterPosition].lastName.toString())
                        putExtra("emailAddress",
                            contactsList[adapterPosition].emailAddress.toString())
                        putExtra("phoneNumber",
                            contactsList[adapterPosition].phoneNumber.toString())
                        putExtra("address",
                            contactsList[adapterPosition].address.toString())
                    }
                ContactListActivity.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = inflater.inflate(R.layout.contact_list, parent, false)
        return ContactViewHolder(view)
    }

    /**
     *
     * @return Int returns size of the users contact list
     */
    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact: Contact = contactsList[position]
        holder.firstNameText.text = contact.firstName

    }

    /**
     * This adds the users contacts to display on recycler view on the ContactListActivity
     *
     * @param contacts List<Contact> list of contacts fetched from the Room database
     */
    fun addContactsToList(contacts: List<Contact>) {
        this.contactsList = contacts
        notifyDataSetChanged()
    }
}
