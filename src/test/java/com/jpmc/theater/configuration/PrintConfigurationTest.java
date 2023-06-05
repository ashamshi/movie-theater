package com.jpmc.theater.configuration;

import com.jpmc.theater.service.print.JsonPrintService;
import com.jpmc.theater.service.print.PrintService;
import com.jpmc.theater.service.print.SimpleTextPrintService;
import org.junit.jupiter.api.Test;

import java.lang.module.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class PrintConfigurationTest {

  @Test
  void shouldReturnJsonPrintServiceIfCreatedWithPrintToJsonTrue() {
    // Given
    PrintConfiguration target = new PrintConfiguration().withPrintToJson(true);
    // When
    PrintService result = target.printService();
    // Then
    assertTrue(result instanceof JsonPrintService);
  }

  @Test
  void shouldReturnSimpleTextPrintServiceIfCreatedWithPrintToJsonFalse() {
    // Given
    PrintConfiguration target = new PrintConfiguration().withPrintToJson(false);
    // When
    PrintService result = target.printService();
    // Then
    assertTrue(result instanceof SimpleTextPrintService);
  }
}
