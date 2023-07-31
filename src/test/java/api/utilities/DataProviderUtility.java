package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

	String path = System.getProperty("user.dir") + "//testdata//userTestData.xlsx";
	XLSUtility xls = new XLSUtility(path);
	
	@DataProvider(name="data")
	public String[][] getAllData() throws IOException {
		int rowCount = xls.getRowCount("sheet1");
		int columnCount = xls.getCellCount("sheet1", 1);
		String [][] data = new String[rowCount][columnCount];
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				data[i-1][j] = xls.getCellData("sheet1", i, j);
			}
		}
		return data;
	}
	
	@DataProvider(name="usernames")
	public String[] getUsername() throws IOException
	{
		int rowCount = xls.getRowCount("sheet1");
		String [] usernames = new String[rowCount];
		for(int i=1;i<=rowCount;i++)
		{
			usernames[i-1] = xls.getCellData("sheet1", i, 1);
		}
		return usernames;
	}
	
	/*
	 * int rowCount = xls.getRowCount("sheet1");
		int columnCount = xls.getCellCount("sheet1", 1);
		String [][] data = new String[rowCount][columnCount];
		String [] usernames = new String[rowCount];
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				data[i-1][j] = xls.getCellData("sheet1", i, j);
			}
		}
		
		for(int k=0;k<data.length;k++)
		{
			if(k == 1)
			{
			usernames[k-1] = data[k][k+1];
			}
		}
		return usernames;
	 */
}
