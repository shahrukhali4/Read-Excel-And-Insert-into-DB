package myProject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Export {

	
	 private static final String FILE_NAME = "C:\\Users\\syed.ali\\Desktop\\LMMS_VENDOR_LICENSE.xlsx";

	    public static void main(String[] args) {
	    	
	    	 try {

	             FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
	             Workbook workbook = new XSSFWorkbook(excelFile);
	             Sheet datatypeSheet = workbook.getSheetAt(0);
	             Iterator<Row> iterator = datatypeSheet.iterator();
	             
	             if (iterator.hasNext()) {
	                 iterator.next();
	             }
	           	             
	             while (iterator.hasNext()) {

	                 Row currentRow = iterator.next();
	                 Iterator<Cell> cellIterator = currentRow.iterator();
	                 List list = new ArrayList();
	                 while (cellIterator.hasNext()) {

	                     Cell currentCell = cellIterator.next();
	                     
					if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
						if ((currentCell.getStringCellValue().toUpperCase()).equalsIgnoreCase("NA")) {
							list.add(null);
						} else {
							list.add(currentCell.getStringCellValue());
						}
					} else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	                         list.add(currentCell.getNumericCellValue());
	                     }
	                                          
	                     
	                 }
	                 double LICENSE_ID=(Double) list.get(0);
	                 String LICENSE_FILE_NAME=(String) list.get(1);
	                 String LICENSE_FILE_DESCRIPTION=(String) list.get(2);
	                 String LICENSE_FILE=(String) list.get(3);
	                 String LICENSE_KEY=(String) list.get(4);
	                 String LICENSE_TYPE=(String) list.get(5);
	                 String VENDOR_NAME=(String) list.get(6);
	                 String EXPIRY_DATE=(String) list.get(7);
	                 String CREATED_BY=(String) list.get(8);
	                 String CREATED_AT=(String) list.get(9);
	                 String UPDATED_BY=(String) list.get(10);
	                 String UPDATED_AT=(String) list.get(11);
	                 String MODEL=(String) list.get(12);
	                 double COUNT=(Double) list.get(13);
	                 double LICENSE_TYPE_ID=(Double) list.get(14);
	               
	        
	                
	         		Connection con = null;
	        
	         		PreparedStatement pstmt = null;
	      
	         		Class.forName("oracle.jdbc.driver.OracleDriver");
	        		con= DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/TIPSM1","IPSM_CUSTOMER_PORTAL", "Ipsm4_portal");
	         			
	         			String sql ="INSERT INTO LMMS_VENDOR_LICENSE (LICENSE_ID,LICENSE_FILE_NAME, LICENSE_FILE_DESCRIPTION, LICENSE_FILE, LICENSE_KEY, LICENSE_TYPE ,VENDOR_NAME, EXPIRY_DATE,CREATED_BY,CREATED_AT,UPDATED_BY,UPDATED_AT,MODEL,COUNT,LICENSE_TYPE_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         			
	         			byte[] byteData = LICENSE_FILE.getBytes("UTF-8");
		                 Blob blobData = con.createBlob();
		                 blobData.setBytes(1, byteData);
	         			
	         			pstmt=con.prepareStatement(sql);
	         			pstmt.setDouble(1, LICENSE_ID);
	         			pstmt.setString(2, LICENSE_FILE_NAME);
	         			pstmt.setString(3, LICENSE_FILE_DESCRIPTION);
	         			pstmt.setBlob(4, blobData);
	         			pstmt.setString(5, LICENSE_KEY);
	         			pstmt.setString(6, LICENSE_TYPE);
	         			pstmt.setString(7, VENDOR_NAME);
	         			pstmt.setString(8, EXPIRY_DATE);
	         			pstmt.setString(9, CREATED_BY);
	         			pstmt.setString(10, CREATED_AT);
	         			pstmt.setString(11, UPDATED_BY);
	         			pstmt.setString(12, UPDATED_AT);
	         			pstmt.setString(13, MODEL);
	         			pstmt.setDouble(14, COUNT);
	         			pstmt.setDouble(15, LICENSE_TYPE_ID);
	         			
	         			pstmt.executeUpdate();  
	       
	         
	             }
	         } catch (FileNotFoundException e) {
	             e.printStackTrace();
	         } catch (IOException e) {
	             e.printStackTrace();
	         } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
}
