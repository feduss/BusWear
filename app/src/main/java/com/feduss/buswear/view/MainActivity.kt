package com.feduss.buswear.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.feduss.buswear.model.MainActivityViewModel
import com.feduss.buswear.view.nav.NavView

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavView(
                context = this,
                db = viewModel.getDb(this)
            )
        }
    }
}

