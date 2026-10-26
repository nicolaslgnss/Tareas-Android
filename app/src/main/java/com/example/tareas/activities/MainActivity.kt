package com.example.tareas.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tareas.R
import com.example.tareas.data.Task
import com.example.tareas.data.TaskDAO
import com.example.tareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}


// Codigo para probar el DAO
/*
val taskDAO = TaskDAO(context = this)
taskDAO.open()

val task1 = Task(id = -1, title = "Comprar leche")
val task2 = Task(id = -1, title = "Comprar Alcohol")
val task3 = Task(id = -1, title = "Comprar Preservativos")

taskDAO.insert(task1)
taskDAO.insert(task2)
taskDAO.insert(task3)

taskDAO.findAll().forEach {
    Log.i("TEST_DB", it.toString())
}

val task4 = taskDAO.findById(1)
if (task4 != null) {
    task4.title = "Comprar leche entera"
    taskDAO.update(task4)
}

val task5 = taskDAO.findById(2)
if (task5 != null) {
    task5.done = true
    taskDAO.update(task5)
}

taskDAO.findAll().forEach {
    Log.i("TEST_DB", it.toString())
}

taskDAO.findAll().forEach {
    taskDAO.delete(task= it)
}
Log.i("TEST_DB","Numero de tareas en BD: ${taskDAO.findAll().size}")

 */