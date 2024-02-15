package com.example.fabandsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    // initialized outside on create so we can use them in the addItem function
    var listItem = ArrayList<String?>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView
    lateinit var fab: FloatingActionButton
    lateinit var undoOnClickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //linking our two interactable items
        listView = findViewById(R.id.lvStuff)
        fab = findViewById(R.id.fab1)

        // setting up the adapter to use the simple list layout, and listItem as the source array
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            listItem
        )

        listView.adapter = adapter

        fab.setOnClickListener {
            // when someone clicks the floating action button it runs the addItem function
            addItem()
            /* snackbar with the text Item Added on the left and an action button "Undo" on the right
            clicking the Undo action button calls the undoOnclickListener
             */
            Snackbar.make(it, "Item Added", Snackbar.LENGTH_LONG)
                .setAction("Undo", undoOnClickListener)
                .show()
        }

        // custom on click listener
        undoOnClickListener = View.OnClickListener {
            // removes the last item added to the array listItem
            listItem.removeAt(listItem.size - 1)
            // notifies the adapter of the change so the screen is updated
            adapter?.notifyDataSetChanged()
            // creates a snackbar that says item removed
            Snackbar.make(it, "Item Removed", Snackbar.LENGTH_LONG)
                /* need to have the set action for the snack bar but setting it to null means this
                doesn't actually show up on the snackbar
                 */
                .setAction("Action", null)
                .show()
        }
    }

    // function to add item to listItem
    fun addItem(){
        // adds a string to the array "Item X" where x is the list size plus 1
        listItem.add("Item " + (listItem.size + 1))
        // notifies the adapter of the change so it updates on the screen
        adapter?.notifyDataSetChanged()
    }
}