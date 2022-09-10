package com.polishchuk.level2.repository

import android.content.Context
import com.polishchuk.level2.model.UserContact

interface IContactRepository {

    fun loadContacts(): List<UserContact>
    fun addContact()
    fun deleteContact()
}