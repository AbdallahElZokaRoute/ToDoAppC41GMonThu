package com.route.todoappc41gmonthu.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.route.todoappc41gmonthu.database.TasksDatabase
import com.route.todoappc41gmonthu.databinding.FragmentTaskListBinding
import com.route.todoappc41gmonthu.viewContainer.CustomWeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
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
        binding.weekCalendarView.dayBinder = CustomWeekDayBinder()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDate = LocalDate.now()
            val currentMonth = YearMonth.now()
            val startDate = LocalDate.now() // Adjust as needed
            val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
            val firstDayOfWeek = DayOfWeek.SATURDAY // Available from the library
            binding.weekCalendarView.setup(startDate, endDate, firstDayOfWeek)
            binding.weekCalendarView.scrollToWeek(currentDate)
        }

    }
}
