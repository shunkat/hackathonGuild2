package guild.hackathon.ui.Login

import cafe.adriel.voyager.core.model.ScreenModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import guild.hackathon.net.UserApi
import guild.hackathon.model.User
import guild.hackathon.utils.settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginScreenModel(
    private val userApi: UserApi,
): ScreenModel {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun signUp(name: String, email: String, password: String) {
        coroutineScope.launch {
            val user = registerUser(name, email, password)
            // Handle the user registration result here
        }
    }

    private suspend fun registerUser(name: String, email: String, password: String): User? {
        val user = userApi.register(name, email, password)
        user?.let {
            // Save the user data to settings
            val userJson = Json.encodeToString(it)
            settings["user"] = userJson
            // Save the email and password to settings
            settings["email"] = email
            settings["password"] = password
        }
        return user
    }

    fun getSavedCredentials(): Pair<String?, String?> {
        val email = settings.getStringOrNull("email")
        val password = settings.getStringOrNull("password")
        return Pair(email, password)
    }

    fun login(email: String, password: String) {
        coroutineScope.launch {
            userApi.login(email, password)
        }
    }
}