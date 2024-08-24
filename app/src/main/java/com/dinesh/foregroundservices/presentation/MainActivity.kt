package com.dinesh.foregroundservices.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinesh.foregroundservices.presentation.quote_app.MainScreen
import com.dinesh.foregroundservices.presentation.services_demo.RunningService
import com.dinesh.foregroundservices.presentation.ui.theme.ForeGroundServicesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }


        setContent {
            ForeGroundServicesTheme {
                Surface (modifier = Modifier.safeContentPadding()){

                    val viewModel = hiltViewModel<MainViewModel>()
                    MainScreen(viewModel = viewModel) {
                        viewModel.getQuote()
                    }
                }




//                Column (
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ){
//
//                    Button(onClick = {
//                        Intent(applicationContext, RunningService::class.java).also {
//                            it.action = RunningService.Actions.START.toString()
//                            startService(it)
//                        }
//                    }) {
//                            Text(text = "Start Service")
//                    }
//
//                    Button(onClick = {
//                        Intent(applicationContext, RunningService::class.java).also {
//                            it.action = RunningService.Actions.STOP.toString()
//                            startService(it)
//                        }
//                    }) {
//                        Text(text = "Stop Service")
//                    }
//
//                }
            }
        }
    }
}