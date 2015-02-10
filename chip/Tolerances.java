package chip;
import java.math.BigDecimal;
import java.math.MathContext;

public class Tolerances {
        
    //Higher Value of Component
    public static BigDecimal highervalue(BigDecimal tolerance, BigDecimal component){
        BigDecimal hundred = new BigDecimal(100,MathContext.DECIMAL64);
        BigDecimal part = tolerance.divide(hundred);
        BigDecimal hvalue = component.multiply(part);
        return hvalue.add(component);
    }
    
    //Lower Value of Component
    public static BigDecimal lowervalue(BigDecimal tolerance, BigDecimal component){
        BigDecimal hundred = new BigDecimal(-100,MathContext.DECIMAL64);
        BigDecimal part = tolerance.divide(hundred);
        BigDecimal lvalue = component.multiply(part);
        return lvalue.add(component);
    }
}