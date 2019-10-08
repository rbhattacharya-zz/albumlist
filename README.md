# Albumlist
Displays a list of albums

- The solution is implemented using Activity-Fragments (though the current scope has only 1 screen with a single view) the architecture supports addition of multiple view by fragments
- The list is implemented using recycler view so that the list can be enhanced in the future with Layout Manager, Item Decorators and animations.
- Application follows the recommended ViewModel architecture to follow the separation of concerns approach
- Retrofit2 has been used as the network library as it is simple to use and comes with all required apis.

Further enhancements:-

* Paging Library by Android Jetpack can be integrated to the RecyclerView for more graceful data loading
* Include further test cases for accessibility checks
* List can be enhanced to be refreshed on demand
* List can be sorted based on user preference
* Using a LayoutManager for different available layouts in the list

