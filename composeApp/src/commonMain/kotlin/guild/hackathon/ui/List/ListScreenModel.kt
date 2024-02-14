package guild.hackathon.ui.List

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import guild.hackathon.model.Hackathon
import guild.hackathon.net.HackathonApi
import kotlinx.coroutines.launch

class ListScreenModel(
    private val hackathonApi: HackathonApi,
): ScreenModel {
    var hackathons = mutableStateOf(listOf<Hackathon>())
    var errorMessage = mutableStateOf<String?>(null)

    init {
        loadHackathons()
    }

    private fun loadHackathons() {
        coroutineScope.launch {
            kotlin.runCatching {
                hackathonApi.getHackathons()
            }.onSuccess {
                hackathons.value = it ?: emptyList()
            }.onFailure {
                errorMessage.value = "something went wrong"
            }
        }
    }}