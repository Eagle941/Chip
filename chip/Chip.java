package chip;
import static chip.ChargingCurve.chargingcurve;
import static chip.Tolerances.highervalue;
import static chip.Tolerances.lowervalue;
import static java.lang.Math.E;
import static java.lang.Math.pow;
import static java.lang.StrictMath.log;
import java.math.BigDecimal;
import java.math.MathContext;

public class Chip{
    
    public static void main(String[] args) {
        
        //Resistor Values
        BigDecimal[] resistors = new BigDecimal[74];
        
        for(int i=0; i<resistors.length-2; i++){
            int division = i/12;
            switch (division){
                case 0: resistors[i] = new BigDecimal(0.1,MathContext.DECIMAL64);
                    break;
                case 1: resistors[i] = new BigDecimal(1,MathContext.DECIMAL64);
                    break;
                case 2: resistors[i] = new BigDecimal(10,MathContext.DECIMAL64);
                    break;
                case 3: resistors[i] = new BigDecimal(100,MathContext.DECIMAL64);
                    break;
                case 4: resistors[i] = new BigDecimal(1000,MathContext.DECIMAL64);
                    break;
                case 5: resistors[i] = new BigDecimal(10000,MathContext.DECIMAL64);
                    break;
            }
        }
        
        for(int i=0; i<resistors.length-2; i++){
            int change = i%12;
            switch (change){
                case 0: resistors[i] = (new BigDecimal(10,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 1: resistors[i] = (new BigDecimal(12,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 2: resistors[i] = (new BigDecimal(15,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 3: resistors[i] = (new BigDecimal(18,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 4: resistors[i] = (new BigDecimal(22,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 5: resistors[i] = (new BigDecimal(27,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 6: resistors[i] = (new BigDecimal(33,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 7: resistors[i] = (new BigDecimal(39,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 8: resistors[i] = (new BigDecimal(47,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 9: resistors[i] = (new BigDecimal(56,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 10: resistors[i] = (new BigDecimal(68,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
                case 11: resistors[i] = (new BigDecimal(82,MathContext.DECIMAL64)).multiply(resistors[i]);
                    break;
            }
        }
              
        resistors[72] = new BigDecimal(1000000,MathContext.DECIMAL64);
        resistors[73] = new BigDecimal(1500000,MathContext.DECIMAL64);
        
        /*for( int i = 0; i < resistors.length; i++){
            System.out.println(resistors[i]);
        }*/
        
        //Capacitor Values
        BigDecimal[] capacitors = new BigDecimal[43];
        
        for(int i=0; i<capacitors.length-3; i++){
            int division = i/6;
            switch (division){
                case 0: capacitors[i] = new BigDecimal(10.0E-12,MathContext.DECIMAL64);
                    break;
                case 1: capacitors[i] = new BigDecimal(100.0E-12,MathContext.DECIMAL64);
                    break;
                case 2: capacitors[i] = new BigDecimal(1.0E-9,MathContext.DECIMAL64);
                    break;
                case 3: capacitors[i] = new BigDecimal(10.0E-9,MathContext.DECIMAL64);
                    break;
                case 4: capacitors[i] = new BigDecimal(100E-9,MathContext.DECIMAL64);
                    break;
                case 5: capacitors[i] = new BigDecimal(1.0E-6,MathContext.DECIMAL64);
                    break;
                case 6: capacitors[i] = new BigDecimal(10E-6,MathContext.DECIMAL64);
                    break;
            }
        }
        
        for(int i=0; i<capacitors.length-3; i++){
            int change = i%6;
            switch (change){
                case 0: capacitors[i] = (new BigDecimal(1,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
                case 1: capacitors[i] = (new BigDecimal(1.5,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
                case 2: capacitors[i] = (new BigDecimal(2.2,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
                case 3: capacitors[i] = (new BigDecimal(3.3,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
                case 4: capacitors[i] = (new BigDecimal(4.7,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
                case 5: capacitors[i] = (new BigDecimal(6.8,MathContext.DECIMAL64)).multiply(capacitors[i]);
                    break;
            }
        }
        
        capacitors[40] = new BigDecimal(100.0E-6,MathContext.DECIMAL64);
        capacitors[41] = new BigDecimal(470.0E-6,MathContext.DECIMAL64);
        capacitors[42] = new BigDecimal(1.0E-3,MathContext.DECIMAL64);
        
        /*for( int i = 0; i < capacitors.length; i++){
            System.out.println(capacitors[i]);
        }*/
        
        //Tolerances
        BigDecimal restol = new BigDecimal(1.0,MathContext.DECIMAL64);
        BigDecimal captol = new BigDecimal(10.0, MathContext.DECIMAL64);
        
        //R16 == Rt
        BigDecimal r16n = resistors[0];
        BigDecimal r16l = lowervalue(restol,r16n);
        BigDecimal r16h = highervalue(restol,r16n);
        BigDecimal[] r16 = {r16n,r16l,r16h};
        
        //System.out.println(r16n + " " + r16l + " " + r16h);
        
        //C4 == Ct
        BigDecimal c4n = capacitors[0];
        BigDecimal c4l = lowervalue(captol,c4n);
        BigDecimal c4h = highervalue(captol,c4n);
        BigDecimal[] c4= {c4n,c4l,c4h};
        
        //R14 == Rs
        BigDecimal r14n = resistors[50];
        BigDecimal r14l = lowervalue(restol,r14n);
        BigDecimal r14h = highervalue(restol,r14n);
        BigDecimal[] r14= {r14n,r14l,r14h};
        
        //R17 == Rl
        BigDecimal r17n = resistors[55];
        BigDecimal r17l = lowervalue(restol,r17n);
        BigDecimal r17h = highervalue(restol,r17n);
        BigDecimal[] r17= {r17n,r17l,r17h};
        
        //C5 == Cl
        BigDecimal c5n = capacitors[20];
        BigDecimal c5l = lowervalue(captol,c5n);
        BigDecimal c5h = highervalue(captol,c5n);
        BigDecimal[] c5= {c5n,c5l,c5h};
        
        //Vthresh
        BigDecimal vthreshnom = new BigDecimal(5*0.667, MathContext.DECIMAL64);
        BigDecimal vthreshlow = new BigDecimal(5*0.63, MathContext.DECIMAL64);
        BigDecimal vthreshhigh = new BigDecimal(5*0.7, MathContext.DECIMAL64);
        BigDecimal[] vthresh= {vthreshnom,vthreshlow,vthreshhigh};
        
        //t1
        BigDecimal t1nom = new BigDecimal(-r16[0].doubleValue()*c4[0].doubleValue()*log(1-(vthresh[0].doubleValue()/5)), MathContext.DECIMAL64);
        BigDecimal t1low = new BigDecimal(-r16[1].doubleValue()*c4[1].doubleValue()*log(1-(vthresh[1].doubleValue()/5)), MathContext.DECIMAL64);
        BigDecimal t1high = new BigDecimal(-r16[2].doubleValue()*c4[2].doubleValue()*log(1-(vthresh[2].doubleValue()/5)), MathContext.DECIMAL64);
        BigDecimal[] t1= {t1nom,t1low,t1high};
        
        //Vpin2
        BigDecimal vpin2nom = new BigDecimal(1.89, MathContext.DECIMAL64);
        BigDecimal vpin2low = new BigDecimal(1.7, MathContext.DECIMAL64);
        BigDecimal vpin2high = new BigDecimal(2.08, MathContext.DECIMAL64);
        BigDecimal[] vpin2= {vpin2nom,vpin2low,vpin2high};
        
        //Is
        BigDecimal isnom = new BigDecimal(vpin2[0].doubleValue()/r14[0].doubleValue(), MathContext.DECIMAL64);
        BigDecimal islow = new BigDecimal(vpin2[1].doubleValue()/r14[1].doubleValue(), MathContext.DECIMAL64);
        BigDecimal ishigh = new BigDecimal(vpin2[2].doubleValue()/r14[2].doubleValue(), MathContext.DECIMAL64);
        BigDecimal[] is= {isnom,islow,ishigh};
        
        //System.out.println(r14n + " " + isnom + " " + " " + islow + " " + ishigh);
        
        //Vts
        BigDecimal vts = new BigDecimal(1.6, MathContext.DECIMAL64);
        
        //V1(t1)
        BigDecimal v1t1nom = new BigDecimal(r17[0].doubleValue()*is[0].doubleValue()-(r17[0].doubleValue()*is[0].doubleValue())*pow(E,-t1[0].doubleValue()/(r17[0].doubleValue()*c5[0].doubleValue())));
        BigDecimal v1t1low = new BigDecimal(r17[1].doubleValue()*is[1].doubleValue()-(r17[1].doubleValue()*is[1].doubleValue())*pow(E,-t1[1].doubleValue()/(r17[1].doubleValue()*c5[1].doubleValue())));
        BigDecimal v1t1high = new BigDecimal(r17[2].doubleValue()*is[2].doubleValue()-(r17[2].doubleValue()*is[2].doubleValue())*pow(E,-t1[2].doubleValue()/(r17[2].doubleValue()*c5[2].doubleValue())));
        BigDecimal[] v1t1 = {v1t1nom,v1t1low,v1t1high};
        
        //System.out.println(v1t1nom);
        System.out.println("The resistance R17 is " + r17[0] + "\n" + "The capacitor C5 is " + c5[0] + "\n" + "The current Is is " + is[0]);
        chargingcurve(is,r17,c5,vts);
    }   
}