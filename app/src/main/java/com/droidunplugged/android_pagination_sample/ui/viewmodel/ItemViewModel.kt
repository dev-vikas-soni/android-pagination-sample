package com.droidunplugged.android_pagination_sample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.droidunplugged.android_pagination_sample.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {
    /*Offset based pagination*/
    val offsetBasedRecipes: Flow<PagingData<Recipe>> = repository.getRecipesByOffset().flow

    /*Cursor based pagination*/
    val recipes: Flow<PagingData<Recipe>> = repository.getRecipesByCursor().flow
}
