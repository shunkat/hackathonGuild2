package guild.hackathon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hackathon(
    @SerialName("id")
    val id: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("title")
    val title: String,
    @SerialName("duration")
    val duration: String,
    @SerialName("apply_finish_date")
    val applyFinishDate: String,
    @SerialName("detail")
    val detail: String,
    @SerialName("detail_url")
    val detailUrl: String,
)