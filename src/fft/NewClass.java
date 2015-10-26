/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fft;

/**
 *
 * @author kosma
 */
public class NewClass {
    static void dft(Complex[] in, Complex[] out)
    {
    int n = in.length;
    for (int k = 0; k < n; k++) {  // For each output element
        double sumreal = 0;
        double sumimag = 0;
        for (int t = 0; t < n; t++) {  // For each input element
            double angle = 2 * Math.PI * t * k / n;
            sumreal +=  in[t].real * Math.cos(angle) + in[t].imag * Math.sin(angle);
            sumimag += -in[t].real * Math.sin(angle) + in[t].imag * Math.cos(angle);
            
        }
        out[k]=new Complex(sumreal,sumimag);
    }
}
    static void dft2(Complex[][] in, Complex[][] out, int len1, int len2)
    {
        //int n = in.length;
        for(int i=0;i<len1;i++)
        {
            for (int j = 0; j < len1; j++) 
            {  // For each output element
                double sumreal = 0;
                double sumimag = 0;
                for (int t = 0; t < len2; t++) {  // For each input element
                    for (int k = 0; k < len2; k++) {
                        double angle = 2 * Math.PI * j * k / len1+2 * Math.PI * i * t / len2;
                        sumreal +=  in[t][k].real * Math.cos(angle) + in[t][k].imag * Math.sin(angle);
                        sumimag += -in[t][k].real * Math.sin(angle) + in[t][k].imag * Math.cos(angle);
                    }
                }
                out[i][j]=new Complex(sumreal,sumimag);
            }
        }
        System.out.println("done");
    }
}
