package com.example.tareas.activities


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tareas.R
import com.example.tareas.adapters.TaskAdapter
import com.example.tareas.data.Task
import com.example.tareas.data.TaskDAO
import com.example.tareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: TaskAdapter

    lateinit var taskDAO: TaskDAO

    var taskList = listOf<Task>()

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

        taskDAO = TaskDAO(this)

        taskList = taskDAO.findAll()

        adapter = TaskAdapter(items = taskList, ::onItemClick, ::onItemCheck, ::onItemEdit, ::onItemDelete
        )

        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(this)
    }

    fun onItemClick(position: Int) {
        onItemCheck(position)
    }

    fun onItemCheck(position: Int) {
        val task = taskList[position]
        task.done = !task.done
        taskDAO.update(task)

        // TODO: Se puede optimizar
        taskList = taskDAO.findAll()
        adapter.updateData(taskList)
    }

    fun onItemEdit(position: Int) {


    }

    fun onItemDelete(position: Int) {
        
    }
}


// Codigo para probar el DAO
/*
val taskDAO = TaskDAO(context = this)
taskDAO.open()


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