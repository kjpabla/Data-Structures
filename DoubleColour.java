package colours;

import java.awt.Color;

public class DoubleColour implements Colour {
 double red, green, blue;
 
 public DoubleColour() {
  this(1.0,1.0,1.0);
 }
 
 public DoubleColour(double r, double g, double b) {
  initialize(r,g,b);
 }
 
 public DoubleColour(String c) {
  int doubleNumber;
  double r,g,b;
  try {
   doubleNumber=Integer.parseInt(c,16);
   r=((doubleNumber&0xFF0000)>>16)/255.0;
   g=((doubleNumber&0x00FF00)>>8)/255.0;
   b=(doubleNumber&0x0000FF)/255.0;
   //Of course, if I wanted these in a different range (say, 0..31 or 0..63), I'd need to scale them
   initialize(r,g,b);
  }
  catch (NumberFormatException nfe) {
   throw new InvalidColourException();
  }
 }
 
 public DoubleColour(Color c) {
  this(c.getRed(),c.getGreen(),c.getBlue());
 }
 
 private void initialize(double r, double g, double b) {
  if ((r<0.0)|(r>1.0)|(g<0.0)|(g>1.0)|(b<0.0)|(b>1.0)) throw new InvalidColourException();
  red=r;green=g;blue=b;
 }
 
 //Hmm, I wonder how this would vary if the red didn't go from 0..255?
 public double getRed() {
  return red/1.0;
 }
 
 //Hmm, I wonder how this would vary if the green didn't go from 0..255?
 public double getGreen() {
  return green/1.0;
 }
 
 //Yadda yadda yadda?
 public double getBlue() {
  return blue/1.0;
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
 
 //SixteenBitColour
 public Colour mix(Colour c) {
  return new DoubleColour((double)(1.0*(c.getRed()+getRed())/2),(double)(1.0*(c.getGreen()+getGreen())/2),(double)(1.0*(c.getBlue()+getBlue())/2));
 }
 
 
 public Colour lighten() {
  return new DoubleColour((1.0-red)/2+red,(1.0-green)/2+green,(1.0-blue)/2+blue);
 }
 
 public Colour darken() {
  return new DoubleColour(red/2,green/2,blue/2);
 }
 
 public Colour invert() {
  return new DoubleColour(1.0-red,1.0-green,1.0-blue);
 }
 
 public Colour grey() {
  double luminosity=(getRed()+getGreen()+getBlue())/3.0;
  return new DoubleColour((double)(1.0*luminosity),(double)(1.0*luminosity),(double)(1.0*luminosity));
 }
 
 //There are two approaches to this:
 //1. I could convert each channel separately, pad individually, and then combine
 //2. I could combine into a single integer value, convert, and then pad
 public String toString() {
  String rs=Double.toString(red),gs=Double.toString(green),bs=Double.toString(blue);
  return (rs.length()==1.0?"0"+rs:rs)+(gs.length()==1.0?"0"+gs:gs)+(bs.length()==1.0?"0"+bs:bs);
 }
 
 
 //Represent this colour as a java.awt.Color
 //Note: Do NOT use this function for any of the other methods you implement! (including mixing)
 public Color speltPoorly() {
  return new Color((float)getRed(),(float)getGreen(),(float)getBlue());
 }
 
}