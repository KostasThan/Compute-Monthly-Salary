package graphics;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Save {
	JFrame parentFrame = new JFrame();
	 
	public String mixas() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(null);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    return fileToSave.getAbsolutePath();
		}else {
			return "";
		}
	}
	
}
