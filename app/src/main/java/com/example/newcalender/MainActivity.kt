package com.example.newcalender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.*
import sun.bob.mcalendarview.MCalendarView


class MainActivity : AppCompatActivity() {


    var dateString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val readdbtv: TextView = findViewById(R.id.readdbtv)
        val textView: TextView = findViewById(R.id.newtextView)
        val readdbtvevent: TextView = findViewById(R.id.readdbtvevent)
        val editText: EditText = findViewById(R.id.editText)

        val calendarView: CalendarView = findViewById(R.id.calenderView)
        val submit_button: Button = findViewById(R.id.button)




        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener() { calendarView, year: Int, month: Int, day: Int ->

           readdbtv.setText("")

            dateString = ("$day/$month/$year")

            val newmonthyear = "$month/$year"


            readdbtvevent.setText("")


            MyDBOpenHelper.newStringDateDB = dateString.toString()
            MyDBOpenHelper.monthyear = newmonthyear.toString()



            Log.i("Saad", MyDBOpenHelper.newStringDateDB)


            val dbHandler = MyDBOpenHelper(this, null)

            val cursor_sec = dbHandler.getDateEvents()



            try {

            cursor_sec!!.moveToFirst()

            readdbtvevent.append(
                cursor_sec.getString(
                    cursor_sec.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)

                )
            )

            cursor_sec!!.moveToFirst()

            readdbtvevent.append(
                cursor_sec.getString(
                    cursor_sec.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)

                )
            )




                while (cursor_sec.moveToNext()) {

                    readdbtvevent.append(
                        cursor_sec.getString(
                            cursor_sec.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)

                        )
                    )

                    readdbtvevent.append(
                        cursor_sec.getString(
                            cursor_sec.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)

                        )
                    )


                }

            }

            catch (e: Exception) { Log.i("MainActivity", "While loop is failing") }


        })



            submit_button.setOnClickListener(View.OnClickListener {


                val dbHandler = MyDBOpenHelper(this, null)

                val newdateString: String = dateString.toString()
                val newEditTextString: String = " - " + editText.text.toString() + "\n"


                val user = Product(newdateString, newEditTextString)

                dbHandler.addName(user)

                Toast.makeText(this, "Stored", Toast.LENGTH_LONG).show()


                readdbtvevent.setText("")
                readdbtv.setText("")
                editText.setText("")

                val cursor = dbHandler.getAllEvents()


                cursor!!.moveToFirst()

                readdbtv.append(
                    (cursor.getString(
                        cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)
                    ))
                )


                cursor!!.moveToFirst()

                readdbtv.append(
                    (cursor.getString(
                        cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)
                    ))
                )


                while (cursor.moveToNext()) {
                    readdbtv.append(
                        (cursor.getString(
                            cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)
                        ))
                    )


                    readdbtv.append(
                        (cursor.getString(
                            cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)
                        ))
                    )
                readdbtv.setText("")
                }

            })


        textView.setOnClickListener(View.OnClickListener {



            val dbHandler = MyDBOpenHelper(this, null)

            readdbtvevent.setText("")
            readdbtv.setText("")
            editText.setText("")

            val cursor = dbHandler.getAllEvents()


            cursor!!.moveToFirst()

            readdbtv.append(
                (cursor.getString(
                    cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)
                ))
            )


            cursor!!.moveToFirst()

            readdbtv.append(
                (cursor.getString(
                    cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)
                ))
            )


            while (cursor.moveToNext()) {
                readdbtv.append(
                    (cursor.getString(
                        cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME1)
                    ))
                )


                readdbtv.append(
                    (cursor.getString(
                        cursor.getColumnIndex(MyDBOpenHelper.COLUMN_NAME2)
                    ))
                )
            }

        })

    }
}
