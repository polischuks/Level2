package com.polishchuk.level2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polishchuk.level2.model.UserContact
import com.polishchuk.level2.repository.ContactRepositoryImpl

class ContactsViewModel(private val contactRepository: ContactRepositoryImpl) : ViewModel() {

    private var contactListMutableLiveData = MutableLiveData<List<UserContact>>()
    var contactListLiveData : LiveData<List<UserContact>> = contactListMutableLiveData

    fun load(){
        contactListMutableLiveData.value = contactRepository.loadContacts()
    }
}