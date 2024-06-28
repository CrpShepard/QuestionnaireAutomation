package com.example.questionnaireautomation

import android.content.Context
import android.widget.ProgressBar
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.Charset

class FormUtils {

    companion object {
        fun setup(headersList: MutableList<String>) {
            headersList.add("Фамилия") //1 стр
            headersList.add("Имя")
            headersList.add("Отчество")
            headersList.add("Пол")
            headersList.add("Дата рождения")
            headersList.add("Место рождения")
            headersList.add("Гражданство")
            headersList.add("Паспорт серия")
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
            headersList.add("Воинский учет") //2
            headersList.add("Воинское звание")
            headersList.add("Военный билет серия")
            headersList.add("Военный билет номер")
            headersList.add("Военный билет кем выдан")
            headersList.add("Военный билет дата выдачи")
            headersList.add("Образование") //3
            headersList.add("Диплом наименование обр учр")
            headersList.add("Диплом серия номер")
            headersList.add("Диплом дата окончания")
            headersList.add("Диплом квалиф")
            headersList.add("Диплом специальность")
            headersList.add("Послевузовское проф обр")
            headersList.add("Послевузовское наим обр учр")
            headersList.add("Послевузовское удостоверение номер дата выдачи")
            headersList.add("Послевузовское год окончания")
            headersList.add("Послевузовское специальность")
            headersList.add("Ученая степень")
            headersList.add("Ученая диплом")
            headersList.add("Ученая звание")
            headersList.add("Ученая аттестат серия")
            headersList.add("Ученая аттестат номер")
            headersList.add("Иностранные языки")
            /*headersList.add("Повыш квал обр учр") //4
            headersList.add("Повыш квал диплом")
            headersList.add("Повыш квал дата")
            headersList.add("Повыш квал специальность")
            headersList.add("Стаж прием") //5
            headersList.add("Стаж увольнение")
            headersList.add("Стаж учр")
            headersList.add("Стаж должность")
            headersList.add("Опыт работы знания")
            headersList.add("Опыт работы достижения")
            headersList.add("Дост и недост") //6
            headersList.add("Увлечения")
            headersList.add("ФИО рука")
            headersList.add("Гос награды")
            headersList.add("Заграница начало") //7
            headersList.add("Заграница конец")
            headersList.add("Заграница страна")
            headersList.add("Заграница цель")
            headersList.add("Семейное положение") //8
            headersList.add("Родственники степень")
            headersList.add("Родственники ФИО")
            headersList.add("Родственники дата рождения")
            headersList.add("Родственники должность")
            headersList.add("Родственники адрес")
            headersList.add("Родственники телефон")
            headersList.add("Ответственность") //9
            headersList.add("Ответственность детали")
            headersList.add("Опыт работы на компе") //10
            headersList.add("Водительское удост")
            headersList.add("Рекомендации ФИО")
            headersList.add("Рекомендации место работы")
            headersList.add("Рекомендации должность")
            headersList.add("Рекомендации телефон")
            headersList.add("Гос служба") //11
            headersList.add("Гос служба АКБ банка")
            headersList.add("Гос служба согласие")
            headersList.add("Доп сведения") //12 last стр*/
        }

        fun saveFormData(context: Context, headersList: MutableList<String>, answersList: List<String>) {
            setup(headersList)

            val data = StringBuilder()

            // Добавляем заголовки
            for (header in headersList) {
                data.append(header).append(";")
            }
            data.deleteCharAt(data.length - 1) // Удалить последнюю запятую
            data.append("\n")

            // Добавляем данные
            for (answer in answersList) {
                data.append(answer).append(";")
            }
            data.deleteCharAt(data.length - 1) // Удалить последнюю запятую

            try {
                val file = File(context.getExternalFilesDir(null), "form_data.csv")
                val fos: FileOutputStream = FileOutputStream(file)
                //fos.write(data.toString().toByteArray(Charsets.UTF_8))
                //fos.close()
                val writer = OutputStreamWriter(fos, Charset.forName(("UTF-8")))
                writer.write(data.toString())
                writer.close()
                Toast.makeText(context, "Данные сохранены: ${file.absolutePath}", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show()
            }
        }

        fun updateProgressBar(progressBar: ProgressBar, position: Int, totalPages: Int) {
            val progress = ((position + 1) * 100 / totalPages)
            progressBar.progress = progress
        }
    }
}