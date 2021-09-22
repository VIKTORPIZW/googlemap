package com.example.clever_3

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*


class ListFragment : Fragment() {
    var listView: ListView? = null
    var arrayList: MutableList<String>? = null
    var arrayAdapter: ArrayAdapter<*>? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            arrayList = ArrayList()
            arrayAdapter = ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_list_item_1, arrayList!! as List<String>)
            listView?.adapter = arrayAdapter

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 100)
            } else {

                readContacts()
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        readContacts()
    }

    private fun readContacts() {
        val contentResolver = requireActivity().contentResolver

        val cursor =
            contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor!!.moveToFirst()) {
            do {
                arrayList?.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
            } while (cursor.moveToNext())
            arrayAdapter!!.notifyDataSetChanged()
        }
    }

    }

