package org.arbeitspferde.groningen.experimentdb.jvmflags;

import junit.framework.TestCase;

/**
 * Tests for {@link Validators}.
 */
public class ValidatorsTest extends TestCase {
  public void test_INTEGER_FORMATTER_validate_AdaptiveSizeDecrementFactor_InvalidValues() {
    try {
      Validators.INTEGER.validate(JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, -1L);
      fail("JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR disallows -1.");
    } catch (final IllegalArgumentException e) {
    }

    try {
      Validators.INTEGER.validate(JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, 101L);
      fail("JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR disallows 101.");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void test_INTEGER_FORMATTER_validate_AdaptiveSizeDecrementFactor_ValidValues() {
    try {
      Validators.INTEGER.validate(JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, 1L);
    } catch (final IllegalArgumentException e) {
      fail("JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR allows 1.");
    }

    try {
      Validators.INTEGER.validate(JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, 50L);
    } catch (final IllegalArgumentException e) {
      fail("JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR allows 50");
    }

    try {
      Validators.INTEGER.validate(JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR, 100L);
    } catch (final IllegalArgumentException e) {
      fail("JvmFlag.ADAPTIVE_SIZE_DECREMENT_SCALE_FACTOR allows 100");
    }
  }

  public void test_BOOLEAN_FORMATTER_validate_CMSIncrementalMode_InvalidValues() {
    try {
      Validators.BOOLEAN.validate(JvmFlag.CMS_INCREMENTAL_MODE, -1L);
      fail("JvmFlags.CMS_INCREMENTAL_MODE disallows -1.");
    } catch (final IllegalArgumentException e) {
    }

    try {
      Validators.BOOLEAN.validate(JvmFlag.CMS_INCREMENTAL_MODE, 2L);
      fail("JvmFlags.CMS_INCREMENTAL_MODE disallows 2.");
    } catch (final IllegalArgumentException e) {
    }
  }

  public void test_BOOLEAN_FORMATTER_validate_CMSIncrementalMode_ValidValues() {
    try {
      Validators.BOOLEAN.validate(JvmFlag.CMS_INCREMENTAL_MODE, 0L);
    } catch (final IllegalArgumentException e) {
      fail("JvmFlags.CMS_INCREMENTAL_MODE allows 0.");
    }

    try {
      Validators.BOOLEAN.validate(JvmFlag.CMS_INCREMENTAL_MODE, 1L);
    } catch (final IllegalArgumentException e) {
      fail("JvmFlags.CMS_INCREMENTAL_MODE allows 1.");
    }
  }
}
