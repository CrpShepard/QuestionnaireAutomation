package com.example.questionnaireautomation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

class PageFragment(val fragmentPage: Int, val pageIndex: Int, val answersList:MutableList<String>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragmentPage, container, false)
        // Инициализация элементов страницы
        when (pageIndex) {
            0 -> {
                setupTextWatcher(view, R.id.editTextSurname, 0)
                setupTextWatcher(view, R.id.editTextName, 1)
                setupTextWatcher(view, R.id.editTextPatronymic, 2)
                setupRadioGroup(view, R.id.radioGroupSex, listOf(R.id.radioButtonMale, R.id.radioButtonFemale), 3)
                setupTextWatcher(view, R.id.editTextBirthDay, 4)
                setupTextWatcher(view, R.id.editTextBirthPlace, 5)
                setupTextWatcher(view, R.id.editTextNationality, 6)
                setupTextWatcher(view, R.id.editTextPassportSerial, 7)
                setupTextWatcher(view, R.id.editTextPassportNumber, 8)
                setupTextWatcher(view, R.id.editTextPassportGivenBy, 9)
                setupTextWatcher(view, R.id.editTextPassportGivenDate, 10)
                setupTextWatcher(view, R.id.editTextSNILS, 11)
                setupTextWatcher(view, R.id.editTextINN, 12)
                setupTextWatcher(view, R.id.editTextPhoneMobile, 13)
                setupTextWatcher(view, R.id.editTextPhoneHome, 14)
                setupTextWatcher(view, R.id.editTextAddressPassportIndex, 15)
                setupTextWatcher(view, R.id.editTextAddressPassport, 16)
                setupTextWatcher(view, R.id.editTextAddressFactualIndex, 17)
                setupTextWatcher(view, R.id.editTextAddressFactual, 18)
            }
            1 -> {
                setupTextWatcher(view, R.id.editTextMilitaryStatus, 19)
                setupTextWatcher(view, R.id.editTextMilitaryRank, 20)
                setupTextWatcher(view, R.id.editTextMilitaryDocsSerial, 21)
                setupTextWatcher(view, R.id.editTextMilitaryDocsNumber, 22)
                setupTextWatcher(view, R.id.editTextMilitaryDocsFrom, 23)
                setupTextWatcher(view, R.id.editTextMilitaryDocsDate, 24)
            }
            2 -> {
                setupTextWatcher(view, R.id.editTextEducation, 25)
                setupTextWatcher(view, R.id.editTextEducationUni, 26)
                setupTextWatcher(view, R.id.editTextEducationDiploma, 27)
                setupTextWatcher(view, R.id.editTextEducationDate, 28)
                setupTextWatcher(view, R.id.editTextEducationDiplomaQual, 29)
                setupTextWatcher(view, R.id.editTextEducationDiplomaSpecialty, 30)
                setupTextWatcher(view, R.id.editTextEducationAfterUni, 31)
                setupTextWatcher(view, R.id.editTextEducationAfterUniName, 32)
                setupTextWatcher(view, R.id.editTextEducationAfterUniDoc, 33)
                setupTextWatcher(view, R.id.editTextEducationAfterUniYear, 34)
                setupTextWatcher(view, R.id.editTextEducationAfterUniSpecialty, 35)
                setupTextWatcher(view, R.id.editTextEducationScience, 36)
                setupTextWatcher(view, R.id.editTextEducationScienceDiploma, 37)
                setupTextWatcher(view, R.id.editTextEducationScienceRank, 38)
                setupTextWatcher(view, R.id.editTextEducationScienceRankSerial, 39)
                setupTextWatcher(view, R.id.editTextEducationScienceRankNumber, 40)
                setupTextWatcher(view, R.id.editTextEducationForeign, 41)
            }
        }
        return view
    }

    private fun setupTextWatcher(view: View, editTextId: Int, answerIndex: Int) {
        val editText = view.findViewById<EditText>(editTextId)
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                answersList[answerIndex] = s.toString()
            }
        })
    }

    private fun setupRadioGroup(view: View, radioGroupId: Int, radioButtonIds: List<Int>, answerIndex: Int) {
        val radioGroup = view.findViewById<RadioGroup>(radioGroupId)
        radioButtonIds.forEachIndexed { index, radioButtonId ->
            val radioButton = view.findViewById<RadioButton>(radioButtonId)
            if (answersList[answerIndex] == radioButton.text.toString()) {
                radioButton.isChecked = true
            }
            radioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    answersList[answerIndex] = radioButton.text.toString()
                }
            }
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == -1) {
                answersList[answerIndex] = ""
            }
        }
    }
}