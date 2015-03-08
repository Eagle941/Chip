package chip;
import static chip.ChargingCurve.chargingcurve;
import static chip.Discharging.dischargingcurve;
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
        
        /////////////////////////////////////////////////////////////////////////////////////
        
        //System.out.println(r16n + " " + r16l + " " + r16h);

        //R16 == Rt 45
        BigDecimal r16n = resistors[0];
        BigDecimal r16l = lowervalue(restol,r16n);
        BigDecimal r16h = highervalue(restol,r16n);
        BigDecimal[] r16 = {r16n,r16l,r16h};
        
        //C4 == Ct 23
        BigDecimal c4n = capacitors[0];
        BigDecimal c4l = lowervalue(captol,c4n);
        BigDecimal c4h = highervalue(captol,c4n);
        BigDecimal[] c4= {c4n,c4l,c4h};
        
        //R14 == Rs 50
        BigDecimal r14n = resistors[0];
        BigDecimal r14l = lowervalue(restol,r14n);
        BigDecimal r14h = highervalue(restol,r14n);
        BigDecimal[] r14= {r14n,r14l,r14h};
        
        //R17 == Rl 55
        BigDecimal r17n = resistors[0];
        BigDecimal r17l = lowervalue(restol,r17n);
        BigDecimal r17h = highervalue(restol,r17n);
        BigDecimal[] r17= {r17n,r17l,r17h};
        
        //C5 == Cl 20
        BigDecimal c5n = capacitors[0];
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
        double vts = 0.0; 
        
        //V1(t1)
        BigDecimal v1t1nom = new BigDecimal(r17[0].doubleValue()*is[0].doubleValue()-(r17[0].doubleValue()*is[0].doubleValue())*pow(E,-t1[0].doubleValue()/(r17[0].doubleValue()*c5[0].doubleValue())));
        BigDecimal v1t1low = new BigDecimal(r17[1].doubleValue()*is[1].doubleValue()-(r17[1].doubleValue()*is[1].doubleValue())*pow(E,-t1[1].doubleValue()/(r17[1].doubleValue()*c5[1].doubleValue())));
        BigDecimal v1t1high = new BigDecimal(r17[2].doubleValue()*is[2].doubleValue()-(r17[2].doubleValue()*is[2].doubleValue())*pow(E,-t1[2].doubleValue()/(r17[2].doubleValue()*c5[2].doubleValue())));
        BigDecimal[] v1t1 = {v1t1nom,v1t1low,v1t1high};
        
        //System.out.println(v1t1nom);
        //chargingcurve(is,r17,c5,vts);
        //dischargingcurve(r17,c5,is,t1);
        //System.out.println("The resistance R17 is " + r17[0] + "\n" + "The capacitor C5 is " + c5[0] + "\n" + "The current Is is " + is[0] + "\n" + "The resistance R16 is " + r16[0] + "\n" + "The capacitor C4 is " + c4[0]);
        
        //Contator
        int contcharge = 0;
        int contv1t1c = 0;
        int comb = 0;
        int contv1t1d = 0;
        int contt2 = 0;
        int contf = 0;
        
        
        //Vts
        BigDecimal[] time = new BigDecimal[12];
        double advance = 0.5;
        for(int i = 0; i<time.length; i++){
            time[i] = new BigDecimal(advance,MathContext.DECIMAL64);
            advance += 0.1;
        }
        
        for(int a=0; a<resistors.length;a++){            
            for(int b=0; b<capacitors.length;b++){
                for(int c=0; c<resistors.length;c++){
                    for(int d=0; d<resistors.length;d++){
                        for(int e=0; e<capacitors.length;e++){
                            
                            //Assigned values
                            r16n=resistors[a];
                            c4n=capacitors[b];
                            r14n=resistors[c];
                            r17n=resistors[d];
                            c5n=capacitors[e];

                            //v1t list
                            BigDecimal[][] charge = chargingcurve(is,r17,c5,vts);
                            BigDecimal[][][] discharge = dischargingcurve(r17,c5,is,t1);
                            comb++;
                            System.out.println(comb);
                            
                            if(t1nom.doubleValue()>(0.000316) & t1low.doubleValue()>(0.000316) & t1high.doubleValue()>(0.000316)){
                                vts=0.5;
                                for(int f0=0; f0<charge.length; f0++){
                                    for(int f1=0; f1<charge[f0].length; f1++){
                                        if(charge[f0][f1].doubleValue()<4.6){
                                            contcharge++;
                                            if(contcharge == 60){
                                                for(int g=0; g<v1t1.length; g++){
                                                    if(v1t1[g].doubleValue()<4.6 & v1t1[g].doubleValue()>vts){
                                                        contv1t1c++;
                                                        if(contv1t1c==3){
                                                            //3
                                                            for(int h=0; h<discharge.length;h++){
                                                                //3
                                                                for(int i=0; i<discharge[h].length;i++){
                                                                    //12
                                                                    for(int l=0; l<discharge[h][l].length;l++){
                                                                        //v1t1
                                                                        if(h==0){
                                                                            if(discharge[h][i][l].doubleValue()<4.6 & discharge[h][i][l].doubleValue()>time[l].doubleValue()){
                                                                                contv1t1d++;
                                                                            }
                                                                        }
                                                                        //t2
                                                                        else if(h==1){
                                                                            if(discharge[h][i][l].doubleValue()>0.000263){
                                                                                contt2++;
                                                                            }
                                                                        }
                                                                        //f
                                                                        else if(h==2){
                                                                            if(discharge[h][i][l].doubleValue()<1727 & discharge[h][i][l].doubleValue()>512){
                                                                                contf++;
                                                                            }
                                                                        }
                                                                        else if(contv1t1d==36 & contt2==36 & contf==36){
                                                                            System.out.format("R16: %.1f | C4: %.1fpF | R14: %.1f | R17: %.1f | C5: %.1fpF" + "\n",r16n,c4n.multiply(new BigDecimal(Math.pow(10.0, 12))),r14n,r17n,c5n.multiply(new BigDecimal(Math.pow(10.0, 12))));
                                                                            contcharge = 0;
                                                                            contv1t1c = 0;
                                                                            comb = 0;
                                                                            contv1t1d = 0;
                                                                            contt2 = 0;
                                                                            contf = 0;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
        }
    }   
}