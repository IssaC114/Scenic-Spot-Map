package com.example.scenicspottomap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.scenicspottomap.ui.theme.MyApplicationTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name") ?: "Unknown"
        val imageRes = intent.getIntExtra("imageRes", -1)

        // 使用簡單的佔位符來代替實際說明
        val descriptions = mapOf(
            "羅馬競技場" to "說明1",
            "萬神殿" to "說明2",
            "特萊維噴泉" to "說明1",
            "聖母大殿" to "說明2",
            "米開朗基羅廣場" to "說明1",
            "烏菲茲美術館" to "說明2",
            "聖母百花大教堂" to "說明1",
            "韋奇奧宮" to "說明2"
        )

        // 獲取對應景點的說明文字
        val description = descriptions[name] ?: "這是${name}的詳細說明。"

        setContent {
            MyApplicationTheme {
                DetailScreen(name, imageRes, description)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(name: String, imageRes: Int, description: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = name) },
            navigationIcon = {
                IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "返回")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
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
            text = description,
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
