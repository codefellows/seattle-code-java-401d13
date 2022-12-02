package com.zork.zorkmaster;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.zork.zorkmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void mainActivityTest() {
    ViewInteraction appCompatImageView = onView(
      allOf(withId(R.id.MainActivityImageViewUserProfile),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          2),
        isDisplayed()));
    appCompatImageView.perform(click());

    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.UserProfileETUsername),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          0),
        isDisplayed()));
    appCompatEditText.perform(replaceText("Zork"), closeSoftKeyboard());

    ViewInteraction materialButton = onView(
      allOf(withId(R.id.UserProfileSaveBttn), withText("Save"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          3),
        isDisplayed()));
    materialButton.perform(click());

    pressBack();

    ViewInteraction textView = onView(
      allOf(withId(R.id.MainActivityTextViewGreeting), withText("Zork"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView.check(matches(withText("Zork")));
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
