/*************************************************
 *************************************************
 * A program to test the fonts on a computer
 * 
 * Author: Tahmid Munat
 * contact@tahmidmunat.com
 **************************************************
 **************************************************
 */

/* Imports */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    // ActionListener, ActionEvent
import javax.swing.event.*; // ChangeListener, ChangeEvent


public class FontViewer extends JFrame {
  
  // instance variables
  private JComboBox fontMenu;
  private JCheckBox italicCheckbox, boldCheckbox;
  private JSlider sizeSlider;
  private JLabel sizeLabel, sizeWord, textLabel;
  private JPanel mainGui;
  private int fontType;
  
  
  // listener class
  private class FontViewerListener implements ActionListener, ChangeListener {
   
    public void stateChanged(ChangeEvent event) {
      updateSlider();
    }
   
    public void actionPerformed(ActionEvent event) {
      updateOption();
    }
  }
  
  
  // updates slider and the slider value label
  public void updateSlider() {
    int size = this.sizeSlider.getValue();
    this.sizeLabel.setText(Integer.toString(size));
    updateSampleText();
  }
  
  
  // updates corresponding option
  public void updateOption() {
    if ((this.italicCheckbox.isSelected()) &&
        (this.boldCheckbox.isSelected())) {
      this.fontType = Font.BOLD + Font.ITALIC;
    }
    if ((!this.italicCheckbox.isSelected()) ||
        (!this.boldCheckbox.isSelected())) {
      this.fontType = Font.PLAIN;
    }
    if ((this.boldCheckbox.isSelected()) && 
      (!this.italicCheckbox.isSelected())){
      this.fontType = Font.BOLD;
    }
    if ((this.italicCheckbox.isSelected()) && 
        (!this.boldCheckbox.isSelected())){
      this.fontType = Font.ITALIC;
    }
    updateSampleText();
  }
  
  // updates the font
  public void updateSampleText() {
    String currentFont = (String) this.fontMenu.getSelectedItem();
    int size = this.sizeSlider.getValue();
    Font font = new Font(currentFont, this.fontType, size);
    this.textLabel.setFont(font);
  }
  
  // constructor
  public FontViewer() {
    
    this.fontType = Font.PLAIN;
    
    //create top level GUI
    JPanel mainGui = new JPanel(new BorderLayout());
    
    // create control panel
    JPanel controlPanel = new JPanel(new GridLayout(1, 5));
    
    // create text panel
    JPanel textPanel = new JPanel();
    textPanel.setBackground(Color.WHITE);
    
    // slider
    this.sizeSlider = new JSlider(10, 72, 40);
    this.sizeWord = new JLabel("Size:");
    this.sizeLabel = new JLabel("40");
    
    // checkboxes and drop down menu
    this.boldCheckbox = new JCheckBox("BOLD");
    this.italicCheckbox = new JCheckBox("ITALIC");
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fontNames = env.getAvailableFontFamilyNames();
    this.fontMenu = new JComboBox(fontNames);
    
    
    // adding components
    controlPanel.add(this.fontMenu);
    controlPanel.add(this.boldCheckbox);
    controlPanel.add(this.italicCheckbox);
    controlPanel.add(this.sizeSlider);
    controlPanel.add(this.sizeWord);
    controlPanel.add(this.sizeLabel);
    
    // initial text (only the 1st line is fine but updated for clarity)
    this.textLabel = new JLabel("Here is some sample text"); /* change to your desired text if necessary */
    String currentFont = (String) this.fontMenu.getSelectedItem();
    int size = this.sizeSlider.getValue();
    Font font = new Font(currentFont, this.fontType, size);
    this.textLabel.setFont(font);
    textPanel.add(this.textLabel);
    
    // attach listener to slider controls
    FontViewerListener listener = new FontViewerListener();
    this.sizeSlider.addChangeListener(listener);
    this.italicCheckbox.addActionListener(listener);
    this.boldCheckbox.addActionListener(listener);
    this.fontMenu.addActionListener(listener);
    
    // initialize the display
    mainGui.add(controlPanel, BorderLayout.NORTH);
    mainGui.add(textPanel, BorderLayout.CENTER);
    this.add(mainGui);

    // configure frame parameters
    this.setTitle("Font Viewer");
    this.setSize(750, 170);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
 
  // test program
  public static void main(String[] args) {
    new FontViewer();
  }
}