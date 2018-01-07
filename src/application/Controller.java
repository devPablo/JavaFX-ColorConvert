package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
	@FXML TextField hex, rgb;
	@FXML AnchorPane backgroundAnchorPane;
	private String hexValue, rgbValue;
	private String[] rgbArray = new String[3];
	private boolean isHex, isRGB;
	
	public void initialize() {
		hex.setPromptText("hex");
		rgb.setPromptText("rgb");
	}
	
	@FXML public void hexActionHandler(KeyEvent e) {	
		hexValue = hex.getText().replaceAll("#", "");
		isHex = hex.getText().matches("^#[0-9a-fA-F]{3,6}$");
		
		if(isHex) {
			rgbValue = new ColorConvert(hexValue).toRGB();		
			rgb.setText(rgbValue);
			
			backgroundAnchorPane.setStyle("-fx-background-color:" + hex.getText() + ";");
			
			rgbArray = rgbValue.trim().replaceAll("[()]", "").split(",");
			String colorValue = textColorUpdater(rgbArray);
			hex.setId(colorValue);
			rgb.setId(colorValue);
			
		}
	}
	
	@FXML public void rgbActionHandler(KeyEvent e) {
		rgbArray = rgb.getText().trim().replaceAll("[()]", "").split(",");
		try {
			rgbValue = rgbArray[0] + rgbArray[1] + rgbArray[2];
		}catch(ArrayIndexOutOfBoundsException ex) {
			
		}
		int[] tempRGB = new int[rgbArray.length];
		for(int i = 0; i < rgbArray.length; i++) {
			try {
				tempRGB[i] = Integer.parseInt(rgbArray[i].trim());
			} catch (NumberFormatException ex) {
				
			}
		}
		if(rgbArray.length == 3) {
			isRGB = (tempRGB[0] >= 0 && tempRGB[0] <= 255) && (tempRGB[1] >= 0 && tempRGB[1] <= 255) && (tempRGB[2] >= 0 && tempRGB[2] <= 255) ? true : false;
		}
		if(isRGB) {
			hexValue = new ColorConvert(rgbValue).toHex();
			hex.setText(hexValue);
			
			backgroundAnchorPane.setStyle("-fx-background-color: rgb(" + tempRGB[0] + ", " + tempRGB[1] + ", " + tempRGB[2] + ");");
			String colorValue = textColorUpdater(rgbArray);
			hex.setId(colorValue);
			rgb.setId(colorValue);
		}
	}
	
	public String textColorUpdater(String[] stringArray) {
		if((Integer.parseInt(stringArray[0].trim()) <= 124) && (Integer.parseInt(stringArray[1].trim()) <= 124) && Integer.parseInt(stringArray[2].trim()) <= 124) {
			return "white";
		}else {
			return "black";
		}
	}

}

	
