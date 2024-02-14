package guild.hackathon.ui.Detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import guild.hackathon.di.getScreenModel
import guild.hackathon.model.Hackathon
import guild.hackathon.theme.rememberCustomFontStyle

class DetailScreen(private val hackathon: Hackathon): Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DetailScreenModel>()

        Column {
            Text(text = "ID: ${hackathon.id}", fontFamily = rememberCustomFontStyle())
            Text(text = "Created At: ${hackathon.createdAt}", fontFamily = rememberCustomFontStyle())
            Text(text = "Title: ${hackathon.title}", fontFamily = rememberCustomFontStyle())
            Text(text = "Duration: ${hackathon.duration}", fontFamily = rememberCustomFontStyle())
            Text(text = "Apply Finish Date: ${hackathon.applyFinishDate}", fontFamily = rememberCustomFontStyle())
            Text(text = "Detail: ${hackathon.detail}", fontFamily = rememberCustomFontStyle())
            Text(text = "Detail URL: ${hackathon.detailUrl}", fontFamily = rememberCustomFontStyle())

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Handle click here */ }) {
                Text(text = "チーム募集を開始する")
            }
        }
    }
}