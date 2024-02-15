package guild.hackathon.net

import guild.hackathon.model.Hackathon
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

interface HackathonApi {
    suspend fun getHackathons(): List<Hackathon>?
    suspend fun writeBulletin(user_id: String, hackathon_id: Int, role: String)
//    suspend fun getBulletin(hackathon_id: Int):
}

internal class HackathonApiImpl(
    private val client : SupabaseClient,
): HackathonApi {
    override suspend fun getHackathons(): List<Hackathon>? {
        val response = client.postgrest["hackathons"].select().decodeList<Hackathon>()
        return response
    }
    override suspend fun writeBulletin(user_id: String, hackathon_id: Int, role: String) {
        client.postgrest["bulletins"].insert(mapOf(
            "user_id" to user_id,
            "hackathon_id" to hackathon_id,
            "role" to role
        ))
    }

//    override suspend fun getBulletin(hackathon_id: Int) {
//
//    }
}