package chip;

import static java.lang.Math.E;
import static java.lang.Math.pow;
import static java.lang.StrictMath.log;
import java.math.BigDecimal;
import java.math.MathContext;

public class Discharging {
    
    public static void dischargingcurve(BigDecimal[] r17, BigDecimal[] c5, BigDecimal[] is, BigDecimal[] t1){
        
        //Vts
        BigDecimal[] vts = new BigDecimal[12];
        double advance = 0.5;
        for(int i = 0; i<vts.length; i++){
            vts[i] = new BigDecimal(advance,MathContext.DECIMAL64);
            advance += 0.1;
        }
        
        //Time Constant
        BigDecimal taunom = r17[0].multiply(c5[0]);
        BigDecimal taulow = r17[1].multiply(c5[1]);
        BigDecimal tauhigh = r17[2].multiply(c5[2]);
        BigDecimal[] tau = {taunom,taulow,tauhigh};
        
        //V1(t1) lists
        BigDecimal[] v1t1nom = new BigDecimal[12];
        BigDecimal[] v1t1low = new BigDecimal[12];
        BigDecimal[] v1t1high = new BigDecimal[12];
        
        //t2 lists
        BigDecimal[] t2nom = new BigDecimal[12];
        BigDecimal[] t2low = new BigDecimal[12];
        BigDecimal[] t2high = new BigDecimal[12];
        
        //T lists
        BigDecimal[] tnom = new BigDecimal[12];
        BigDecimal[] tlow = new BigDecimal[12];
        BigDecimal[] thigh = new BigDecimal[12];
        
        //F lists
        BigDecimal[] fnom = new BigDecimal[12];
        BigDecimal[] flow = new BigDecimal[12];
        BigDecimal[] fhigh = new BigDecimal[12];
        
        //System.out.println("  Vts     Nom V1(t1)        Nom t2         Nom T             Nom F           Min V1(t1)        Min t2          Min T             Min F           Max V1(t1)        Max t2          Max T             Max F");
        for(int i=0; i<vts.length; i++){
            
            //V1(t1) calculations
            v1t1nom[i] = new BigDecimal(r17[0].doubleValue()*is[0].doubleValue()-(r17[0].doubleValue()*is[0].doubleValue()-vts[i].doubleValue())*pow(E,-t1[0].doubleValue()/tau[0].doubleValue()));
            v1t1low[i] = new BigDecimal(r17[1].doubleValue()*is[1].doubleValue()-(r17[1].doubleValue()*is[1].doubleValue()-vts[i].doubleValue())*pow(E,-t1[1].doubleValue()/tau[1].doubleValue()));
            v1t1high[i] = new BigDecimal(r17[2].doubleValue()*is[2].doubleValue()-(r17[2].doubleValue()*is[2].doubleValue()-vts[i].doubleValue())*pow(E,-t1[2].doubleValue()/tau[2].doubleValue()));
            
            //t2 calculations
            t2nom[i] = new BigDecimal(tau[0].doubleValue()*log(v1t1nom[i].doubleValue()/vts[i].doubleValue()));
            t2low[i] = new BigDecimal(tau[1].doubleValue()*log(v1t1low[i].doubleValue()/vts[i].doubleValue()));
            t2high[i] = new BigDecimal(tau[2].doubleValue()*log(v1t1high[i].doubleValue()/vts[i].doubleValue()));
            
            //T
            tnom[i] = t2nom[i].add(t1[0]);
            tlow[i] = t2low[i].add(t1[1]);
            thigh[i] = t2high[i].add(t1[2]);
            
            //F
            fnom[i] = new BigDecimal(1.0/tnom[i].doubleValue());
            flow[i] = new BigDecimal(1.0/tlow[i].doubleValue());
            fhigh[i] = new BigDecimal(1.0/thigh[i].doubleValue());
            
            //System.out.format("%.1fs" + "   " + "%.10fV" + "   " + "%.10fs" + "   " + "%.10fs" + "   " + "%.11fHz" + "   " + "%.10fV" + "   " + "%.10fs" + "   " + "%.10fs" + "   " + "%.11fHz" + "   " + "%.10fV" + "   " + "%.10fs" + "   " + "%.10fs" + "   " + "%.11fHz" + "\n", vts[i].doubleValue(), v1t1nom[i].doubleValue(), t2nom[i].doubleValue(), tnom[i].doubleValue(), fnom[i].doubleValue(), v1t1low[i].doubleValue(), t2low[i].doubleValue(), tlow[i].doubleValue(), flow[i].doubleValue(), v1t1high[i].doubleValue(), t2high[i].doubleValue(), thigh[i].doubleValue(), fhigh[i].doubleValue());
        }
    }
}
