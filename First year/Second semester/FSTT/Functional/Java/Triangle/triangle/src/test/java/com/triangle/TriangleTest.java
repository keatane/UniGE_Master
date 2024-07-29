/* Author: Kevin Cattaneo - S4944382 */
package com.triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    @ParameterizedTest
    @CsvSource({
        "0, 1, 1, INVALID",
        "3, 0, 5, INVALID",
        "3, 4, 0, INVALID",
        "10, 1, 1, INVALID",
        "1, 10, 1, INVALID",
        "1, 1, 10, INVALID",
        "3, 3, 3, Equilateral",
        "3, 3, 4, Isosceles",
        "3, 4, 3, Isosceles",
        "4, 3, 3, Isosceles",
        "3, 4, 5, Scalene"
    })
    void testCheckType(float a, float b, float c, TriangleType expected) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expected, triangle.CheckType());
    }

    @ParameterizedTest
    @CsvSource({
        "3, 3, 3, false",
        "3, 4, 5, true",
        "5, 4, 3, true",
        "4, 5, 3, true",
        "4, 3, 5, true",
        "5, 3, 4, true"
    })
    void testIsRight(float a, float b, float c, boolean expected) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expected, triangle.isRight());
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1, 1, false",
        "3, 4, 5, true",
    })
    void testIsValid(float a, float b, float c, boolean expected) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expected, triangle.isValid());
    }
}
