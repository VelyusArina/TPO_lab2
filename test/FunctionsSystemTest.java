
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;
import function.FunctionsSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class FunctionsSystemTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.00000001");
    private final FunctionsSystem system = new FunctionsSystem();

    //тестируют результаты вычислений для положительных и отрицательных значений аргументов
    @Test
    void calcForPositiveValueShouldOK() {
        BigDecimal expected = new BigDecimal("1.17077002");
       assertEquals(expected, system.calculate(new BigDecimal("0.5"), DEFAULT_PRECISION));
    }

    @Test
    void calcForNegativeValueShouldOK() {
        BigDecimal expected = new BigDecimal("1.00000000");
        assertEquals(expected, system.calculate(new BigDecimal("-0.5"), DEFAULT_PRECISION));
    }

    @Test
    void calcWithNullPrecisionShouldThrow() {
        assertThrows(NullPointerException.class, () -> system.calculate(new BigDecimal(-2), null));
    }
//предоставляет набор некорректных значений точности
    private static Stream<Arguments> incorrectPrecisions() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1)),
                Arguments.of(BigDecimal.valueOf(0)),
                Arguments.of(BigDecimal.valueOf(1.01)),
                Arguments.of(BigDecimal.valueOf(-0.01)));
    }

    @ParameterizedTest
    @MethodSource("incorrectPrecisions")
    void calcWithIncorrectPrecisionsShouldThrow(BigDecimal precision) {
        assertThrows(ArithmeticException.class, () -> system.calculate(new BigDecimal(-2), precision));
    }

    //если аргумент равен нулю или если аргумент передается как 1
    @Test
    void calcForZeroShouldThrow() {
        assertThrows(ArithmeticException.class, () -> system.calculate(ZERO, DEFAULT_PRECISION)); // todo: csc(x) = 0
    }




}
