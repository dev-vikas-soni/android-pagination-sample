package com.droidunplugged.android_pagination_sample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.droidunplugged.android_pagination_sample.data.remote.ApiService

class CursorPagingSource(
    private val apiService: ApiService
) : PagingSource<String, Recipe>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Recipe> {
        val cursor = params.key ?: "" // Start with empty cursor for the first page
        return try {
            val response = apiService.getRecipesByCursor(
                limit = params.loadSize,
                cursor = cursor
            )
            val recipes = response.body()?.recipes ?: emptyList()
            val nextCursor = if (recipes.isEmpty()) null else response.body()?.nextCursor
            val prevCursor = if (cursor.isEmpty()) null else response.body()?.prevCursor

            LoadResult.Page(
                data = recipes,
                prevKey = prevCursor,
                nextKey = nextCursor
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Recipe>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id.toString()
        }
    }
}