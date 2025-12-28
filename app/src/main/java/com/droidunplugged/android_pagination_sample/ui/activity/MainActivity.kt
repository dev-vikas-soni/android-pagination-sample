package com.droidunplugged.android_pagination_sample.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.droidunplugged.android_pagination_sample.ui.compose.RecipeListScreen
import com.droidunplugged.android_pagination_sample.ui.theme.AndroidpaginationsampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaginationsampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}