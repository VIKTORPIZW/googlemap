package com.example.clever_3

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clever_3.db.ContactsEntity
import com.example.clever_3.db.RoomContactsDb


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD




        val button: Button = findViewById(R.id.btn_read_contact)
=======
        val buttonShowFromSP: Button = findViewById(R.id.btn_show_from_SP)
        val button: Button = findViewById(R.id.btn_read_contact)
        var list: RecyclerView = findViewById(R.id.listView)
        list.layoutManager = LinearLayoutManager(this)

        buttonShowFromSP.setOnClickListener {

        }

>>>>>>> a7e0a74fd5d0ce24f12e63e686264863d89d9531
        button.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
<<<<<<< HEAD
            )
                ActivityCompat.requestPermissions(
                    this,
                    Array(1) { Manifest.permission.READ_CONTACTS },
                    123
                )
            else readContact()

        }
=======
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    Array(1) { Manifest.permission.READ_CONTACTS },
                    111
                )

            } else {


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
>>>>>>> a7e0a74fd5d0ce24f12e63e686264863d89d9531



        }
    }

<<<<<<< HEAD
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {
        var list: RecyclerView = findViewById(R.id.listView)
        list.layoutManager = LinearLayoutManager(this)
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
            val email =
                contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
            val obj = ContactDTO()
            obj.name = name
            obj.number = number
            obj.emai = email

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

=======
>>>>>>> a7e0a74fd5d0ce24f12e63e686264863d89d9531

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
            holder.email.text = contactItem.emai
            holder.profile.setImageBitmap(list[position].image)
            holder.itemView.setOnClickListener(object : View.OnClickListener {




                override fun onClick(v: View?) {
                    Toast.makeText(context, "Сохранено в Базу Данных", Toast.LENGTH_SHORT).show()
                    val txtName = contactItem.name
                    val txtNumber = contactItem.number
                    val txtEmail = contactItem.emai

                    val db = RoomContactsDb.getAppDatabase(context)?.contactDao()
                    val contact = ContactsEntity(txtName,txtNumber,txtEmail)
                    db?.insertContact(contact)


                }
            })

        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            var name = v.findViewById<TextView>(R.id.tv_name)
            var number = v.findViewById<TextView>(R.id.tv_number)
            var email = v.findViewById<TextView>(R.id.tv_email)
            val profile = v.findViewById<ImageView>(R.id.iv_profile)

        }



    }
<<<<<<< HEAD
}

=======
     override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
>>>>>>> a7e0a74fd5d0ce24f12e63e686264863d89d9531


