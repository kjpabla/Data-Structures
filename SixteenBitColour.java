package colours;

import java.awt.Color;

public class SixteenBitColour implements Colour {
 int red, green, blue;
 
 public SixteenBitColour() {
  this(31,63,31);
 }
 
 public SixteenBitColour(int r, int g, int b) {
  initialize(r,g,b);
 }
 
 public SixteenBitColour(String c) {
  int integerNumber;
  int r,g,b;
  try {
   integerNumber=Integer.parseInt(c,16);
   r=((integerNumber&0xFF0000)>>16)/8;
   g=((integerNumber&0x00FF00)>>8)/4;
   b=((integerNumber&0x0000FF))/8;

   initialize(r,g,b);
  }
  catch (NumberFormatException nfe) {
   throw new InvalidColourException();
  }
 }
 
 public SixteenBitColour(Color c) {
  this(c.getRed(),c.getGreen(),c.getBlue());
 }
 
 private void initialize(int r, int g, int b) {
   if ((r<0)|(r>31)|(g<0)|(g>63)|(b<0)|(b>31)) throw new InvalidColourException();
  red=r;green=g;blue=b;
 }
 
 //Hmm, I wonder how this would vary if the red didn't go from 0..255?
 public double getRed() {
  return red/31.0;
 }
 
 //Hmm, I wonder how this would vary if the green didn't go from 0..255?
 public double getGreen() {
  return green/63.0;
 }
 
 //Yadda yadda yadda?
 public double getBlue() {
  return blue/31.0;
 }
 
 public double getCyan() {
  return 1-getRed();
 }
 
 public double getMagenta() {
  return 1-getGreen();
 }
 
 public double getYellow() {
  return 1-getBlue();
 }
 
 public Colour mix(Colour c) {
  return new SixteenBitColour((int)(31*(c.getRed()+getRed())/2),(int)(63*(c.getGreen()+getGreen())/2),(int)(31*(c.getBlue()+getBlue())/2));
 }
 
 
 public Colour lighten() {
  return new SixteenBitColour((31-red)/2+red,(63-green)/2+green,(31-blue)/2+blue);
 }
 
 public Colour darken() {
  return new SixteenBitColour(red/2,green/2,blue/2);
 }
 
 public Colour invert() {
  return new SixteenBitColour(31-red,63-green,31-blue);
 }
 
 public Colour grey() {
  double luminosity=(getRed()+getGreen()+getBlue())/3.0;
  return new SixteenBitColour((int)(31*luminosity),(int)(63*luminosity),(int)(31*luminosity));
 }
 
 //There are two approaches to this:
 //1. I could convert each channel separately, pad individually, and then combine
 //2. I could combine into a single integer value, convert, and then pad
 public String toString() {
  String rs=Integer.toString(red,16),gs=Integer.toString(green,16),bs=Integer.toString(blue,16);
  return (rs.length()==1?"0"+rs:rs)+(gs.length()==1?"0"+gs:gs)+(bs.length()==1?"0"+bs:bs);
 }
 
 
 //Represent this colour as a java.awt.Color
 //Note: Do NOT use this function for any of the other methods you implement! (including mixing)
 public Color speltPoorly() {
  return new Color((float)getRed(),(float)getGreen(),(float)getBlue());
 }
 
}
