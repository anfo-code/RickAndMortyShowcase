package com.example.rickyandmortyshowcase.adaptive_ui_tests

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.rickyandmortyshowcase.characters.ui.viewmodel.CharactersState
import com.example.rickyandmortyshowcase.characters.ui.CharactersMainScreen
import org.junit.Rule
import org.junit.Test

class RaMSAdaptiveUiTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottom() {
        //Set up compact window
        composeTestRule.setContent {
            CharactersMainScreen(
                state = CharactersState(),
                windowSize = WindowWidthSizeClass.Compact,
                onSelectCharacter = {},
                onEnterSearch = {},
                onEnterCharacters = {},
                onEnterFavorites = {},
                onAddCharacterToFavorites = {},
                onFilterCharacters = {},
                onRemoveCharacterFromFavorites = {}
            )
        }
        composeTestRule.onNodeWithTag("Navigation Bottom").assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        //Set up compact window
        composeTestRule.setContent {
            CharactersMainScreen(
                state = CharactersState(),
                windowSize = WindowWidthSizeClass.Medium,
                onSelectCharacter = {},
                onEnterSearch = {},
                onEnterCharacters = {},
                onEnterFavorites = {},
                onAddCharacterToFavorites = {},
                onFilterCharacters = {},
                onRemoveCharacterFromFavorites = {}
            )
        }
        composeTestRule.onNodeWithTag("Navigation Rail").assertExists()
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        //Set up compact window
        composeTestRule.setContent {
            CharactersMainScreen(
                state = CharactersState(),
                windowSize = WindowWidthSizeClass.Expanded,
                onSelectCharacter = {},
                onEnterSearch = {},
                onEnterCharacters = {},
                onEnterFavorites = {},
                onAddCharacterToFavorites = {},
                onFilterCharacters = {},
                onRemoveCharacterFromFavorites = {}
            )
        }
        composeTestRule.onNodeWithTag(
            "Navigation Drawer"
        ).assertExists()
    }
}