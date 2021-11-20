package com.example.testtodoapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


/* Some source code in this file can be found at:

https://medium.com/@tanunprabhu95/to-do-list-application-using-kotlin-using-android-studio-546e74ac75aa

 */


class TodoActivity : AppCompatActivity() {


    // Todo list items
    private val itemlist = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)


        // UI Elements
        val delete = findViewById<Button>(R.id.delete)
        val add = findViewById<Button>(R.id.add)
        val listView = findViewById<ListView>(R.id.listView)


        // Adapter to translate list items
        val adapter =ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist)


        // Add button
        add.setOnClickListener {
            this.displayDialog(adapter, listView)
        }


        // Deleting selected items
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist[item])
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }


    }


    // Function to call custom_dialog
    @SuppressLint("InflateParams")
    private fun displayDialog(adapter: ArrayAdapter<String>,
                              listView: ListView) {


        // Config
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
        val editText: EditText = dialogLayout.findViewById(R.id.editText)


        // Dialog config
        with (builder) {
            setTitle("Add Todo")
            setPositiveButton("Add"){_, _ ->
                if (editText.text.isNotEmpty()) {
                    itemlist.add(editText.text.toString())
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    editText.text.clear()
                }
            }
            setNegativeButton("Cancel"){_, _ ->
                // Cancels
            }
            setView(dialogLayout)
            show()
        }
    }


}

