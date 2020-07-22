package graphics;

import java.util.Scanner;

import javax.swing.JFileChooser;

public class OpenFile {
	 
	 //Declare Variable
	 JFileChooser fileChooser = new JFileChooser();
	 StringBuilder sb = new StringBuilder();
	 
	 public String PickMe() throws Exception{
	  
	  if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	   
	   //get the file
	   java.io.File file = fileChooser.getSelectedFile();
	   return file.getAbsolutePath();

	  }
	  else return "";
	}
}