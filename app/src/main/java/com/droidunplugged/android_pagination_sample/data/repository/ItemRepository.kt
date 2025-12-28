package com.droidunplugged.android_pagination_sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.droidunplugged.android_pagination_sample.data.paging.CursorPagingSource
import com.droidunplugged.android_pagination_sample.data.paging.RecipePagingSource
import com.droidunplugged.android_pagination_sample.data.remote.ApiService

class RecipeRepository(
    private val apiService: ApiService
) {
    fun getRecipesByOffset(): Pager<Int, Recipe> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 2, enablePlaceholders = false),
            pagingSourceFactory = { RecipePagingSource(apiService) }
        )
    }

    fun getRecipesByCursor(): Pager<String, Recipe> {
        return Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10, prefetchDistance = 2, enablePlaceholders = false),
            pagingSourceFactory = { CursorPagingSource(apiService) }
        )
    }
}