package com.dinesh.foregroundservices.presentation.quote_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinesh.foregroundservices.presentation.MainViewModel
import com.dinesh.foregroundservices.utils.formatTimestamptoDMY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    fetchQuote : () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Quote's")}, actions = {
                IconButton(onClick = fetchQuote) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                }
            })
        }){
            if (uiState.data.isEmpty()){
                Box (
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){

                    Text(text = "Nothing Found")
                }
            }else{
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    items (uiState.data){
                        Card (
                            modifier = Modifier
                                .padding(8.dp)
                                .fillParentMaxWidth()
                        ){
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text =it.quote)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Author : ${it.author}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = formatTimestamptoDMY(it.time).toString())
                                    Text(text = it.workType.toString())
                                }
                            }

                        }
                    }
                }
            }
    }

}