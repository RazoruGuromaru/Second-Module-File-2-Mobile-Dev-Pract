package com.example.secondmodulefile2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class WaterTrackerUiTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun addWaterButtonIncreasesAmount() {
        composeTestRule.setContent {
            WaterTracker()
        }
        composeTestRule
            .onNodeWithText("100 мл")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("+250 мл")
            .performClick()

        composeTestRule
            .onNodeWithText("350 мл")
            .assertIsDisplayed()
    }

    @Test
    fun finishDayButtonWorks() {
        composeTestRule.setContent {
            WaterTracker()
        }

        repeat(6) {
            composeTestRule
                .onNodeWithText("+250 мл")
                .performClick()
        }

        composeTestRule
            .onNodeWithText("1600 мл")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Завершить день")
            .performClick()

        composeTestRule
            .onNodeWithText("0 мл")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("1 дней подряд")
            .assertIsDisplayed()
    }

    @Test
    fun streakCounterWorks() {
        composeTestRule.setContent {
            WaterTracker()
        }

        composeTestRule
            .onNodeWithText("0 дней подряд")
            .assertIsDisplayed()

        repeat(6) {
            composeTestRule
                .onNodeWithText("+250 мл")
                .performClick()
        }

        composeTestRule
            .onNodeWithText("Завершить день")
            .performClick()

        composeTestRule
            .onNodeWithText("1 дней подряд")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("+250 мл")
            .performClick()

        composeTestRule
            .onNodeWithText("Завершить день")
            .performClick()

        composeTestRule
            .onNodeWithText("0 дней подряд")
            .assertIsDisplayed()
    }
}