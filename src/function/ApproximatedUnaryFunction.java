package function;

import java.math.BigDecimal;

public interface ApproximatedUnaryFunction {

    BigDecimal calculate(BigDecimal x, BigDecimal precision);

}
