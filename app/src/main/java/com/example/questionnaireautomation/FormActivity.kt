package com.example.questionnaireautomation

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class FormActivity : AppCompatActivity() {
    private val headersList = mutableListOf<String>()
    private val answersList = MutableList(42){""} // всего 79 полей

    val titles = arrayOf(
        "Личные данные",
        "Отношение к воинской обязанности",
        "Образование"
    )

    private lateinit var viewPager: ViewPager
    private lateinit var progressBar: ProgressBar

    private val fragmentPagesArray = arrayOf(
        R.layout.fragment_page1,
        R.layout.fragment_page2,
        R.layout.fragment_page3,
        /*R.layout.fragment_page4,
        R.layout.fragment_page5,
        R.layout.fragment_page6,
        R.layout.fragment_page7,
        R.layout.fragment_page8,
        R.layout.fragment_page9,
        R.layout.fragment_page10,
        R.layout.fragment_page11,
        R.layout.fragment_page_last*/
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        viewPager = findViewById(R.id.viewPager)

        val homeButton: Button = findViewById(R.id.homeButton)
        val submitButton: Button = findViewById(R.id.submitButton)

        val adapter = FormPagerAdapter(supportFragmentManager, fragmentPagesArray, answersList)
        viewPager.adapter = adapter
        progressBar = findViewById(R.id.progressBar)

        updateActionBarTitle(0, titles)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                FormUtils.updateProgressBar(progressBar, position, adapter.count)
                updateActionBarTitle(position, titles)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        FormUtils.updateProgressBar(progressBar, viewPager.currentItem, adapter.count)

        homeButton.setOnClickListener {
            onBackButtonPressed()
        }

        submitButton.setOnClickListener {
            FormUtils.saveFormData(this, headersList, answersList)
        }
    }

    class FormPagerAdapter(fm: FragmentManager, private val fragments: Array<Int>, private val answersList: MutableList<String>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return fragments.size // Количество страниц
        }

        override fun getItem(position: Int): Fragment {
            return PageFragment(fragments[position], position, answersList)
        }
    }

    private fun updateActionBarTitle(position: Int, titles: Array<String>) {
        supportActionBar?.title = titles[position]
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onBackButtonPressed() {
        showExitConfirmationDialog()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выход")
        builder.setMessage("Вы уверены, что хотите выйти?")
        builder.setPositiveButton("Да") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
            goToMainMenu()
        }
        builder.setNegativeButton("Нет") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun goToMainMenu() {
        resetAll()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.N) // видимо будет работать только от андроид 7.0
    private fun resetAll() {
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = FormPagerAdapter(supportFragmentManager, fragmentPagesArray, answersList)

        for (i in 0 until viewPager.childCount) {
            val fragment = adapter.getItem(i) as PageFragment // заменить на PageFragment
            val view = fragment.view

            view?.findViewById<EditText>(R.id.editTextSurname)?.setText("")
            view?.findViewById<EditText>(R.id.editTextName)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPatronymic)?.setText("")
            view?.findViewById<RadioButton>(R.id.radioButtonMale)?.isChecked = false
            view?.findViewById<RadioButton>(R.id.radioButtonFemale)?.isChecked = false
            view?.findViewById<EditText>(R.id.editTextBirthDay)?.setText("")
            view?.findViewById<EditText>(R.id.editTextBirthPlace)?.setText("")
            view?.findViewById<EditText>(R.id.editTextNationality)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPassportSerial)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPassportNumber)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPassportGivenBy)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPassportGivenDate)?.setText("")
            view?.findViewById<EditText>(R.id.editTextSNILS)?.setText("")
            view?.findViewById<EditText>(R.id.editTextINN)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPhoneMobile)?.setText("")
            view?.findViewById<EditText>(R.id.editTextPhoneHome)?.setText("")
            view?.findViewById<EditText>(R.id.editTextAddressPassportIndex)?.setText("")
            view?.findViewById<EditText>(R.id.editTextAddressPassport)?.setText("")
            view?.findViewById<EditText>(R.id.editTextAddressFactualIndex)?.setText("")
            view?.findViewById<EditText>(R.id.editTextAddressFactual)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryStatus)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryRank)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryDocsSerial)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryDocsNumber)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryDocsFrom)?.setText("")
            view?.findViewById<EditText>(R.id.editTextMilitaryDocsDate)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducation)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationUni)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationDiploma)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationDate)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationDiplomaQual)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationDiplomaSpecialty)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationAfterUni)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationAfterUniName)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationAfterUniDoc)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationAfterUniYear)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationAfterUniSpecialty)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationScience)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationScienceDiploma)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationScienceRank)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationScienceRankSerial)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationScienceRankNumber)?.setText("")
            view?.findViewById<EditText>(R.id.editTextEducationForeign)?.setText("")
        }

        headersList.clear()
        answersList.replaceAll { "" }
    }
}
