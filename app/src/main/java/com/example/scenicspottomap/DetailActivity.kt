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
            "羅馬競技場，羅馬" to "羅馬競技場（義大利語：Colosseo，又譯作羅馬鬥獸場、羅馬大角鬥場、科洛西姆或哥羅塞姆；原名弗萊文圓形劇場，拉丁語：Anfiteatro Flavio / Amphitheatrvm falvvm）是古羅馬時期最大的橢圓形角鬥場，建於公元72年-82年間，現僅存遺蹟位於現今義大利羅馬市的中心。羅馬競技場是卵形的圓形劇場，也是目前最大的圓形劇場，使用材料包括洞石、凝灰岩及磚飾面的混凝土。",
            "萬神殿，羅馬" to "萬神殿（義大利語：Pantheon，拉丁語：Pantheum），又譯萬神殿、潘提翁神殿，位於義大利羅馬，是一座古羅馬時期的宗教建築，後改建為教堂。公元609年，東羅馬帝國皇帝將萬神殿獻給教宗波尼法爵四世，後者將它更名為聖母與諸殉道者教堂（Santa Maria ad Martyres），這也是今天萬神殿的正式名稱。由於其完美的古典幾何比例，萬神殿被米開朗基羅譽為「天使的設計」。",
            "特萊維噴泉，羅馬" to "特萊維噴泉（義大利語：Fontana di Trevi），俗稱許願池，是一座位於義大利羅馬特雷維區的噴泉，也是羅馬的地標之一。噴泉建成於1762年，高26.3公尺，寬49.15公尺，是羅馬最大的巴洛克風格噴泉。遊客通常會在此地許願，因為相傳羅馬帝國軍團士兵出征前會在此投下硬幣祈願能凱旋歸來。",
            "聖母大殿，羅馬" to "聖母大殿（義大利語：Basilica di Santa Maria Maggiore）位於義大利羅馬，是天主教會的四座特級宗座聖殿之一。\n根据考证，该教堂大约建于公元四世纪，在教宗西斯笃三世任内被重建。该教堂曾经拥有过多个名字，如Santa Maria della Neve (圣玛利亚之雪)， Santa Maria Liberiana（圣玛丽娅利伯略）。在获得了圣婴摇篮（Holy Crib）之后，又改名为Santa Maria Del Presepe (圣玛丽亚摇篮)。因为它是罗马以圣母玛利亚命名的教堂中最大的，所以获得了现在的名字。18世纪时该教堂经历了一次大修，现在所看到的外表基本都是修缮后的产物。",
            "米開朗基羅廣場，佛羅倫斯" to "米開朗基羅廣場（Piazzale Michelangelo）是義大利佛羅倫斯最著名的市景俯瞰地點，位於該市阿諾河南岸，市中心以南的奧特拉諾區的山上，是一個熱門旅遊目的地，吸引了無數遊客，出現在許多明信片中。\n這座廣場興建於1869年，1865年由建築師朱塞佩·波吉（Giuseppe Poggi）設計。當時佛羅倫斯是義大利的臨時首都，進行了一項城市復興計劃，在右岸，將14世紀的城牆改建為環形馬路；而在阿諾河左岸，聖米尼亞托山8公里長的蜿蜒的山路Viale dei Colli，在山頂上修建了廣場，成為觀看城市全景的陽台。",
            "烏菲茲美術館，佛羅倫斯" to "烏菲茲美術館（義大利語：Galleria degli Uffizi）是在義大利佛羅倫斯最有歷史及最有名的一座藝術博物館。\n烏菲茲美術館的興建始於1560年。這裡一開始是喬爾喬·瓦薩里受第一代托斯卡納大公，科西莫一世·德·美第奇之託所建的市政司法機構辦公室（Uffizi為義大利語「辦公室」之意）。在瓦薩里死後由老阿方索·帕里吉及貝爾納爾多·布翁塔蘭提接手，整個龐大的宮殿式建築直到1581年才完工。",
            "聖母百花大教堂，佛羅倫斯" to "聖母百花大教堂，又稱聖母百花聖殿或花之聖母主教座堂（義大利語：Cattedrale di Santa Maria del Fiore）或佛羅倫斯主教座堂，是位於義大利佛羅倫斯的一座天主教堂，是天主教佛羅倫斯總教區的主教座堂，屬哥德式風格。始建於1296年，由建築師阿諾爾·迪·坎比奧設計，並採用了精通羅馬古建築的工匠菲利波·布魯內萊斯基著名的圓頂（穹頂）建造，1436年最終完工。",
            "韋奇奧宮，佛羅倫斯" to "韋奇奧宮（Palazzo Vecchio，意大利語意 “舊宮”）是意大利佛羅倫薩的市政廳。它俯瞰着領主廣場（Piazza della Signoria），是佛羅倫薩歷史中心，也是佛羅倫薩共和國起源和歷史的主要地點，仍然保持着作為城市政治焦點的聲譽。"
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
