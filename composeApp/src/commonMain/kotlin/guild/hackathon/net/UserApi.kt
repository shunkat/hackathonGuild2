package guild.hackathon.net

import guild.hackathon.model.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

interface UserApi {
    suspend fun register(name: String, email: String, password: String): User?
}

internal class UserApiImpl(
    private val client : SupabaseClient,
): UserApi {
    private val table = client.postgrest["user"]

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): User? {
        // Use Supabase auth to register the user
        val result = client.gotrue.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        return if (result != null) {
            // If registration is successful, insert the user into the "user" table
            val user = User(result.id?.toInt(), name, email, null)
            table.insert(user).decodeSingle<User>()
        } else {
            null
        }
    }
}