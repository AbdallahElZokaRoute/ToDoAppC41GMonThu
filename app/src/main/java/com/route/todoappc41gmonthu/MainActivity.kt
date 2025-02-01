package com.route.todoappc41gmonthu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.route.todoappc41gmonthu.database.TasksDatabase
import com.route.todoappc41gmonthu.databinding.ActivityMainBinding
import com.route.todoappc41gmonthu.fragments.AddTaskFragment
import com.route.todoappc41gmonthu.fragments.SettingsFragment
import com.route.todoappc41gmonthu.fragments.TaskListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addTaskFab.setOnClickListener {
            AddTaskFragment().show(supportFragmentManager, null)
        }
        binding.todoBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_task_list -> {
                    showFragment(TaskListFragment())
                }

                R.id.navigation_settings -> {
                    showFragment(SettingsFragment())
                }
            }

            return@setOnItemSelectedListener true
        }
        binding.todoBottomNavigationView.selectedItemId = R.id.navigation_task_list
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.todo_fragment_container, fragment)
            .commit()

    }
}