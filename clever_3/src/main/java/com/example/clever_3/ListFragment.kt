package com.example.clever_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class ListFragment : Fragment() {
    var listView: ListView? = null
    var arrayList: MutableList<String>? = null
    var arrayAdapter: ArrayAdapter<*>? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.listContacts); //listview from xml
        arrayList =  ArrayList()
        arrayAdapter = ArrayAdapter<*>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);

        } else {

            readContacts();
        }

    }
    // if the user clicks ALLOW in dialog this method gets called.
    @Override
    public  fun onRequestPermissionsResult(requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        readContacts();
    }
// function to read contacts using content resolver
    private void readContacts() {
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cursor.moveToFirst()){
            do {     arrayList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            }while (cursor.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
    }


}

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}