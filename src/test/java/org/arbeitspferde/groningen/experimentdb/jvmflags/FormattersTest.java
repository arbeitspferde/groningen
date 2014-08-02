/* Copyright 2012 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.arbeitspferde.groningen.experimentdb.jvmflags;

import static org.junit.Assert.assertThat;

import com.google.common.collect.ImmutableList;

import junit.framework.TestCase;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * Tests for {@link Formatters}.
 */
public class FormattersTest extends TestCase {
  public void test_INTEGER_FORMATTER_asArgumentString_DisallowsNullArguments() {
    try {
      Formatters.INTEGER.asArgumentString(null, 0L);
      fail("Formatters.INTEGER should disallow null arguments.");
    } catch (final NullPointerException e) {
    }
  }

  public void test_INTEGER_FORMATTER_asArgumentString_AdaptiveSizeDecrementScaleFactor() {
    final ImmutableList<String> actual = Formatters.INTEGER.asArgumentString(
        JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, 2L);

    assertThat(actual, matches("-XX:AdaptiveSizeDecrementScaleFactor=2"));
  }

  public void test_INTEGER_FORMATTER_asArgumentString_MaxNewSize() {
    final ImmutableList<String> actual = Formatters.INTEGER.asArgumentString(
        JvmFlag.MAX_NEW_SIZE, 3L);

    assertThat(actual, matches("-XX:MaxNewSize=3m"));
  }

  public void test_INTEGER_FORMATTER_asRegularExpression_AdaptiveSizeDecrementFactor() {
    final ImmutableList<String> actual = Formatters.INTEGER.asRegularExpressionString(
        JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR);

    assertThat(actual, matches("-XX:AdaptiveSizeDecrementScaleFactor=\\d+\\b"));
  }

  public void test_INTEGER_FORMATTER_asRegularExpression_MaxNewSize() {
    final ImmutableList<String> actual = Formatters.INTEGER.asRegularExpressionString(
        JvmFlag.MAX_NEW_SIZE);
    assertThat(actual, matches("-XX:MaxNewSize=\\d+[kKmMgG]?\\b"));
  }

  public void test_BOOLEAN_FORMATTER_asArgumentString_DisallowsNullArguments() {
    try {
      Formatters.BOOLEAN.asArgumentString(null, 0L);
      fail("Formatters.BOOLEAN should disallow null arguments.");
    } catch (final NullPointerException e) {
    }
  }

  public void test_BOOLEAN_FORMATTER_asArgumentString_CMSIncrementalMode_true() {
    final ImmutableList<String> actual = Formatters.BOOLEAN.asArgumentString(
      JvmFlag.CMS_INCREMENTAL_MODE, 1L);

    assertThat(actual, matches("-XX:+CMSIncrementalMode"));
  }

  public void test_BOOLEAN_FORMATTER_asArgumentString_CMSIncrementalMode_false() {
    final ImmutableList<String> actual = Formatters.BOOLEAN.asArgumentString(
      JvmFlag.CMS_INCREMENTAL_MODE, 0L);

    assertThat(actual, matches("-XX:-CMSIncrementalMode"));
  }

  public void test_BOOLEAN_FORMATTER_asRegularExpression_CMSIncrementalMode() {
    final ImmutableList<String> actual = Formatters.BOOLEAN.asRegularExpressionString(
      JvmFlag.CMS_INCREMENTAL_MODE);

    assertThat(actual, matches("-XX:[+-]CMSIncrementalMode"));
  }

  private static TypeSafeMatcher<ImmutableList<String>> matches(final String ...expected) {
    return new TypeSafeMatcher<ImmutableList<String>>() {
      @Override
      public boolean matchesSafely(ImmutableList<String> item) {
        return ImmutableList.copyOf(expected).equals(item);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("expected ")
            .appendValue(expected);
      }
    };
  }
}
