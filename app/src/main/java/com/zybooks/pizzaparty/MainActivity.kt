package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil
import android.util.Log

const val TAG = "MainActivity"
const val SLICES_PER_PIZZA = 8


/**
 * This is a MainActivity class. It extends the AppCompatActivity class (an activity displays the UI and proccess User Input)
 *
 * This class defines the activity which starts when the app runs.
 *
 * @property [numAttendEditText] - lateinit (allows properties to be initiated in [onCreate]), UI for number of Pizza Eaters
 * @property [numPizzasTextView] - ^ , text box to display the results of calculating how many pizzas to order (No UserInput)
 * @property [howHungryRadioGroup] - ^, range of options for how hungry, one selection at a time
 */
class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * onCreate function (called when the activity is first created)
     *
     * @[setContentView] - sets the MainActivity content (properties) to the layout in activity_main.xml
     * @[findViewById] - retrieves an object from the 'widget' from activity_main.xml
     * @[Log.d] - creates a message on the system log (Useful for debugging)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate was called")

        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
    }

    /**
     * calculateClick function ('Calculate' button on activity_main has been clicked)
     * all information from 'numAttend' and 'slicesPerPerson' are used to calculate the totalPizzas value
     * and update the numPizzaTextView
     *
     *
     *
     */
    fun calculateClick(view: View) {

        Log.d(TAG, "button was called")

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Determine how many slices on average each person will eat
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        // Calculate and show the number of pizzas needed
        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }


}
