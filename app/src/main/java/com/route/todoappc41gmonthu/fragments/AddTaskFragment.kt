package com.route.todoappc41gmonthu.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc41gmonthu.database.TasksDatabase
import com.route.todoappc41gmonthu.database.model.Task
import com.route.todoappc41gmonthu.databinding.FragmentAddTaskBinding
import com.route.todoappc41gmonthu.fragments.callbacks.OnTaskAddedListener
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class AddTaskFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var calendar: Calendar
    var onTaskAddedListener: OnTaskAddedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        binding.selectDateTv.setOnClickListener {
            showDate()
        }
        binding.addTaskBtn.setOnClickListener {
            if (validateInputs())
                addTask()
        }
    }

    fun showDate() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { view, year, month, dayOfMonth ->
                calendar.setDate(year, month, dayOfMonth)
                calendar.clearTime()
                binding.selectDateTv.text = "${dayOfMonth}/${month + 1}/${year}"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = Date().time
        datePicker.show()
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.title.text.isNullOrEmpty() || binding.title.text.isNullOrBlank()) {
            binding.title.error = "Required"
            isValid = false
        } else {
            binding.title.error = null
        }

        return isValid
    }

    fun addTask() {
        TasksDatabase.getInstance().getTaskDao().insertTask(
            Task(title = binding.title.text.toString(), date = calendar.time)
        )
        onTaskAddedListener?.onTaskAdded()
        dismiss()
    }
}
