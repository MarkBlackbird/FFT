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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Marek Kos
 */
public class Panel extends JPanel{
  private Image imgBuff;
  private Graphics2D buffGc;
  private Color kolor=Color.red;
  public  Image imageA = null;
  int size =50;
  int multiplier=8;
    BufferedImage image;
    Graphics2D g2;
    boolean first = true;
    public int used=0;
    Complex[][] in1;
    Complex[][] in2;
    Complex[][] in3;
    Complex[][] out;
    Complex[][] out2;
    Complex[][] out3;
    int u=1, w=1;
    public Panel ()
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("test.png"));
        } catch (IOException e) {
        }
        setSize(size, size);
        in1 = new Complex[size][size];
        in2 = new Complex[size][size];
        in3 = new Complex[size][size];
        out = new Complex[size][size];
        out2 = new Complex[size][size];
        out3 = new Complex[size][size];
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                in1[i][j] = new Complex(img.getRGB(i, j)%256,0);
                in2[i][j] = new Complex((img.getRGB(i, j)/256)%256,0);
                in3[i][j] = new Complex((img.getRGB(i, j)/256/256)%256,0);
            }
        }
        /*in[-1+w][-1+u].real=1;
        in[-1+w][0+u].real=1;
        in[-1+w][1+u].real=1;
        in[0+w][-1+u].real=1;
        in[0+w][0+u].real=1;
        in[0+w][1+u].real=1;
        in[1+w][-1+u].real=1;
        in[1+w][0+u].real=1;
        in[1+w][1+u].real=1;*/
        
        /*in[-1+w][-1+u].imag=1;
        in[-1+w][0+u].imag=1;
        in[-1+w][1+u].imag=1;
        in[0+w][-1+u].imag=1;
        in[0+w][0+u].imag=1;
        in[0+w][1+u].imag=1;
        in[1+w][-1+u].imag=1;
        in[1+w][0+u].imag=1;
        in[1+w][1+u].imag=1;*/
        NewClass.dft2(in1, out, size, size);
        NewClass.dft2(in2, out2, size, size);
        NewClass.dft2(in3, out3, size, size);
        Complex [][]pom = new Complex[size][size];
        Complex [][]pom2 = new Complex[size][size];
        Complex [][]pom3 = new Complex[size][size];
        NewClass.dft2(out, pom, size, size);
        NewClass.dft2(out2, pom2, size, size);
        NewClass.dft2(out3, pom3, size, size);
        
        //NewClass.dft2(out, out2, size, size);
        for(int i =0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                out[i][j]=pom[(2*size-i)%size][(2*size-j)%size];
                out2[i][j]=pom2[(2*size-i)%size][(2*size-j)%size];
                out3[i][j]=pom3[(2*size-i)%size][(2*size-j)%size];
            }
        }
        /*out=pom;
        out2=pom2;
        out3=pom3;*/
        //out2=pom
        //out=out2;
    }
    @Override
  public void paint(Graphics g)
  {
    if( g==null )
      return;

    try
    {
      if( buffGc==null )
      {
        imgBuff = createImage(size*multiplier, size*multiplier);
        if (imgBuff != null)
          buffGc = (Graphics2D) imgBuff.getGraphics();
      }

      if( imageA!=null )
      {
        int w = imageA.getWidth(null),
            h = imageA.getHeight(null);
        buffGc.drawImage(imageA, 0,0, w,h, this);
      }

      buffGc.setPaintMode();
    buffGc.setColor(kolor);

    buffGc.drawRect(0,0, size*multiplier,size*multiplier);
    
    
    double max=out[0][0].moduł(),min=out[0][0].moduł();
    double max2 = out2[0][0].moduł(),min2 = out2[0][0].moduł();
    double max3 = out3[0][0].moduł(),min3 = out3[0][0].moduł();
    for(int i=0;i<size;i++)
    {
        for(int j=0;j<size;j++)
        {
            if(max<out[i][j].moduł())
            {
                max=out[i][j].moduł();
            }
            if(min>out[i][j].moduł())
            {
                min=out[i][j].moduł();
            }
            if(max2<out2[i][j].moduł())
            {
                max2=out2[i][j].moduł();
            }
            if(min2>out2[i][j].moduł())
            {
                min2=out2[i][j].moduł();
            }
            if(max3<out3[i][j].moduł())
            {
                max3=out3[i][j].moduł();
            }
            if(min3>out3[i][j].moduł())
            {
                min3=out3[i][j].moduł();
            }
        }
    }
    //int max=1,min=0;
    for(int i=0;i<size;i++)
    {
        for(int j=0;j<size;j++)
        {
            /*float wyn=(float)((out[(i+size/2)%size][(j+size/2)%size].moduł()-min)/(max-min));
            float wyn2=(float)((out2[(i+size/2)%size][(j+size/2)%size].moduł()-min2)/(max2-min2));
            float wyn3=(float)((out3[(i+size/2)%size][(j+size/2)%size].moduł()-min3)/(max3-min3));*/
            float wyn=1-(float)((out[i][j].moduł()-min)/(max-min));
            float wyn2=1-(float)((out2[i][j].moduł()-min2)/(max2-min2));
            float wyn3=1-(float)((out3[i][j].moduł()-min3)/(max3-min3));
            buffGc.setColor(new Color(wyn3,wyn2,wyn));
            buffGc.fillRect(i*multiplier, j*multiplier, multiplier, multiplier);
        }
    }

      if( imgBuff!=null )
        g.drawImage(imgBuff, 0,0, this);
    }
    catch(NullPointerException e)
    {
    }
  }
    

    public BufferedImage createImage() {    
        int w = getWidth();
        int h = getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        paint(g);
        repaint();
        return bi;
    }
}

