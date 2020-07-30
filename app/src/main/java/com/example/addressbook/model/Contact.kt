package com.example.addressbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @property firstName String? first name of the contact
 * @property lastName String? last name of the contact
 * @property emailAddress String? email address of the contact
 * @property phoneNumber Int phone number of the contact which is a Primary key
 * @property address String? address of the contact
 * @constructor
 */
@Entity(tableName = "contacts")
data class Contact(
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "email_address") var emailAddress: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "phone_number") var phoneNumber: Long,
    @ColumnInfo(name = "address") var address: String?
)
