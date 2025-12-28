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
