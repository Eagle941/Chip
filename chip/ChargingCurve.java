package chip;

import static java.lang.Math.E;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.math.MathContext;

public class ChargingCurve {
    
    public static BigDecimal[][] chargingcurve(BigDecimal[] is, BigDecimal[] r17, BigDecimal[] c5, double vts){
        
        //Time
        BigDecimal[] time = new BigDecimal[20];
        double advance = 0.0;
        for(int i = 0; i<time.length; i++){
            time[i] = new BigDecimal(advance,MathContext.DECIMAL64);
            advance += 0.00004;
        }
        
        //Time Constant
        BigDecimal taunom = r17[0].multiply(c5[0]);
        BigDecimal taulow = r17[1].multiply(c5[1]);
        BigDecimal tauhigh = r17[2].multiply(c5[2]);
        BigDecimal[] tau = {taunom,taulow,tauhigh};
        
        BigDecimal[] v1tnom = new BigDecimal[20];
        BigDecimal[] v1tlow = new BigDecimal[20];
        BigDecimal[] v1thigh = new BigDecimal[20];
        BigDecimal[][] v1t = {v1tnom,v1tlow,v1thigh};

        //v1t
        //System.out.println("    t           V1(t)         Min V1(t)       Max V1(t)   ");
        
        for(int i=0; i<time.length; i++){
            v1tnom[i] = new BigDecimal(r17[0].doubleValue()*is[0].doubleValue()-(r17[0].doubleValue()*is[0].doubleValue()-vts)*pow(E,-time[i].doubleValue()/tau[0].doubleValue()));
            v1tlow[i] = new BigDecimal(r17[1].doubleValue()*is[1].doubleValue()-(r17[1].doubleValue()*is[1].doubleValue()-vts)*pow(E,-time[i].doubleValue()/tau[1].doubleValue()));
            v1thigh[i] = new BigDecimal(r17[2].doubleValue()*is[2].doubleValue()-(r17[2].doubleValue()*is[2].doubleValue()-vts)*pow(E,-time[i].doubleValue()/tau[2].doubleValue()));
            //System.out.format("%.5fs" + "   " + "%.10fV" + "   " + "%.10fV" + "   " + "%.10fV" + "\n", time[i].doubleValue(), v1tnom.doubleValue(), v1tlow.doubleValue(), v1thigh.doubleValue());
        }
        
        return v1t;
    }
}
