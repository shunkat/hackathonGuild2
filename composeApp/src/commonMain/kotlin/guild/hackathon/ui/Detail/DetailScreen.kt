package guild.hackathon.ui.Detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import guild.hackathon.di.getScreenModel
import guild.hackathon.model.Hackathon
import guild.hackathon.openUrl
import guild.hackathon.theme.rememberCustomFontStyle

class DetailScreen(private val hackathon: Hackathon): Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DetailScreenModel>()

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "作成日: ${hackathon.createdAt}", fontFamily = rememberCustomFontStyle())
            Text(text = "タイトル: ${hackathon.title}", fontFamily = rememberCustomFontStyle())
            Text(text = "期間: ${hackathon.duration}", fontFamily = rememberCustomFontStyle())
            Text(text = "申し込み締切日: ${hackathon.applyFinishDate}", fontFamily = rememberCustomFontStyle())
            Text(text = "詳細: ${hackathon.detail}", fontFamily = rememberCustomFontStyle())
            Text(
                text = "詳細URL: ${hackathon.detailUrl}",
                fontFamily = rememberCustomFontStyle(),
                modifier = Modifier.clickable { openUrl(hackathon.detailUrl) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
            }) {
                Text(text = "チーム募集を開始する", fontFamily = rememberCustomFontStyle())
            }
        }
    }
}