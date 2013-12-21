package org.arbeitspferde.groningen.common;

import java.util.logging.Logger;

import com.google.common.util.concurrent.AbstractExecutionThreadService;

/**
 * Enables groningen to run indefinitely as a service.
 */
public class GroningenService extends AbstractExecutionThreadService {

  private static final Logger log = Logger.getLogger(GroningenService.class.getCanonicalName());

  @Override
  protected void run() throws Exception {
    log.info("Running Groningen Service");
    synchronized(this) {
      while (isRunning()) {
        wait();
      }
    }
  }

}
