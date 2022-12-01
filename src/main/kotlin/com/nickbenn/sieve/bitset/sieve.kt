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
package com.nickbenn.sieve.bitset

import java.util.BitSet
import kotlin.math.sqrt

fun sieve(limit: Int): BitSet {
    val limitRoot = sqrt(limit.toDouble()).toInt()
    val primes = BitSet(limit + 1)
    primes.set(2, limit + 1)
    var prime = 2
    while (prime <= limitRoot) {
        for (multiple in (prime * prime)..limit step prime) {
            primes.clear(multiple)
        }
        prime = primes.nextSetBit(prime + 1)
    }
    return primes
}

fun main() {
    val start = System.currentTimeMillis()
    val upperBound = 10_000_000
    val primes = sieve(upperBound)
    val end = System.currentTimeMillis()
    print(
        """
            Kotlin Sieve with BitSet: 
            ${primes.cardinality()} primes found between ${primes.nextSetBit(0)} and ${primes.previousSetBit(primes.size() - 1)} (inclusive) in ${end - start} ms.
        """.trimIndent()
    )
}