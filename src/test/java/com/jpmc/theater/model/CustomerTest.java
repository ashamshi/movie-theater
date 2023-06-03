package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
  Customer target = new Customer("name", "id");

  @Test
  void shouldReturnStringRepresentation() {
    assertEquals("name: name", target.toString());
  }

  @Test
  void shouldBeEqualWhenObjectsEqual() {
    assertEquals(new Customer("name", "id"), target);
  }

  @Test
  void shouldBeNotEqualWhenObjectsAreNotEqual() {
    assertNotEquals(new Customer("other", "other"), target);
    assertNotEquals(new Customer("name", "other"), target);
    assertNotEquals(new Customer("other", "id"), target);
    assertNotEquals(new Customer("name", null), target);
    assertNotEquals(new Customer(null, "id"), target);
    assertNotEquals(new Customer(null, null), target);
    assertNotEquals(new Object(), target);
    assertNotEquals(null, target);
  }

  @Test
  void shouldCalculateSameHashCode() {
    assertEquals(new Customer("name", "id").hashCode(), target.hashCode());
  }

  @Test
  void shouldCalculateDifferentHashCode() {
    assertNotEquals(new Customer("other", "other").hashCode(), target.hashCode());
    assertNotEquals(new Customer("name", "other").hashCode(), target.hashCode());
    assertNotEquals(new Customer("other", "id").hashCode(), target.hashCode());
    assertNotEquals(new Customer("name", null).hashCode(), target.hashCode());
    assertNotEquals(new Customer(null, "id").hashCode(), target.hashCode());
    assertNotEquals(new Customer(null, null).hashCode(), target.hashCode());
  }
  
  @Test
  void shouldGetName() {
    assertEquals("name", target.getName());
  }

  @Test
  void shouldGetId() {
    assertEquals("id", target.getId());
  }
}
