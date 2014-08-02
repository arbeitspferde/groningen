package org.arbeitspferde.groningen.experimentdb.jvmflags;

interface Validator {
  /**
   * Verify that the proposed value is acceptable.
   *
   * Implementors should include information in the thrown exception about why
   * proposedValue is unacceptable.
   *
   * @param cla The argument for which this formatter is used.
   * @param proposedValue The value to verify.
   * @throws IllegalArgumentException if proposedValue is unacceptable.
   */
  void validate(final JvmFlag cla, final long proposedValue) throws IllegalArgumentException;
}
