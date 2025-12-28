package com.droidunplugged.android_pagination_sample.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.droidunplugged.android_pagination_sample.data.model.Recipe
import com.droidunplugged.android_pagination_sample.ui.viewmodel.RecipeViewModel

@Composable
fun RecipeListScreen(
    modifier: Modifier,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipes = viewModel.recipes.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
        items(
            count = recipes.itemCount,
            key = recipes.itemKey { it.id }
        ) { index ->
            val recipe = recipes[index]
            if (recipe != null) {
                RecipeItem(recipe = recipe)
            }
        }
        // Optional: Add loading and error states
        if (recipes.loadState.append is LoadState.Loading) {
            item { CircularProgressIndicator() }
        }
        if (recipes.loadState.append is LoadState.Error) {
            item { Text("Error loading recipes") }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prep Time: ${recipe.prepTimeMinutes} mins",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Cook Time: ${recipe.cookTimeMinutes} mins",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Servings: ${recipe.servings}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Difficulty: ${recipe.difficulty}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Cuisine: ${recipe.cuisine}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Rating: ${recipe.rating}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Review Count: ${recipe.reviewCount}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Meal Type: ${recipe.mealType.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Tags: ${recipe.tags.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}