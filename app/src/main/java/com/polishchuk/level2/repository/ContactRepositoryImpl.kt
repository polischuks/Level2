package com.polishchuk.level2.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import com.polishchuk.level2.model.UserContact

class ContactRepositoryImpl(private val contentResolver: ContentResolver) : IContactRepository {

    private val contentUri: Uri = ContactsContract.Contacts.CONTENT_URI
    private val idUser = ContactsContract.Contacts._ID
    private val displayName = ContactsContract.Contacts.DISPLAY_NAME
    private val hasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER
    private val phoneContentUri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    private val phoneContactId = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
    private val number = ContactsContract.CommonDataKinds.Phone.NUMBER
    private val output = mutableListOf<UserContact>()
    private val cursor: Cursor? = contentResolver.query(
        contentUri, null, null,
        null, null
    )

    @SuppressLint("Range")
    override fun loadContacts(): List<UserContact> {
        if (cursor != null) {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val contactId = cursor.getString(cursor.getColumnIndex(idUser))
                    val name = cursor.getString(cursor.getColumnIndex(displayName))
                    val hasPhoneNumbers =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(hasPhoneNumber)))
                    var phoneNumber = ""
                    if (hasPhoneNumbers > 0) {
                        val phoneCursor = contentResolver.query(
                            phoneContentUri, null,
                            "$phoneContactId = ?", arrayOf(contactId), null
                        )
                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                phoneNumber =
                                    phoneCursor.getString(phoneCursor.getColumnIndex(number))
                            }
                        }
                    }
                    val user = UserContact(name, phoneNumber, "")
                    Log.d("TAG", user.toString())
                    output.add(user)
                }
            }
        }
        return output
    }

    override fun addContact() {
        TODO("Not yet implemented")
    }

    override fun deleteContact() {
        TODO("Not yet implemented")
    }
}