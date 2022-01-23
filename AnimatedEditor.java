
/* 
     ***************************************************************
     *                                                                                                    
     *  AnimatedEditor - V1                                                               
     *  Developed by rhale                                                   
     *  Release: Jan 2012                                                                 
     *  Purpose: Custom Text Editor with transparent text area  
     *           over animated GIF image.                                            
     *  Free Open Source....                                                                                             
     *                                                                                                     
     ***************************************************************
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.filechooser.*;
import javax.swing.SwingUtilities;

public class AnimatedEditor 
{

         ImageIcon img = new ImageIcon("bg.gif");
         Image image =  img.getImage();
        int repaintVal = 0;
        File selImage = null;
  public void CreateAndShowGUI()
  {

 final JTextArea text1 = new JTextArea(50,50);
  final JFileChooser fc = new JFileChooser();
 
    JViewport viewport = new JViewport()
    {
       public void paintComponent(Graphics g)
       {
          super.paintComponent(g);
  
                                     if(repaintVal == 1 ) {
                                                         ImageIcon img = new ImageIcon(selImage.toString());
                                                         Image image =  img.getImage();
                                                         g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
                                                  //System.out.println("Draw change Called");
                                                 //img.getImage(selImage.toString());

                                                           }
                                                       else
                                                       {

                                                   g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
//                                                    System.out.println("Draw Called");
                                                    }

       }
    };

   
    text1.setForeground(Color.white);
    text1.setCaretColor(Color.white);
    Font font1 = new Font("Arial", Font.PLAIN, 14);
    text1.setFont(font1); 

JTextField textfield = new JTextField ();
    text1.setOpaque(false);
   
    JScrollPane scroll1 = new JScrollPane();
    scroll1.setViewport(viewport);
    scroll1.setViewportView(text1);    


    JFrame f = new JFrame();
    f.getContentPane().add(scroll1);



  JMenuBar menubar = new JMenuBar();
  JMenu filemenu = new JMenu("File");
  //filemenu.add(new JSeparator());
  JMenu editmenu = new JMenu("Edit");
  // editmenu.add(new JSeparator());

// File Menu Items
 final JMenuItem fileNew = new JMenuItem("New");
 final JMenuItem fileOpen = new JMenuItem("Open");
 final JMenuItem fileBackground = new JMenuItem("Background");
 final JMenuItem fileSave = new JMenuItem("Save");
 final JMenuItem fileSaveAs = new JMenuItem("Save As");
 final JMenuItem fileTextColor = new JMenuItem("Text Color");
 final JMenuItem fileExit = new JMenuItem("Exit");


  filemenu.add(fileNew);
  filemenu.add(fileOpen);
  filemenu.add(fileBackground);
  filemenu.add(fileSave);
  filemenu.add(fileSaveAs);
  filemenu.add(fileTextColor);
  filemenu.add(fileExit);


// Edit Menu Items
  JMenuItem editCut = new JMenuItem("Cut");
  JMenuItem editCopy = new JMenuItem("Copy");
  //editCopy.add(new JSeparator());
  JMenuItem editPaste = new JMenuItem("Paste");
  JMenuItem editInsert = new JMenuItem("Go to line");


  editmenu.add(editCut);
  editmenu.add(editCopy);
  editmenu.add(editPaste);
  editmenu.add(editInsert);



// Set up the action listeners 

fileNew.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileNew) {
                                           //int returnVal = fc.showOpenDialog(text1);

                                     }

				}
			}
		);


fileOpen.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileOpen) {
                                           int returnVal = fc.showOpenDialog(text1);
     
  
           if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
// READ FILE

                if (file == null)
                        return;

                  FileReader reader = null;
                    try {
                            reader = new FileReader(file);
                            text1.read(reader, null);
                              } catch (IOException ex) {
                                  JOptionPane.showMessageDialog(text1,
                                     "File Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
                               } finally {
                                   if (reader != null) {
                                                   try {
                                                         reader.close();
                                             } catch (IOException x) {
                                            }
                                 }
                          }                

// END READ FILE


            } else {
                System.out.println("Open command cancelled by user.");
            }

                             /*  End Action  */
                                     }

				}
			}
		);

/************************************       CHANGE THE BACKGROUND  *****************************************/

fileBackground.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{

                                    if (e.getSource() == fileBackground) {
                                                int returnVal2 = fc.showOpenDialog(text1);     
                                                  selImage = fc.getSelectedFile();
                 

                                              repaintVal = 1;
                                          
                                     
                                        

                                     }

				}
			}
		);



fileSave.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileSave) {
// Save the File
 
                                      File file = fc.getSelectedFile();
                                                 if (file == null)
                                                       return;
                                       FileWriter writer = null;
                                       try {
                                             writer = new FileWriter(file);
                                             text1.write(writer);
                                            } catch (IOException ex) {
        JOptionPane.showMessageDialog(text1,"File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                              if (writer != null) {
                                                      try {
                                                            writer.close();
                                                          } catch (IOException x) {
                                                      }
                                               }
                                             
// End Save file                                        


                                     }

				}
			}
		);

fileTextColor.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileTextColor) {
                  // Change Text Color
                 Color foregroundColor = Color.black;

           Color fgcolor = JColorChooser.showDialog(null,"Text Color Selection",foregroundColor);
        if (fgcolor != null) {
               text1.setForeground(fgcolor);
               text1.setCaretColor(fgcolor);
           }

               
                  // End Change Text Color

                                     }

				}
			}
		);


/********************************************     SAVE AS     *********************************************/
fileSaveAs.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileSaveAs) {
                                     
// SAVE AS
                                       
 int returnVal2 = fc.showSaveDialog(text1);
     
  
           if (returnVal2 == JFileChooser.APPROVE_OPTION) {
                                      File file = fc.getSelectedFile();
                                                 if (file == null)
                                                       return;
                                       FileWriter writer = null;
                                       try {
                                             writer = new FileWriter(file);
                                             text1.write(writer);
                                            } catch (IOException ex) {
        JOptionPane.showMessageDialog(text1,"File Not Saved", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                              if (writer != null) {
                                                      try {
                                                            writer.close();
                                                          } catch (IOException x) {
                                                      }
                                               }
                                           }
            // SAVE AS END                                 


                                     }

				}
			}
		);


fileExit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				   
                                    if (e.getSource() == fileExit) {
                                                  System.exit(0);

                                     }

				}
			}
		);


  menubar.add(filemenu);
  menubar.add(editmenu);
  f.setJMenuBar(menubar);

    f.setSize(600,600);
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
 

}


  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        new AnimatedEditor().CreateAndShowGUI();
      }
    });
  }

}

