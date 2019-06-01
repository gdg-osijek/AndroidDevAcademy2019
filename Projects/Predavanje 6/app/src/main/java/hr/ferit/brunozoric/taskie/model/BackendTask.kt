package hr.ferit.brunozoric.taskie.model

import com.google.gson.annotations.SerializedName

data class BackendTask(
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("isFavorite") val isFavorite: Boolean,
    @SerializedName("taskPriority") val taskPriority: Int,
    @SerializedName("isCompleted") val isCompleted: Boolean
)