package com.example.clever_3

import android.content.Context
import android.net.Uri

import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.btn_read_contact)
        var list: RecyclerView = findViewById(R.id.listView)
        list.layoutManager = LinearLayoutManager(this)
        button.setOnClickListener {
            val contactList: MutableList<ContactDTO> = ArrayList()
            val contacts = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            while (contacts!!.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val obj = ContactDTO()
                obj.name = name
                obj.number = number

                val photo_uri =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                if (photo_uri != null) {
                    obj.image =
                        MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(photo_uri))
                }
                contactList.add(obj)
            }
            list.adapter = ContactAdapter(contactList, this)
            contacts.close()
        }

    }

    class ContactAdapter(items: List<ContactDTO>, ctx: Context) :
        RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

        private val list = items
        private val context = ctx
        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.contact_child, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var contactItem = list[position]
            holder.name.text = contactItem.name
            holder.number.text = contactItem.number
            holder.profile.setImageBitmap(list[position].image)
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            var name = v.findViewById<TextView>(R.id.tv_name)
            var number = v.findViewById<TextView>(R.id.tv_number)
            val profile = v.findViewById<ImageView>(R.id.iv_profile)
        }


    }


}
