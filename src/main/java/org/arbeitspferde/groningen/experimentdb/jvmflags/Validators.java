package org.arbeitspferde.groningen.experimentdb.jvmflags;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;

class Validators {
  static final Validator INTEGER = new Validator() {
    private final Range<Long> inherentAcceptableValues = Range.closed((long) Integer.MIN_VALUE,
        (long) Integer.MAX_VALUE);

    @Override
    public void validate(final JvmFlag cla, final long proposedValue) throws IllegalArgumentException {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkArgument(inherentAcceptableValues.contains(proposedValue));
      Preconditions.checkArgument(
          cla.getAcceptableValueRange().contains(proposedValue),
          "The flag %s with range %s cannot contain proposed value %s.",
          cla.getName(), cla.getAcceptableValueRange(),
          proposedValue);
      }
  };

  static final Validator BOOLEAN = new Validator() {
    private final Range<Long> inherentAcceptableValues = Range.closed(0L, 1L);

    @Override
    public void validate(final JvmFlag cla, final long proposedValue)
        throws IllegalArgumentException {
      Preconditions.checkNotNull(cla, "cla may not be null.");
      Preconditions.checkArgument(inherentAcceptableValues.contains(proposedValue));
    }

  };
}
