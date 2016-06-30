package onlinevs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class doreg extends JFrame{

	private boolean done=false;
	public boolean don()
	{
		return done;
	}
	
	doreg(String ccard,String nname,int mm,int dd,int yy,String mmail,String passd1,String cont,String sq) throws SQLException
	{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		setResizable(false);
		
		String url="jdbc:mysql://localhost:3306/onlinevs";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(url,"root","toor");
			
		}
		catch(Exception e)
		{
			System.out.println("dd");
		}
		st=con.createStatement();  
		  
		
		///  int rst=st.executeUpdate("delete from login where email=\"sonusha\" and password=\"123\"");
		  //if(rst>0)
			//  System.out.println("done");
		  
		  //int x=st.executeUpdate("update login set password=\"1234\" where email=\"no@d\"");  
		  
		 rs=st.executeQuery("select id from user where cardno=\""+ccard+"\"");
		 
		 
			//	  if(rs.isFirst())
				//	  System.out.println("Found");
				  //else
					//  System.out.println("Not foune");
				  int uid=0;
				  while(rs.next()&&ccard.compareToIgnoreCase("NA")!=0)
				  {
					  
					  if(rs.isFirst())
					  {
						  
						  uid=rs.getInt(1);
					  }
				  }
				  
				  rs=st.executeQuery("select id from user where mail=\""+mmail+"\"");
					 
				  int uuid=0;
				  while(rs.next())
				  {
					  
					  if(rs.isFirst())
					  {
						  
						  uuid=rs.getInt(1);
					  }
				  }
				  String dob=yy+"-"+mm+"-"+dd;
	
	if(uuid!=0)
	{
		 JOptionPane.showMessageDialog( this,"User already registered with this E-Mail Address!!!");
			return;
	 }
	else if(uuid==0&&uid==0)
	{
					
					
							JOptionPane.showMessageDialog( this,"You will be treated as candidate having no Voter-id card");
							int x=st.executeUpdate("insert into user set name=\""+nname+"\",dob=\""+dob+"\",mail=\""+mmail+"\",password=\""+passd1+"\",contno=\""+cont+"\",cardno=\"NA\",secure=\""+sq+"\"");
							 if(x>0)
							 {
								 JOptionPane.showMessageDialog( this,"Congrats!!You temporary have been successfully registered!!!Login Now");
								 done=true;
							 }
							 
						
				 }
				 else
				 {
					 rs=st.executeQuery("Select * from user where id="+uid);
					 if(rs.next())
					 {
						 if(rs.getString(2)==null||rs.getString(2).isEmpty())
						 {
							 
							 int x=st.executeUpdate("update user set name=\""+nname+"\",dob=\""+dob+"\",mail=\""+mmail+"\",password=\""+passd1+"\",contno=\""+cont+"\",secure=\""+sq+"\" where id="+uid);
							 if(x>0)
							 {
								 JOptionPane.showMessageDialog( this,"Congrats!!You have been successfully registered!!!Login Now");
									done=true;
							 }
						 }
						 else
						 {
							 JOptionPane.showMessageDialog( this,"User already registered with this Voter-Id Card!!!");
								return;
						 }
					 }
				 }
		con.close();  
	}
	
}
