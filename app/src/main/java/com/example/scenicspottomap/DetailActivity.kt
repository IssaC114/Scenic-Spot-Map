package com.example.scenicspottomap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scenicspottomap.ui.theme.MyApplicationTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name")
        val imageRes = intent.getIntExtra("imageRes", -1)

        // 添加日誌以確認接收到的數據
        Log.d("DetailActivity", "Received name: $name, imageRes: $imageRes")

        if (name == null || imageRes == -1) {
            Log.e("DetailActivity", "Invalid intent extras: name=$name, imageRes=$imageRes")
            finish() // Close the activity if the intent extras are invalid
            return
        }

        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DetailScreen(name, imageRes)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(name: String, imageRes: Int) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "這是${name}的詳細說明。",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${name}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(context.packageManager)?.let {
                context.startActivity(mapIntent)
            }
        }) {
            Text(text = "透過 Google Map 檢視位置")
        }
    }
}
