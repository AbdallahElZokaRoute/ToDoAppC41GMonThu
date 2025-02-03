package com.route.todoappc41gmonthu.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.route.todoappc41gmonthu.R
import com.route.todoappc41gmonthu.database.TasksDatabase
import com.route.todoappc41gmonthu.databinding.FragmentTaskListBinding
import com.route.todoappc41gmonthu.fragments.adapter.TasksAdapter
import com.route.todoappc41gmonthu.viewContainer.CustomWeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.util.Calendar
import java.util.Date


class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TasksAdapter
    private lateinit var weekDayBinder: CustomWeekDayBinder
    private lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendar = Calendar.getInstance()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            weekDayBinder = CustomWeekDayBinder(
                selectedTextColor = resources.getColor(R.color.blue, null),
                unselectedTextColor = resources.getColor(R.color.black, null)
            ) { weekDay ->
                val currentSelection = weekDayBinder.selectedDate
                if (currentSelection == weekDay.date) {
                    weekDayBinder.selectedDate = null
                    getTasksFromDataBase()
                    binding.weekCalendarView.notifyDateChanged(currentSelection)
                } else {
                    weekDayBinder.selectedDate = weekDay.date
                    Log.e(
                        "TAG",                                  //  1
                        "onViewCreated: Calendar Month Value ${calendar.get(Calendar.MONTH)}",
                    )
                    Log.e(
                        "TAG",                                  // 2
                        "onViewCreated: Week Day.Date. Month Value ${weekDay.date.monthValue}",
                    )
                    calendar.setDate(
                        weekDay.date.year,
                        weekDay.date.monthValue - 1,
                        weekDay.date.dayOfMonth
                    )

                    calendar.clearTime()
                    getTaskByDateFromDataBase(calendar.time)
                    binding.weekCalendarView.notifyDateChanged(weekDay.date)
                    if (currentSelection != null) {
                        binding.weekCalendarView.notifyDateChanged(currentSelection)
                    }
                }
            }
            binding.weekCalendarView.dayBinder = weekDayBinder
            val currentDate = LocalDate.now()
            val currentMonth = YearMonth.now()
            val startDate = LocalDate.now() // Adjust as needed
            val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
            val firstDayOfWeek = DayOfWeek.SATURDAY // Available from the library
            binding.weekCalendarView.setup(startDate, endDate, firstDayOfWeek)
            binding.weekCalendarView.scrollToWeek(currentDate)
        }
        adapter = TasksAdapter()
        binding.tasksRecyclerView.adapter = adapter
        getTasksFromDataBase()
    }

    // 3 - 2 -2025 8:46:20PM      Parameter   1738879200000         1739052000000
    // 3 - 2 -2025 8:46:28PM      Database    1738922400000         1739095200000
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTaskByDateFromDataBase(date: Date) {
        Log.e("Date", "Parameter = ${date.time}")
        val list = TasksDatabase.getInstance().getTaskDao().getTasksByDate(date)
        adapter.taskList = list
        adapter.notifyDataSetChanged()
    }

    fun getTasksFromDataBase() {
        if (isHidden) return
        if (isVisible) {
            val context = requireContext()
            val activity = requireActivity()
        }
        val tasksList = TasksDatabase.getInstance().getTaskDao().getAllTasks()
        adapter.taskList = tasksList
        adapter.notifyDataSetChanged()
//        adapter.notifyItemInserted()
    }
}
