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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

@DisplayName("com.nickbenn.sieve.bitset.SieveKtTest")
class SieveKtTest {

    @ParameterizedTest(name = "[{index}] limit={0}, count={1}, min={2}, max={3}")
    @CsvFileSource(resources = ["/sieve-cases.csv"], numLinesToSkip = 1)
    fun sieve(limit: Int, count: Int, min: Int?, max: Int?) {
        val actual = sieve(limit)
        assertEquals(count, actual.cardinality())
        if (!actual.isEmpty) {
            assertEquals(min, actual.nextSetBit(0))
            assertEquals(max, actual.previousSetBit(actual.size() - 1))
        }
    }

}