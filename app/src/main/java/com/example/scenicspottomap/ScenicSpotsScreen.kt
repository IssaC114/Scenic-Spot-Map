package com.example.scenicspottomap

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScenicSpotsScreen() {
    val spots = listOf(
        Pair("羅馬競技場，羅馬", R.drawable.first),
        Pair("萬神殿，羅馬", R.drawable.second),
        Pair("特萊維噴泉，羅馬", R.drawable.third),
        Pair("聖母大殿，羅馬", R.drawable.fourth),
        Pair("米開朗基羅廣場，佛羅倫斯", R.drawable.fifth),
        Pair("烏菲茲美術館，佛羅倫斯", R.drawable.sixth),
        Pair("聖母百花大教堂，佛羅倫斯", R.drawable.seventh),
        Pair("韋奇奧宮，佛羅倫斯", R.drawable.eighth)
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        spots.forEach { spot ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp)
                    .clickable {
                        val intent = Intent(context, DetailActivity::class.java).apply {
                            putExtra("name", spot.first)
                            putExtra("imageRes", spot.second)
                        }
                        context.startActivity(intent)
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = spot.second),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 16.dp)
                    )
                    Text(
                        text = spot.first,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
        }
    }
}
