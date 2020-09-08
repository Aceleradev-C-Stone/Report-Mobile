package com.upco.report.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.upco.report.R
import com.upco.report.extension.findNavController
import com.upco.report.extension.hide
import com.upco.report.extension.show
import com.upco.report.feature.list.LogsListFragmentDirections
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.appbar.toolbar
import kotlinx.android.synthetic.main.appbar.appbar

class ReportActivity: AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        setupNavigationController()
        setupToolbar()
        setupFab()
    }

    private fun setupNavigationController() {
        navController = findNavController(R.id.navHostFragment)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.loginFragment,
                R.id.registerFragment -> hideToolbarAndFab()
                R.id.logsListFragment -> showToolbarAndFab()
                else -> showOnlyToolbar()
            }
        }
    }

    private fun setupToolbar() {
        val config = AppBarConfiguration(setOf(R.id.loginFragment, R.id.logsListFragment))
        toolbar.setupWithNavController(navController, config)
    }

    private fun setupFab() {
        btnAdd.setOnClickListener {
            val destination = LogsListFragmentDirections.toNewLogFragment()
            navController.navigate(destination)
        }
    }

    private fun hideToolbarAndFab() {
        hideToolbar()
        hideFab()
    }

    private fun showToolbarAndFab() {
        showToolbar()
        expandAppBar()
        showFab()
    }

    private fun showOnlyToolbar() {
        showToolbar()
        expandAppBar()
        hideFab()
    }

    private fun showFab() = btnAdd.show()
    private fun hideFab() = btnAdd.hide()

    private fun showToolbar() = toolbar.show()
    private fun hideToolbar() = toolbar.hide()

    private fun expandAppBar() = with(appbar) {
        setExpanded(true, false)
    }
}