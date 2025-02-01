package com.route.todoappc41gmonthu.fragments

import java.util.Calendar


fun Calendar.setDate(year: Int, month: Int, dayOfMonth: Int) {
    set(Calendar.YEAR, year)
    set(Calendar.MONTH, month)
    set(Calendar.DAY_OF_MONTH, dayOfMonth)
}
