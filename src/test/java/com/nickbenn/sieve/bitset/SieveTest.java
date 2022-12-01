package com.nickbenn.sieve.bitset;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("com.nickbenn.sieve.bitset.SieveTest")
class SieveTest {

  @ParameterizedTest(name = "[{index}] limit={0}, count={1}, min={2}, max={3}")
  @CsvFileSource(resources = "/sieve-cases.csv", numLinesToSkip = 1)
  void sieve(int limit, int count, int min, int max) {
    var actual = Sieve.sieve(limit);
    assertEquals(count, actual.cardinality());
    if (actual.cardinality() > 0) {
      assertEquals(min, actual.nextSetBit(0));
      assertEquals(max, actual.previousSetBit(actual.size() - 1));
    }
  }

}