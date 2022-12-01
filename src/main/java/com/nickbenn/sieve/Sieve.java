/*
 *  Copyright 2022 Nicholas Bennett.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nickbenn.sieve;

import java.util.BitSet;

public class Sieve {

  static BitSet sieve(int limit) {
    var limitRoot = (int) Math.sqrt(limit);
    var candidates = new BitSet(limit + 1);
    candidates.set(2, limit + 1);
    for (int prime = candidates.nextSetBit(0); prime <= limitRoot; prime = candidates.nextSetBit(prime + 1)) {
      for (int multiple = prime * prime; multiple <= limit; multiple += prime) {
        candidates.clear(multiple);
      }
    }
    return candidates;
  }

  public static void main(String... args) {
    var upperBound = 10_000_000;
    var start = System.currentTimeMillis();
    var primes = sieve(upperBound);
    var end = System.currentTimeMillis();
    System.out.printf("""
            Java Sieve with BitSet:
            %1$,d primes found between %2$,d and %3$,d in %4$,d ms.
            """,
        primes.cardinality(), primes.nextSetBit(0), primes.previousSetBit(primes.size() - 1),
        end - start);
  }

}
