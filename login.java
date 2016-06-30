package onlinevs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;

public class login extends JFrame implements MouseListener,ActionListener,ItemListener {

	JTextField name,dob,card;
	JButton jb,next1;int uid=0;
	JLabel fgp,usr,ddob,ccard,nname;
	JRadioButton byname,bycard;
	JPasswordField pass;Font ff;
	String fpass="";
	JPanel jp,jp1;
	login()
	{
		
		super("Welcome to Online Voting System:Login");
		setResizable(false);
		//setBackground(Color.blue);
		JLabel hd=new JLabel("ONLINE VOTING SYSTEM");
		hd.setForeground(Color.blue);
		
	
		hd.setFont(new Font("Serif",Font.BOLD,34));
		//hd.setOpaque(true);
		
		//hd.setBorder(BorderFactory.createEmptyBorder(20, 10, 20,10));
		getContentPane().setBackground(Color.PINK);
		
		
		JPanel jp1=new JPanel();
		jp1.setBounds(130,110,750,2);
		//jp1.add(new JSeparator(JSeparator.HORIZONTAL),BorderLayout.LINE_START);
		jp1.setBackground(Color.black);
		add(jp1);
		
		
	//	hd.setBackground(Color.gray);
		hd.setBounds(280,50,700,60);
		add(hd);
		JPanel jp=new JPanel();
		jp.setBounds(490,220,1,130);
		jp.add(new JSeparator(JSeparator.HORIZONTAL),BorderLayout.LINE_START);
		jp.setBackground(Color.red);
		add(jp);
		
		
		JLabel log=new JLabel("Login");
		setLayout(null);
		log.setBounds(450,10,180,300);
		log.setForeground(Color.BLUE);
		log.setFont(new Font("Serif",Font.BOLD,30));
		add(log);
		
		//left side
		
		//font for jfields
		ff=new Font("Serif",Font.BOLD,20);
		nname=new JLabel("Name");
		nname.setBounds(100,30,300,500);
		nname.setFont(ff);
		add(nname);
		name=new JTextField();
		name.setBounds(160,268,300,25);
		add(name);
		
		ddob=new JLabel("DOB (dd/mm/yy)");
		ddob.setBounds(100,70,300,500);
		ddob.setFont(ff);
		add(ddob);
		dob=new JTextField();
		dob.setBounds(260,310,200,25);
		add(dob);
		
	ddob.setEnabled(false);
	dob.setEnabled(false);
	
	name.setEnabled(false);
	nname.setEnabled(false);
		ccard=new JLabel("Voter Id Number:");
		ccard.setBounds(530,50,450,500);
		ccard.setFont(ff);
		add(ccard);
		card=new JTextField();
		card.setBounds(690,290,200,25);
		add(card);
		
		
	}
	
	public void choice()
	{
		JLabel cc=new JLabel("Select a Login choice below:");
		cc.setBounds(350,-50,300,500);
		cc.setFont(ff);
		add(cc);
		
		byname=new JRadioButton("By Name & DOB");
		bycard=new JRadioButton("By Voter ID Number");
		byname.setBounds(230,210,310,50);
		byname.setOpaque(false);
		byname.addItemListener(this);
		byname.addMouseListener(this);
		byname.setFont(ff);
		add(byname);
		bycard.setBounds(580,210,310,50);
		bycard.setFont(ff);
		bycard.setOpaque(false);
		bycard.setSelected(true);
		bycard.addItemListener(this);
		
		add(bycard);
		
	}
	public void password()
	{
		JLabel ppas=new JLabel("Password:");
		ppas.setBounds(310,120,450,500);
		ppas.setFont(ff);
		add(ppas);
		pass=new JPasswordField();
		pass.setBounds(410,360,250,28);
		add(pass);
		
		jb=new JButton("Enter");
		jb.setBounds(430,440,200,40);
		jb.addActionListener(this);
		add(jb);
		
		
		
		
	}
	public void newuser()
	{
		usr=new JLabel("New User? Register Here");
		usr.setBounds(410,410,250,200);
		usr.setFont(ff);
		usr.setForeground(Color.RED);
		add(usr);
		
		jp=new JPanel();
		jp.setBounds(410,505,250,25);
		jp.setOpaque(false);
		//jp.setBackground(Color.red);
		jp.addMouseListener(this);
		add(jp);
		
		
		fgp=new JLabel("Forgot Password? Recover Here");
		fgp.setBounds(550,310,350,200);
		fgp.setFont(ff);
		fgp.setForeground(Color.RED);
		add(fgp);
		
		jp1=new JPanel();
		jp1.setBounds(550,405,350,25);
		jp1.setOpaque(false);
		//jp.setBackground(Color.red);
		jp1.addMouseListener(this);
		add(jp1);
		
		next1=new JButton("Admin Portal");
		next1.setBounds(390,570,300,45);
		
		next1.addActionListener(this);
		next1.setBackground(Color.green);
		add(next1);
	}
	
/*	public static void main(String...k)
	{
		login n=new login();
	//	n.
	//n.setBackground(Color.black);
		n.setDefaultCloseOperation(EXIT_ON_CLOSE);
		n.setBounds(100,10,1024,700);
		n.setVisible(true);
		n.setLayout(null);
		n.choice();
		n.password();
		n.newuser();
	}
*/
	@Override
	public void actionPerformed(ActionEvent e){
		
	//	System.out.println("click");
		if(e.getSource()==next1)
		{
			this.setVisible(false);
			this.dispose();
			new adminlogin();
			
			
		}
		else
		{
		if(pass.isEnabled())
		{
		String cnum,nname,ddob,passd;
		
		
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/onlinevs";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(url,"root","toor");
			st=con.createStatement();
		}
		catch(Exception ee)
		{
			System.out.println("dd");
		}  
		  
		if(bycard.isSelected())
		{
			cnum=card.getText();
			passd=pass.getText();
			try {
				rs=st.executeQuery("Select id from user where cardno=\""+cnum+"\" and password=\""+passd+"\"");
				if(rs.next()&&!(cnum.equals("NA")))
				{
					if(rs.first())
						uid=rs.getInt(1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		else if(byname.isSelected())
		{
			nname=name.getText();
			ddob=dob.getText();
			passd=pass.getText();
			int mm=0,yy=0,dd=0;
			StringTokenizer stt=new StringTokenizer(ddob,"/");
			while(stt.hasMoreTokens())
			{
				if(stt.countTokens()==3)
				{
					dd=Integer.parseInt(stt.nextToken());
				}
				else if(stt.countTokens()==2)
				{
					mm=Integer.parseInt(stt.nextToken());
				}
				else if(stt.countTokens()==1)
				{
					yy=Integer.parseInt(stt.nextToken());
				}
			}
			if(dd>31||mm>12||yy>2015)
			{
				JOptionPane.showMessageDialog(this,"Invalid Date of Birth");
				return;
			}
			String db=yy+"-"+mm+"-"+dd;
			
			try {
				rs=st.executeQuery("Select id from user where name=\""+nname+"\" and password=\""+passd+"\" and dob=\""+db+"\"");
				if(rs.next())
				{
					if(rs.first())
						uid=rs.getInt(1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		}
		if(isLoggedin())
		{
			//System.out.println("loogedin");
			this.setVisible(false);
			this.dispose();
			if(!pass.isEnabled())
			{
				JOptionPane.showMessageDialog( this,"Your Password is "+fpass+" Please keep in mind!!!");
			}
			
			try {
				new dashboard(uid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
				 JOptionPane.showMessageDialog( this,"Invalid Login-Details!!!");
				 pass.setEnabled(true);
					return;
			 
		}
	}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==jp)
		{
		this.setEnabled(false);
		this.setVisible(false);
		this.dispose();
		new register();
		}
		else if(e.getSource()==jp1)
		{
		//	System.out.println("click");
			
String sq="";
			
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			
			String url="jdbc:mysql://localhost:3306/onlinevs";
			try
			{
				Class.forName("com.mysql.jdbc.Driver");  
				con=DriverManager.getConnection(url,"root","toor");
				st=con.createStatement();
			}
			catch(Exception ee)
			{
				
			}
			

			try{
				rs=st.executeQuery("Select secure from user where id="+uid);
			
				if(rs.next())
				{
					sq=rs.getString(1);
					System.out.println(rs.getString(1));
				}
				
			}
			catch(Exception ee)
			{
				System.out.println("dd");
			}  
			
			
			if(bycard.isSelected())
			{
			String	cnum=card.getText();
				
				if(cnum.isEmpty())
				{
				JOptionPane.showMessageDialog(this,"First Enter the Card Number");
				return;
				}
				else
				{

					String sec=JOptionPane.showInputDialog(this,"Enter your First School Name:","Security Question",JOptionPane.OK_CANCEL_OPTION);
					if(sec.equals("")||sec.isEmpty())
					{
						JOptionPane.showMessageDialog(this,"Security Answer was Empty!!!");
						return;
					}
					else
					{
					try {
						rs=st.executeQuery("Select id,password from user where cardno=\""+cnum+"\" and secure=\""+sec+"\"");
						if(rs.next()&&!(cnum.equals("NA")))
						{
							if(rs.first())
							{
								uid=rs.getInt(1);
							fpass=rs.getString(2);	
							}
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				pass.setEnabled(false);	
				}
			}
			else if(byname.isSelected())
			{
				String nname=name.getText();
				String ddob=dob.getText();
				int mm=0,yy=0,dd=0;
				StringTokenizer stt=new StringTokenizer(ddob,"/");
				while(stt.hasMoreTokens())
				{
					if(stt.countTokens()==3)
					{
						dd=Integer.parseInt(stt.nextToken());
					}
					else if(stt.countTokens()==2)
					{
						mm=Integer.parseInt(stt.nextToken());
					}
					else if(stt.countTokens()==1)
					{
						yy=Integer.parseInt(stt.nextToken());
					}
				}
				if(dd>31||mm>12||yy>2015)
				{
					JOptionPane.showMessageDialog(this,"Invalid Date of Birth");
					return;
				}
				String db=yy+"-"+mm+"-"+dd;
				
				
				if((nname.isEmpty()||ddob.isEmpty()))
				{
					JOptionPane.showMessageDialog(this,"Enter the Name & DOB First");
					return;
				}
				else
				{
					
					String sec=JOptionPane.showInputDialog(this,"Enter your First School Name:","Security Question",JOptionPane.OK_CANCEL_OPTION);
					if(sec.equals("")||sec.isEmpty())
					{
						JOptionPane.showMessageDialog(this,"Security Answer was Empty!!!");
						return;
					}
					else
					{
						try {
							rs=st.executeQuery("Select id,password from user where name=\""+nname+"\" "+" and dob=\""+db+"\"and secure=\""+sec+"\"");
							if(rs.next())
							{
								if(rs.first())
								{
									uid=rs.getInt(1);
								fpass=rs.getString(2);	
								}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				pass.setEnabled(false);	
				}
				
			}
			
			
			
			
			}
			
			
			
			
			
			
		}
		if(e.getSource()==byname)
		{
			bycard.setSelected(false);
			byname.setSelected(true);
		}
		
	}

	public boolean isLoggedin()
	{
		if(uid==0)
			return false;
		else
			return true;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()!=byname)
		this.setCursor(Cursor.HAND_CURSOR);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource()!=byname)
		this.setCursor(Cursor.DEFAULT_CURSOR);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(bycard.isSelected())
		{
			byname.setSelected(false);
			ddob.setEnabled(false);
			dob.setEnabled(false);
			
			name.setEnabled(false);
			nname.setEnabled(false);
			ccard.setEnabled(true);
			card.setEnabled(true);
		}
		else if(byname.isSelected())
		{
			bycard.setSelected(false);
			ddob.setEnabled(true);
			dob.setEnabled(true);
			
			name.setEnabled(true);
			nname.setEnabled(true);
			ccard.setEnabled(false);
			card.setEnabled(false);
			
		}
	}
	
}
