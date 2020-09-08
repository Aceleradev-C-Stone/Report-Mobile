package com.upco.report.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(viewId: Int): NavController {
    val navHostFragment = supportFragmentManager
        .findFragmentById(viewId) as NavHostFragment
    return navHostFragment.navController
}