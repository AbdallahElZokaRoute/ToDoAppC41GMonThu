package com.route.todoappc41gmonthu.viewContainer

import android.os.Build
import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todoappc41gmonthu.databinding.ItemWeekDayBinding
import java.time.format.TextStyle
import java.util.Locale

class CustomWeekDayBinder : WeekDayBinder<DayViewContainer> {
    override fun bind(container: DayViewContainer, data: WeekDay) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            container.weekDayNameText.text = data.date.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )
            container.monthDayNameText.text = "${data.date.dayOfMonth}"
        }
    }

    override fun create(view: View): DayViewContainer {
        return DayViewContainer(view)
    }


}

class DayViewContainer(view: View) : ViewContainer(view) {
    val weekDayNameText = ItemWeekDayBinding.bind(view).weekDayNameTextView
    val monthDayNameText = ItemWeekDayBinding.bind(view).monthDayTextView
}
