package guild.hackathon.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import io.ktor.http.Url


private val LocalCustomFontsFlow = staticCompositionLocalOf {
    MutableStateFlow<List<Font>>(listOf())
}

private data class FontSet(
    val fileName: String,
    val weight: FontWeight,
    val style: FontStyle,
)

private val fonts: List<FontSet> = listOf(
    FontSet("NotoSansJP-Medium.ttf", FontWeight.Medium, FontStyle.Normal),
    FontSet("NotoSansJP-Regular.ttf", FontWeight.W400, FontStyle.Normal),
    FontSet("NotoSansJP-Black.ttf", FontWeight.Black, FontStyle.Normal),
    FontSet("NotoSansJP-Bold.ttf", FontWeight.Bold, FontStyle.Normal),
    FontSet("NotoSansJP-ExtraBold.ttf", FontWeight.ExtraBold, FontStyle.Normal),
    FontSet("NotoSansJP-ExtraLight.ttf", FontWeight.ExtraLight, FontStyle.Normal),
    FontSet("NotoSansJP-Light.ttf", FontWeight.Light, FontStyle.Normal),
    FontSet("NotoSansJP-SemiBold.ttf", FontWeight.SemiBold, FontStyle.Normal),
    FontSet("NotoSansJP-Thin.ttf", FontWeight.Thin, FontStyle.Normal),
)

@Composable
public actual fun rememberCustomFontStyle(): FontFamily {
    val fontsFlow: MutableStateFlow<List<Font>> = LocalCustomFontsFlow.current

    LaunchedEffect(Unit) {
        fonts.forEach { fontSet ->
            runCatching {
                HttpClient(Js)
                    .get(Url("/fonts/${fontSet.fileName}"))
            }.onFailure {
                it.printStackTrace()
            }.onSuccess { response ->
                val byteArray = response.readBytes()
                fontsFlow.update {
                    it.plus(
                        Font(
                            identity = fontSet.fileName,
                            data = byteArray,
                            weight = fontSet.weight,
                            style = fontSet.style,
                        ),
                    )
                }
            }
        }
    }
    return fontsFlow.map {
        if (it.isEmpty()) {
            FontFamily.Default
        } else {
            FontFamily(it)
        }
    }.collectAsState(FontFamily.Default).value
}