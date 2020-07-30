package com.example.addressbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.addressbook.dao.ContactDao
import com.example.addressbook.model.Contact


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    // Creating Singleton object of database
    companion object {

        // Instance of the Database is made Volatile to be visible by other threads
        @Volatile
        private var DATABASE_INSTANCE: ContactDatabase? = null

        /**
         *
         * @param context Context of the activity
         * @return ContactDatabase returns the room database built
         */
        fun getDatabase(context: Context): ContactDatabase {
            val tempDatabaseInstance = DATABASE_INSTANCE
            if (tempDatabaseInstance != null) {
                return tempDatabaseInstance
            }
            // Building Room database to store users contacts
            synchronized(this) {
                val room = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contacts"
                ).build()
                DATABASE_INSTANCE = room
                return room
            }
        }
    }
}
