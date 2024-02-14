package guild.hackathon.net

import guild.hackathon.model.Hackathon
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

interface HackathonApi {
    suspend fun getHackathons(): List<Hackathon>?
}

internal class HackathonApiImpl(
    private val client : SupabaseClient,
): HackathonApi {
    override suspend fun getHackathons(): List<Hackathon>? {
        val response = client.postgrest["hackathons"].select().decodeList<Hackathon>()
        return response
    }
}