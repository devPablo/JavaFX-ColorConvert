package application;

public class ColorConvert {
	public String hexValue;
	
	public String redValue;
	public String greenValue;
	public String blueValue;
	
	private String[] rgbArray = new String[3];
	
	public ColorConvert(String color) {
		if(color.matches("^[0-9a-fA-F]{3,6}$")) {
			this.hexValue = color;
		} else {
			rgbArray = color.trim().replaceAll("[()]", "").split(" ");
			this.redValue = rgbArray[0];
			this.greenValue = rgbArray[1];
			this.blueValue = rgbArray[2];
		}		
	}
	
	public String toHex() {
		String HEX = String.format("#%02X%02X%02X", Integer.valueOf(redValue), Integer.valueOf(greenValue), Integer.valueOf(blueValue)); 
		return HEX;
	}

	public String toRGB() {	
		String RGB;
		try {
			redValue = Integer.valueOf(hexValue.substring(0, 2), 16).toString();
			greenValue = Integer.valueOf(hexValue.substring( 2, 4 ), 16 ).toString();
			blueValue = Integer.valueOf(hexValue.substring( 4, 6 ), 16 ).toString();			
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	    RGB = "(" + redValue + ", " + greenValue + ", " + blueValue + ")";
	    if(!RGB.contains("null")) {
	    	return RGB;
	    }
	    return null;
	}
	
}
