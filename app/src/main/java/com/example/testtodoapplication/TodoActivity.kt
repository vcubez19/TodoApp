package com.example.testtodoapplication


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
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
        val spinnerFilter = findViewById<Spinner>(R.id.idSpinnerFilter)


        // Check if the itemList is currently sorted by oldest
        var isOldest = false


        // Spinner disabled initially
        spinnerFilter.isEnabled = false


        // Adapter to translate list items
        val adapter =ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist)


        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                if ( spinnerFilter.selectedItemPosition == 1 ) {
                    isOldest = true
                    itemlist.reverse()
                } else {
                    if ( isOldest ) {
                        itemlist.reverse()
                    }
                }
                for ((index, _) in itemlist.withIndex()) {
                    listView.setItemChecked(index, false)
                }
                adapter.notifyDataSetChanged()
            }


            override fun onNothingSelected(parentView: AdapterView<*>?) {
                //
            }
        }


        // Add button
        add.setOnClickListener {
            spinnerFilter.setSelection(0, true)
            this.displayDialog(adapter, listView, spinnerFilter)
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
            if ( itemlist.size < 2 ) {
                spinnerFilter.isEnabled = false
            }

        }


    }


    // Function to call custom_dialog
    @SuppressLint("InflateParams")
    private fun displayDialog(
        adapter: ArrayAdapter<String>,
        listView: ListView,
        spinner: Spinner
    ) {


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
                    //itemlist.add(editText.text.toString())
                    itemlist.add(0, editText.text.toString())
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    editText.text.clear()
                    if ( itemlist.size > 1 ) {
                        spinner.isEnabled = true
                    }
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

