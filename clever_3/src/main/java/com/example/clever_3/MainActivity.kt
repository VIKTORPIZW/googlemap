package com.example.clever_3

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri

import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clever_3.db.ContactsEntity
import com.example.clever_3.db.RoomContactsDb
import com.example.clever_3.ext.toArrayOfStrings


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSecond: Button = findViewById(R.id.btn_show_contact)
        val button: Button = findViewById(R.id.btn_read_contact)
        button.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            )
                ActivityCompat.requestPermissions(
                    this,
                    Array(1) { Manifest.permission.READ_CONTACTS },
                    123
                )
            else readContact()

        }

        buttonSecond.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val db = RoomContactsDb.getAppDatabase(this)?.contactDao()
            val myList: List<ContactsEntity>? = db?.getAllContactsInfo()
            builder.setTitle("Выбрать контакт")
            val listFromDb = myList?.toMutableList()
            val arrayContacts = listFromDb?.toArrayOfStrings()
            var checkedIndex = -1

            builder.setSingleChoiceItems(
                arrayContacts,
                -1,
                DialogInterface.OnClickListener { dialog, index ->
                    checkedIndex = index
                })

            builder.setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
                Toast.makeText(this, "Сохранено в SP", Toast.LENGTH_SHORT).show()
                Log.d("My_Log", "Ok pressed, checked Index is $checkedIndex")
                var contactForSP = arrayContacts?.get(checkedIndex)
                val sharedPreferences =
                    getSharedPreferences("myContactfromSP", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("myData", contactForSP)
                editor.apply()
            }
            builder.show()

        }
        val buttonShowFromSP: Button = findViewById(R.id.btn_show_from_SP)
        buttonShowFromSP.setOnClickListener {
            val sharedPreferences = getSharedPreferences("myContactfromSP", Context.MODE_PRIVATE)
            val readFromSP = sharedPreferences.getString("myData", "")
            val txtFromSp: TextView = findViewById(R.id.contact_from_sp)
            txtFromSp.text = readFromSP
        }

    }


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
                    val contact = ContactsEntity(txtName, txtNumber, txtEmail)
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
}




