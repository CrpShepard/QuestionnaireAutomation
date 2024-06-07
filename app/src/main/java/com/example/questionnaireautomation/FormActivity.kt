package com.example.questionnaireautomation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.Charset

class FormActivity : AppCompatActivity() {

    private var currentIndex = 0
    private var k = 1
    private val formElements = mutableListOf<View>()
    private val headersList = mutableListOf<String>()
    private val answersList = mutableListOf<String>()

    private lateinit var viewPager: ViewPager
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button

    //private lateinit val viewPager: ViewPager
    private lateinit var pagerAdapter: FormPagerAdapter




    private fun setup() {
        headersList.add("Фамилия")
        headersList.add("Имя")
        headersList.add("Отчество")
        headersList.add("Пол")
        headersList.add("Дата рождения")
        headersList.add("Место рождения")
        headersList.add("Гражданство")
        /*headersList.add("Паспорт серия")
        headersList.add("Паспорт номер")
        headersList.add("Паспорт выдан")
        headersList.add("Паспорт дата")
        headersList.add("СНИЛС")
        headersList.add("ИНН")
        headersList.add("Номер телефона моб")
        headersList.add("Номер телефона дом")
        headersList.add("Почтовый индекс пасп")
        headersList.add("Адрес пасп")
        headersList.add("Почтовый индекс факт")
        headersList.add("Адрес факт")
        headersList.add("Состояние воен")
        headersList.add("Воинское звание")*/
    }

    private fun getAnswers() {
        answersList.add(findViewById<EditText>(R.id.editTextSurname).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextName).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPatronymic).text.toString())

        if (findViewById<CheckBox>(R.id.checkBoxSexM).isChecked) {
            answersList.add("М")
        }
        else if (findViewById<CheckBox>(R.id.checkBoxSexF).isChecked) {
            answersList.add("Ж")
        }
        else {
            answersList.add("")
        }

        answersList.add(findViewById<EditText>(R.id.editTextBirthDay).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextBirthPlace).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextNationality).text.toString())
        /*answersList.add(findViewById<EditText>(R.id.editTextPassportSerial).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPassportNumber).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPassportGivenBy).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPassportGivenDate).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextSNILS).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextINN).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPhoneMobile).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextPhoneHome).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextAddressPassportIndex).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextAddressPassport).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextAddressFactualIndex).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextAddressFactual).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextMilitaryStatus).text.toString())
        answersList.add(findViewById<EditText>(R.id.editTextMilitaryRank).text.toString())*/



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        //setup()


        viewPager = findViewById(R.id.viewPager)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        val submitButton: Button = findViewById(R.id.submitButton)

        val adapter = FormPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        prevButton.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        nextButton.setOnClickListener {
            if (viewPager.currentItem < adapter.count - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

        submitButton.setOnClickListener {
            saveFormData()
        }
    }

    class Page1Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page1, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class Page2Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page2, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class Page3Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page3, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class Page4Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page4, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class Page5Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page5, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class Page6Fragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_page6, container, false)
            // Инициализация элементов страницы и их логика
            return view
        }
    }

    class FormPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return 6 // Количество страниц
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> Page1Fragment() // Фрагмент для первой страницы
                1 -> Page2Fragment() // Фрагмент для второй страницы
                2 -> Page3Fragment() // Фрагмент для первой страницы
                3 -> Page4Fragment() // Фрагмент для второй страницы
                4 -> Page5Fragment() // Фрагмент для первой страницы
                5 -> Page6Fragment() // Фрагмент для второй страницы
                // Добавьте фрагменты для остальных страниц по аналогии
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }

    private fun saveFormData() {
        setup()
        getAnswers()

        val data = StringBuilder()

        // Добавляем заголовки
        for (header in headersList) {
            data.append(header).append(",")
        }
        data.deleteCharAt(data.length - 1) // Удалить последнюю запятую
        data.append("\n")

        // Добавляем данные
        for (answer in answersList) {
            data.append(answer).append(",")
        }
        data.deleteCharAt(data.length - 1) // Удалить последнюю запятую

        try {
            val file = File(getExternalFilesDir(null), "form_data.csv")
            val fos: FileOutputStream = FileOutputStream(file)
            //fos.write(data.toString().toByteArray(Charsets.UTF_8))
            //fos.close()
            val writer = OutputStreamWriter(fos, Charset.forName(("UTF-8")))
            writer.write(data.toString())
            writer.close()
            Toast.makeText(this, "Данные сохранены: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show()
        }
    }


}
