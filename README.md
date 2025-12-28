# Android Paging 3 Library Demo

A practical Kotlin Android project demonstrating all major types of pagination using the Paging 3 library. This repo covers basic and advanced use cases, including page-based, offset-based, cursor-based, and local-only pagination, with examples for both RecyclerView and Jetpack Compose. Perfect for learning how to efficiently load and display large datasets in Android apps[web:1][web:8][web:23].

## Features

- Clean, modular code structure
- Support for both network and local data sources
- Loading, error, and empty state handling
- Header and footer views for loading states
- List separators and diffing with DiffUtil
- Offline support and caching
- MVVM architecture
- Kotlin coroutines and Flow

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle dependencies
4. Run the app

## Dependencies

- Android API 21+
- Kotlin 1.8+
- Paging 3.2+
- Retrofit (for network examples)
- Room (for local examples)
- Jetpack Compose (for Compose UI examples)[web:23][web:25]

## Usage Examples

- **Page-based:** Fetch data using page number and page size parameters
- **Offset-based:** Fetch data using offset and limit parameters
- **Cursor-based:** Fetch data using cursor or key-based pagination
- **Local-only:** Load data from Room database
- **Remote + local:** Sync network and local data with RemoteMediator
- **UI:** Display data in RecyclerView or Jetpack Compose LazyColumn

## Screenshots

<!-- Add screenshots here -->

## Further Reading

- [Official Paging 3 Documentation][web:1]
- [Paging 3 Best Practices][web:24]
- [Sample Paging 3 Project on GitHub][web:23][web:25]


## 1. The "Magic Number 30" (Default Initial Load Size)
- **The Problem:** You noticed the first API call requested 30 items even though your pageSize was set to 10. The Cause: In PagingConfig, the property initialLoadSize defaults to 3 times your pageSize. The Logic: This is a performance optimization. The library assumes that when a user first opens a screen, you want to fill the scroll container completely and have a small buffer ready so the user doesn't see a loading spinner the moment they start their first scroll.
## 2. Aggressive Automatic API Calling (Prefetch Distance)
- **The Problem:** The app was calling the second and third pages automatically without you even touching the screen. The Cause: The default prefetchDistance is equal to your pageSize (10). The Logic: If your screen is large enough to display 8 or 9 items, and your page size is 10, the library calculates that you are "within 10 items of the end of the list." Since the distance to the end (1 or 2 items) is less than the prefetchDistance (10), it triggers the next page load immediately to ensure a seamless "infinite scroll" experience. The Fix: You need to explicitly set prefetchDistance = 2 or 3 in your PagingConfig to make it less aggressive.
## 3. The "Infinite Loading" Trigger (Accessing Indices in Keys)
- **The Problem:** Even with a small prefetchDistance, the library was sometimes loading everything at once. The Cause: In your initial RecipeListScreen (before the fix), you were likely accessing the recipes list inside the key block of the LazyColumn using recipes[index]. The Logic:•In Paging 3, calling recipes[index] is a signal to the library that "the user is looking at this item."•LazyColumn often looks ahead at keys to prepare for animations or layout.•If the key block "touches" recipes[index], the Paging library thinks you are displaying that index. If that index is near the end, it triggers the next page. This often creates a loop where the list grows, the key block looks at the new items, and another page is fetched. The Fix: Using recipes.itemKey { it.id } (which you have now implemented) is the correct way. It retrieves the key from the cached data without sending a "signal" to the Paging library to load more data.

By understanding these three defaults—Initial Multiplyer, Prefetch Distance, and Index Access Signaling—you can fully control when and how your network is hit.
