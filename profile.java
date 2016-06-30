package onlinevs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class profile extends JFrame implements ActionListener{

	JButton prof,vot,update,exi,home;
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	static int uid;
	profile(){}
	profile(int uid) throws Exception {
		
		super("Welcome to Online Voting System:DashBoard");
		//System.out.println(uid);
		this.uid=uid;
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
		
		
		JLabel log=new JLabel(getdetails("name")+"'s DashBoard");
		setLayout(null);
		log.setBounds(350,0,580,300);
		log.setForeground(Color.DARK_GRAY);
		log.setFont(new Font("Serif",Font.BOLD,22));
		add(log);
		
		JPanel jp2=new JPanel();
		jp2.setBounds(10,170,980,2);
		//jp1.add(new JSeparator(JSeparator.HORIZONTAL),BorderLayout.LINE_START);
		jp2.setBackground(Color.white);
		add(jp2);
		
		JPanel jp3=new JPanel();
		jp3.setBounds(250,170,2,500);
		//jp1.add(new JSeparator(JSeparator.HORIZONTAL),BorderLayout.LINE_START);
		jp3.setBackground(Color.white);
		add(jp3);
		String mes="<html>&nbsp;<span style=\"font-size:18px\">Welcome,</span><br><p>"+getdetails("name")+"</p></html>";
		JLabel wel=new JLabel(mes);
	
		wel.setBounds(50,200,200,75);
		wel.setForeground(Color.DARK_GRAY);
		wel.setFont(new Font("Serif",Font.BOLD,16));
		add(wel);
		
		
		
		home=new JButton("Home");
		home.setBounds(50,290,160,35);
		home.addActionListener(this);
		add(home);
		
		prof=new JButton("Pofile");
		prof.setBounds(50,340,160,35);
		prof.addActionListener(this);
		add(prof);
		
		vot=new JButton("Voting Zone");
		vot.setBounds(50,390,160,35);
		vot.addActionListener(this);
		add(vot);
		
		update=new JButton("Updates");
		update.setBounds(50,440,160,35);
		update.addActionListener(this);
		add(update);
		
		exi=new JButton("Exit");
		exi.setBounds(50,490,160,35);
		exi.addActionListener(this);
		add(exi);
		String pr="<html><u><span style=\"color:#2A0000\">Personal Profile</u></html>";
		JLabel log1=new JLabel(pr);
		log1.setBounds(450,180,380,30);
		log1.setForeground(Color.DARK_GRAY);
		log1.setFont(new Font("Elephant",Font.BOLD,22));
		add(log1);
		
		Font ff=new Font("Serif",Font.BOLD,20);
		JLabel nname=new JLabel("Name : ");
		nname.setBounds(420,230,100,35);
		nname.setFont(ff);
		add(nname);
		
		JLabel nname1=new JLabel(getdetails("name"));
		nname1.setBounds(520,230,400,35);
		nname1.setFont(ff);
		add(nname1);
		
		JLabel ddob=new JLabel("DOB (yy-mm-dd):");
		ddob.setBounds(328,270,200,35);
		ddob.setFont(ff);
		add(ddob);
		JLabel ddob1=new JLabel(getdetails("dob"));
		ddob1.setBounds(520,270,250,35);
		ddob1.setFont(ff);
		add(ddob1);
		
		
		JLabel ccard=new JLabel("Voter Id Number:");
		ccard.setBounds(332,310,200,35);
		ccard.setFont(ff);
		add(ccard);
		
		JLabel ccard1=new JLabel(getdetails("cardno"));
		ccard1.setBounds(520,310,300,35);
		ccard1.setFont(ff);
		add(ccard1);
		
		JLabel mmail=new JLabel("E-Mail:");
		mmail.setBounds(416,350,200,35);
		mmail.setFont(ff);
		add(mmail);
		
		JLabel mmail1=new JLabel(getdetails("mail"));
		mmail1.setBounds(520,350,500,35);
		mmail1.setFont(ff);
		add(mmail1);

		JLabel ccont=new JLabel("Contact Number:");
		ccont.setBounds(338,390,200,35);
		ccont.setFont(ff);
		add(ccont);
		
		JLabel ccont1=new JLabel(getdetails("contno"));
		ccont1.setBounds(520,390,200,35);
		ccont1.setFont(ff);
		add(ccont1);
		
		setVisible(true);
		
		setBounds(100,10,1024,700);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void connection()
	{
		String url="jdbc:mysql://localhost:3306/onlinevs";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(url,"root","toor");
			st=con.createStatement();  
			
		}
		catch(Exception e)
		{
			System.out.println("dd");
		}
	}
	public String getdetails(String x) throws Exception
	{
		String res="";
		connection();
		rs=st.executeQuery("Select "+x+" from user where id="+uid );
		if(rs.first())
			res=rs.getString(1);
		return res;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()!=exi)
		{
		this.setVisible(false);
		this.dispose();
		}
		if(e.getSource()==home)
		{
			try {
				
				new dashboard(uid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==prof)
		{
			//System.out.println("prof");
			try {
				
				new profile(uid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==vot)
		{
			try {
				new voting(uid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==update)
		{
			try {
				new updat(uid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==exi)
		{
			
		
			int reply=JOptionPane.showConfirmDialog(null,"Do you want to really Exit?", "Are You Sure?",JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}
	
/*	public static void main(String...l) throws Exception
	{
		new profile(5);
	}*/
}
