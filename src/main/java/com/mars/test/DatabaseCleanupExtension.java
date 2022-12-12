package com.mars.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
public class DatabaseCleanupExtension implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) {
    final var applicationContext = SpringExtension.getApplicationContext(context);
    final var databaseCleanup = applicationContext.getBean(DatabaseCleanup.class);
    try {
      databaseCleanup.execute();
    } catch (Exception e) {
      log.error("Failure occurs while cleaning up database", e);
    }
  }
}
