package com.droidunplugged.android_pagination_sample.data.remote

import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /*Offset based pagination*/
    @GET("recipes")
    suspend fun getRecipesByOffset(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<RecipesResponse>

    /*Cursor based pagination*/
    @GET("recipes")
    suspend fun getRecipesByCursor(
        @Query("limit") limit: Int,
        @Query("cursor") cursor: String
    ): Response<CursorRecipesResponse>
}

@JsonClass(generateAdapter = true)
data class RecipesResponse(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@JsonClass(generateAdapter = true)
data class CursorRecipesResponse(
    val recipes: List<Recipe>,
    val nextCursor: String?,
    val prevCursor: String?
)