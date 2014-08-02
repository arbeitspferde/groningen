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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * A repository of {@link Formatter} implementations.
 *
 * {@link Formatter} would have been defined as private static interface of
 * {@link JvmFlag} and these implementations as private static classes
 * contained therein; except that the Java language specification disallows
 * enums to access internal static fields in constructors before they are
 * initialized, but the enum elements must be initialized first.
 */
class Formatters {
  /**
   * Utility class; no instantiation allowed!
   */
  private Formatters() {}

  /**
   * Provide a representation and validations for integer-based JVM Flags.
   */
  static final Formatter INTEGER = new Formatter() {
    @Override
    public ImmutableList<String> asArgumentString(final JvmFlag cla, final long value) {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkState(cla.hasName(), "cla must have name");

      return ImmutableList.of(String.format("%s%s%s%s%s", cla.getHotSpotFlagType().getPrefix(), cla.getName(),
          cla.getValueSeparator().getInfix(), value, cla.getDataSize().getSuffix()));
    }

    @Override
    public ImmutableList<String> asRegularExpressionString(final JvmFlag cla) {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkState(cla.hasName(), "cla must have name");

      return ImmutableList.of(String.format("%s%s%s\\d+%s\\b", cla.getHotSpotFlagType().getPrefix(), cla.getName(),
        cla.getValueSeparator().getInfix(), cla.getDataSize().unitFamilyAsRegexpString()));
    }
  };

  /**
   * Provide a representation and validations for boolean-based JVM Flags.
   */
  static final Formatter BOOLEAN = new Formatter() {
    private final long TRUE_AS_LONG = 1;

    @Override
    public ImmutableList<String> asArgumentString(final JvmFlag cla,
                                   final long value) {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkState(cla.hasName(), "cla must have name");

      final String plusOrMinus = value == TRUE_AS_LONG ? "+" : "-";

      return ImmutableList.of(String.format("%s%s%s", cla.getHotSpotFlagType().getPrefix(), plusOrMinus,
          cla.getName()));
    }

    @Override
    public ImmutableList<String> asRegularExpressionString(final JvmFlag cla) {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkState(cla.hasName(), "cla must have name");

      return ImmutableList.of(String.format("%s[+-]%s", cla.getHotSpotFlagType().getPrefix(), cla.getName()));
    }
  };

  static Formatter pinnedIntervalInteger(final String minimumName, final String maximumName) {
    return new Formatter() {
      @Override
      public ImmutableList<String> asArgumentString(JvmFlag cla, long value) {
        return ImmutableList.of(
            String.format("%s%s%s%s%s", cla.getHotSpotFlagType().getPrefix(), minimumName,
                cla.getValueSeparator().getInfix(), value, cla.getDataSize().getSuffix()),
            String.format("%s%s%s%s%s", cla.getHotSpotFlagType().getPrefix(), maximumName,
                cla.getValueSeparator().getInfix(), value, cla.getDataSize().getSuffix()));
      }

      @Override
      public ImmutableList<String> asRegularExpressionString(JvmFlag cla) {
        return ImmutableList.of(
            String.format("%s%s%s\\d+%s\\b", cla.getHotSpotFlagType().getPrefix(), minimumName,
                cla.getValueSeparator().getInfix(), cla.getDataSize().unitFamilyAsRegexpString()),
            String.format("%s%s%s\\d+%s\\b", cla.getHotSpotFlagType().getPrefix(), maximumName,
                cla.getValueSeparator().getInfix(), cla.getDataSize().unitFamilyAsRegexpString()));
      }
    };
  }
}
