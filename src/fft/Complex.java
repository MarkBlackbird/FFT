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
public class Complex {
    double real, imag;
    public Complex()
    {
        real=0;
        imag=0;
    }
    public Complex(double real, double imag)
    {
        this.real=real;
        this.imag=imag;
    }
    public double moduł ()
    {
        return Math.sqrt(real*real+imag*imag);
    }
    public Complex suma (Complex arg)
    {
        return new Complex(real+arg.real,imag+arg.imag);
    }
    public Complex różnica (Complex arg)
    {
        return new Complex(real-arg.real,imag-arg.imag);
    }
    public Complex mnożenie (Complex arg)
    {
        return new Complex(real*arg.real-imag*arg.imag,real*arg.imag+imag*arg.real);
    }
    public Complex minus ()
    {
        return new Complex(-real, -imag);
    }
}
