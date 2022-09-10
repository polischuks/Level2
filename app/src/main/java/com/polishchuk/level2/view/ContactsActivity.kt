package com.polishchuk.level2.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.polishchuk.level2.R
import com.polishchuk.level2.databinding.ActivityMyContactsBinding
import com.polishchuk.level2.viewModel.ContactsViewModel
import com.polishchuk.level2.viewModel.ContactsViewModelFactory

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyContactsBinding
    private lateinit var viewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        checkPermissionContactReading()

        viewModel = ViewModelProvider(this, ContactsViewModelFactory(this.contentResolver))
            .get(ContactsViewModel::class.java)
        viewModel.contactListLiveData.observe(this, Observer {
            binding.recyclerView.adapter = RecyclerAdapter(it)
            Log.d("CONTACT", it.toString())
        })
        viewModel.load()
    }

    private fun checkPermissionContactReading() {
        val requestCodeReadContacts = 1
        val hasReadContactPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        if (hasReadContactPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                requestCodeReadContacts
            )
        }
    }
}