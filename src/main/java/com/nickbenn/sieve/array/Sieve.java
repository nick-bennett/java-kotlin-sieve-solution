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
package com.nickbenn.sieve.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Sieve {

  static Deque<Integer> sieve(int limit) {
    var limitRoot = (int) Math.sqrt(limit);
    var candidates = new boolean[limit + 1];
    Arrays.fill(candidates, 0, 2, false);
    Arrays.fill(candidates, 2, limit + 1, true);
    var primes = new LinkedList<Integer>();
    if (limit >= 2) {
      for (int factor = 2; factor <= limitRoot; factor++) {
        if (candidates[factor]) {
          primes.add(factor);
          for (int multiple = factor * factor; multiple <= limit; multiple += factor) {
            candidates[multiple] = false;
          }
        }
      }
      for (int i = limitRoot + 1; i <= limit; i++) {
        if (candidates[i]) {
          primes.add(i);
        }
      }
    }
    return primes;
  }

  public static void main(String... args) {
    var upperBound = 10_000_000;
    var start = System.currentTimeMillis();
    var primes = sieve(upperBound);
    var end = System.currentTimeMillis();
    System.out.printf("""
        Java Sieve with boolean[] and Deque<Integer>:
        %1$,d primes found between %2$,d and %3$,d in %4$,d ms.
        """, primes.size(), primes.getFirst(), primes.getLast(), end - start);
  }

}
