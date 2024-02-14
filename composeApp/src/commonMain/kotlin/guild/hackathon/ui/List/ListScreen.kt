package guild.hackathon.ui.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import guild.hackathon.di.getScreenModel
import guild.hackathon.theme.rememberCustomFontStyle
import guild.hackathon.ui.Detail.DetailScreen

class ListScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<ListScreenModel>()
        val hackathons = screenModel.hackathons.value

        Column(modifier = Modifier.padding(top = 16.dp)) {
            LazyColumn(contentPadding = PaddingValues(top = 16.dp)) {
                items(hackathons) { hackathon ->
                    Row(
                        modifier = Modifier.clickable {
                            navigator.push(DetailScreen(hackathon))
                        }
                    ) {
                        Text(text = hackathon.title, fontFamily = rememberCustomFontStyle())
                        Text(text = hackathon.duration, fontFamily = rememberCustomFontStyle())
                    }
                }
            }
        }
    }
}