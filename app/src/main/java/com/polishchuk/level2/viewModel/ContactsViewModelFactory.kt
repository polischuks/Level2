package com.polishchuk.level2.viewModel

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polishchuk.level2.repository.ContactRepositoryImpl

class ContactsViewModelFactory(contentResolver: ContentResolver) : ViewModelProvider.Factory{

    val contactRepository = ContactRepositoryImpl(contentResolver)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactsViewModel::class.java)){
            return ContactsViewModel(contactRepository) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}