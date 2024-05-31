package function;

import logariphmic.Ln;
import logariphmic.Log;
import trigonometric.Csc;
import trigonometric.Sin;
import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;


public class FunctionsSystem implements ApproximatedUnaryFunction{
    private final Sin sin;
    private final Csc csc;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;


    public FunctionsSystem() {
        this.sin = new Sin();
        this.csc = new Csc();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
        this.log10 = new Log(10);
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision){
        if (x.compareTo(ZERO) <= 0){
            // x <= 0: csc(x) / csc(x)
            return csc.calculate(x, precision).divide(csc.calculate(x,precision), HALF_UP);
        } else {
            // x > 0: (((((log_5(x) - log_2(x)) + (log_5(x) + ln(x))) * log_5(x)) - log_10(x)) - log_3(x))


            return ((((log5.calculate(x, precision).subtract(log2.calculate(x,precision))).add((log5.calculate(x,precision).add(ln.calculate(x,precision))))).multiply(log5.calculate(x,precision))).subtract(log3.calculate(x,precision))).setScale(precision.scale(), HALF_EVEN);
        }
    }

}
