package com.pa.pakotlin.data.model

import com.google.gson.annotations.SerializedName

data class Version(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)

data class GameIndices(
    @SerializedName("version") var version: List<Version>
)

data class Species(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)

data class VersionGroupDetails(
    @SerializedName("level_learned_at") var levelLearnedAt: Int,
    @SerializedName("move_learn_method") var moveLearnMethod: Species,
    @SerializedName("version_group") var versionGroup: Species

)

data class Moves(
    @SerializedName("version_group_details") var versionGroupDetails: List<VersionGroupDetails>

)

data class RequestPokemon(
    @SerializedName("id") var id: Int,
    @SerializedName("weight") var weight: Int,
    @SerializedName("species") var species: Species,
    @SerializedName("name") var name: String,
    @SerializedName("game_indices") var gameIndices: List<GameIndices>
    // @SerializedName("moves") var moves: List<Moves>

)

// name
// game_indices->version->name
// moves->version_group_details->move_learn_method->name
