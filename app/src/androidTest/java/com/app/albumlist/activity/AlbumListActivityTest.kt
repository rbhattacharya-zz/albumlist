package com.app.albumlist.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.app.albumlist.CustomMatchers.Companion.withItemCount
import com.app.albumlist.R
import org.junit.Rule
import org.junit.Test

class AlbumListActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(AlbumListActivity::class.java)

    @Test
    fun recyclerViewDisplayed(){
        onView(withId(R.id.albums_list_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun countAlbums() {
        onView(withId(R.id.albums_list_recycler_view))
            .check(matches(withItemCount(100)))
    }

}