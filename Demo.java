package clientsoftware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import colours.*;

public class Demo extends JFrame implements ActionListener {
 Colour[] colours;
 //Default Colours here.
 //Working Colour here
 Colour working;
 JPanel previewPanel;
 //Swing/AWT stuff here
 
 public Demo() {
  super("Colour Samples and Mixing");
  initializeColours();
  createGUI();
  addWindowListener(new GUIListener());
  setVisible(true);
 }
 
 private void initializeColours() {
  colours=new Colour[12];
  colours[0]=Palette.red(); //Int
  colours[1]=Palette.green();
  colours[2]=Palette.blue(); //Other
  colours[3]=Palette.cyan(); //Int
  colours[4]=Palette.magenta();
  colours[5]=Palette.yellow(); //Other
  colours[6]=Palette.orange(); //Int
  colours[7]=Palette.purple();
  colours[8]=Palette.aqua(); //Other
  colours[9]=Palette.black(); //Int
  colours[10]=Palette.white();
  colours[11]=Palette.favourite(); //Other
  working=Palette.white();
 }
 
 private void createGUI() {
  JPanel entirety; //One panel to rule them all, and in the darkness bind them. Or... something.
  JPanel leftside;
  JPanel rightside;
  JPanel controls;
  
  JPanel aColour;
  JButton setButton;
  JButton mixButton;
  
  JButton dealie;
  
  entirety=new JPanel(new BorderLayout());
  
  leftside=new JPanel(new GridLayout(12,3));
  for (int i=0;i<colours.length;i++) {
   aColour=new JPanel();
   aColour.setSize(30,30);
   aColour.setPreferredSize(new Dimension(30,30));
   aColour.setBackground(colours[i].speltPoorly());
   
   setButton=new JButton("->");
   setButton.setActionCommand(">"+i);
   setButton.addActionListener(this);
   mixButton=new JButton("+");
   mixButton.setActionCommand("+"+i);
   mixButton.addActionListener(this);
   
   leftside.add(aColour);
   leftside.add(setButton);
   leftside.add(mixButton);
  }
  
  rightside=new JPanel(new BorderLayout());
  
  previewPanel=new JPanel();
  previewPanel.setSize(310,310);
  previewPanel.setPreferredSize(new Dimension(310,310));
  updatePreview();
  
  controls=new JPanel(new GridLayout(2,2));
  dealie=new JButton("Darken");
  dealie.addActionListener(this);
  controls.add(dealie);
  
  dealie=new JButton("Lighten");
  dealie.addActionListener(this);
  controls.add(dealie);
  
  dealie=new JButton("Grey");
  dealie.addActionListener(this);
  controls.add(dealie);
  
  dealie=new JButton("Invert");
  dealie.addActionListener(this);
  controls.add(dealie);
  
  rightside.add(previewPanel,"North");
  rightside.add(controls,"South");
  
  entirety.add(leftside,"West");
  entirety.add(rightside,"East");
  add(entirety);
  pack();
 }
 
 private void updatePreview() {
  previewPanel.setBackground(working.speltPoorly());
 }
 
 //This decides how to handle each button-press
 public void actionPerformed(ActionEvent e) {
  if (e.getActionCommand().equals("Darken")) {
   working=working.darken();
   updatePreview();
  }
  else if (e.getActionCommand().equals("Lighten")) {
   working=working.lighten();
   updatePreview();
  }
  else if (e.getActionCommand().equals("Grey")) {
   working=working.grey();
   updatePreview();
  }
  else if (e.getActionCommand().equals("Invert")) {
   working=working.invert();
   updatePreview();
  }
  else {
   if (e.getActionCommand().charAt(0)=='>') {
    working=colours[Integer.parseInt(e.getActionCommand().substring(1))];
    updatePreview();
   }
   else {
    working=working.mix(colours[Integer.parseInt(e.getActionCommand().substring(1))]);
    updatePreview();
   }
  }
 }
 
 
 //Private class simply to ensure that the program actually terminates when the window is closed
 private class GUIListener extends WindowAdapter {
  public void windowClosing(WindowEvent event) {
   System.exit(0);
  }
 }
 
 public static void main(String[] args) {new Demo();}
}
