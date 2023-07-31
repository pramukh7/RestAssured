package api.utilities;

import java.util.ResourceBundle;

public class GetPropertiesFileEndPoints {
	
	public static ResourceBundle getPropertiesFileResourceBundle(String filename)
	{
		return ResourceBundle.getBundle(filename);
	}

}
