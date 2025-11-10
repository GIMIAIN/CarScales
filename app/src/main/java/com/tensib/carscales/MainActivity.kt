package com.tensib.carscales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.tensib.carscales.ui.screens.CarScalesApp
import com.tensib.carscales.ui.theme.CarScalesAppTheme
import com.tensib.carscales.viewmodel.CarScalesViewModel
import com.tensib.carscales.viewmodel.CarScalesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarScalesAppTheme {
                val owner = LocalViewModelStoreOwner.current
                owner?.let {
                    val viewModel: CarScalesViewModel = viewModel(
                        it,
                        "CarScalesViewModel",
                        CarScalesViewModelFactory(application)
                    )
                    CarScalesApp(viewModel)
                }
            }
        }
    }
}