package com.example.datecalculator

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class MainActivity : AppCompatActivity() {
    private var currentDay: Int = 0
    private var currentMonth: Int = 0
    private var currentYear: Int = 0

    private var startDay:String = ""
    private var endDay: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val tvStartDate = findViewById<TextView>(R.id.tvStartDate)
        val tvEndDate = findViewById<TextView>(R.id.tvEndDate)
        val tvNumberOfDay = findViewById<TextView>(R.id.tvNumberOfDay)

        val btnStartDate = findViewById<Button>(R.id.btnStartDate)
        val btnEndDate = findViewById<Button>(R.id.btnEndDate)

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            currentDay = day
            currentMonth = month + 1
            currentYear = year
        }

        fun getCalenderDate(): String {
            return "$currentDay/$currentMonth/$currentYear"
        }

        fun datesBetween() {
            var start = LocalDate.parse(startDay, formatter)
            var end =  LocalDate.parse(endDay, formatter)

            if(start.isAfter(end)){
                tvNumberOfDay.text = "Error"
            }
            val numberOfDay = start.until(end, ChronoUnit.DAYS)
            tvNumberOfDay.text = "days $numberOfDay"
        }


        btnStartDate.setOnClickListener {
            startDay = getCalenderDate()
            tvStartDate.text = startDay

        }

        btnEndDate.setOnClickListener {
            endDay = getCalenderDate()
            tvEndDate.text = endDay
            datesBetween()
        }

    //Toast.makeText(this, getCalenderDate(), Toast.LENGTH_SHORT).show()

    }
}