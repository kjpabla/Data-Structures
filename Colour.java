package colours;
import java.awt.Color;

/*This interface represents the specification for a Colour ADT.
 *It could have different implementations (floating point, 24bit RGB, etc).
 *
 *For this assignment, you'll be implementing two versions:
 *SixteenBitColour.java - Simulates 5,6,5 bits for red,green,blue (0-31, 0-63, 0-31)
 *DoubleColour.java - Double-precision floating-point values for red, green, and blue (0.0-1.0 each)
 *
 */
public interface Colour {
 //Note: Every implemented class should have four constructors:
 //-Nullary (zero-argument/default) constructor for white
 //-One for the natural type (3 doubles for DoubleColour; 3 ints for SixteenBitColour)
 //-One for a 24-bit hexadecimal String (e.g. "80FFFF" for a light cyan)
 //-One for a java.awt.Color (just because it'd be neat)
 
 
 public double getRed(); //Red component of this colour, 0.0-1.0
 public double getGreen(); //Green component of this colour, 0.0-1.0
 public double getBlue(); //Blue component of this colour, 0.0-1.0
 public double getCyan(); //Cyan component, 0.0-1.0 (1.0-red)
 public double getMagenta(); //Green component, 0.0-1.0 (1.0-green)
 public double getYellow(); //Yellow component, 0.0-1.0 (1.0-blue)
 
 
 //Mixes another Colour with this one, to produce a new one.
 //"Mixing" is calculated by averaging the reds, greens, and blues
 public Colour mix(Colour c);
 
 
 public Colour lighten();
 public Colour darken();
 
 public Colour invert(); //Returns an inverted copy of the colour
 
 public Colour grey(); //Returns a new (greyscale) colour that's the luminosity of the original
 
 
 //Any Colour should be String-ily expressed in 24-bit hexadecimal notation, irrespective of implementation
 public String toString();
 
 
 //Represent this colour as a java.awt.Color
 //Note: Do NOT use this function for any of the other methods you implement! (including mixing)
 public Color speltPoorly();
 
 
}
