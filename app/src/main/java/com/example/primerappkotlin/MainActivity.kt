package com.example.primerappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var arrayAdapter: ArrayAdapter<String>? = null
    private val items: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val texto = editText.text

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items)

        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        buttonAdd.setOnClickListener {
            if (texto.isEmpty()) {
                editText.error = "Este campo está vacío"
            }
            else {
                items.add(texto.toString())
                texto.clear()
                listView.adapter = arrayAdapter
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_button, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.delete_items -> {
                val positionChecker = listView.checkedItemPositions
                val count = listView.count

                for (i in count-1 downTo 0) {

                    if (positionChecker.get(i)) {
                        arrayAdapter?.remove(items[i])
                        Toast.makeText(applicationContext, "Elemento(s) borrado(s) exitosamente", Toast.LENGTH_LONG).show()
                    }

                }

                positionChecker.clear()

                arrayAdapter?.notifyDataSetChanged()
            }
        }
        return true
    }
}