package com.droidunplugged.android_pagination_sample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.droidunplugged.android_pagination_sample.data.remote.ApiService

class RecipePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Recipe>() {

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val nextPage = params.key ?: 0
        return try {
            val response = apiService.getRecipesByOffset(limit = params.loadSize, skip = nextPage)
            val recipes = response.body()?.recipes ?: emptyList()
            LoadResult.Page(
                data = recipes,
                prevKey = if (nextPage == 0) null else nextPage - params.loadSize,
                nextKey = if (recipes.isEmpty()) null else nextPage + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}