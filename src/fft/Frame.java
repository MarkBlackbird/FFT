/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fft;

import javax.swing.JFrame;

/**
 *
 * @author kosma
 */
public class Frame extends JFrame{
    public Frame()
    {
        setVisible(true);
        Panel pan = new Panel();
        add(pan);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
        pan.repaint();
    }
}
