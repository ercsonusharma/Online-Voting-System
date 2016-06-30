package onlinevs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class updat extends JFrame implements ActionListener{

	Vector<String> que_u=new Vector();
	Vector<Integer> typ_u=new Vector();
	
	Vector<String> tim_u=new Vector();
	Vector<String> tim_e=new Vector();
	
	Vector<String> que_e=new Vector();
	Vector<Integer> typ_e=new Vector();
	JButton prof,vot,update,exi,home;
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	static int uid;
	updat(){}
	updat(int uid) throws Exception {
		
		super("Welcome to Online Voting System:News & Updates");
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
		
		
		String pr="<html><u><span style=\"color:#2A0000\">Updates</u></html>";
		JLabel log1=new JLabel(pr);
		log1.setBounds(550,180,380,30);
		log1.setForeground(Color.DARK_GRAY);
		log1.setFont(new Font("Elephant",Font.BOLD,22));
		add(log1);

		
		
		rs=st.executeQuery("Select quest,type,live,expire from voting");
		
		while(rs.next())
		{
		
			String lv=rs.getString(3),exp=rs.getString(4);
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date lvt=sdf.parse(lv),expt=sdf.parse(exp);
			if(new java.util.Date().getTime()<lvt.getTime())
			{
			
				que_u.addElement(rs.getString(1));
				typ_u.add(rs.getInt(2));
				tim_u.addElement(lv);
			}
			if(new java.util.Date().getTime()>expt.getTime()&&new java.util.Date().getTime()<expt.getTime()+7*24*60*60*1000)
			{

				que_e.addElement(rs.getString(1));
				typ_e.add(rs.getInt(2));
				tim_e.addElement(exp);
			}
		}
		JLabel log2=new JLabel("Upcoming Events:-");
		log2.setBounds(350,220,380,30);
		log2.setForeground(Color.DARK_GRAY);
		log2.setFont(new Font("Arial",Font.BOLD,20));
		add(log2);
		int y=250;
		for(int i=0;i<que_u.size();i++)
		{
			JLabel log4=new JLabel(que_u.elementAt(i)+" on "+tim_u.elementAt(i));
			log4.setBounds(350,y,580,30);
			log4.setForeground(Color.blue);
			log4.setFont(new Font("Arial",Font.ITALIC,18));
			add(log4);
			y+=30;
		}
		
		JLabel log3=new JLabel("Events Expired in Last 7 Days:-");
		log3.setBounds(350,y+10,380,30);
		log3.setForeground(Color.DARK_GRAY);
		log3.setFont(new Font("Arial",Font.BOLD,20));
		add(log3);
		y+=20;
		
		for(int i=0;i<que_e.size();i++)
		{
			y+=30;
			JLabel log4=new JLabel(que_e.elementAt(i)+" on "+tim_e.elementAt(i));
			log4.setBounds(350,y,580,30);
			log4.setForeground(Color.blue);
			log4.setFont(new Font("Arial",Font.ITALIC,18));
			add(log4);
			
		}
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
		if(e.getSource()==home)
		{
			try {
				new dashboard(uid);
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
			
		
			int reply=JOptionPane.showConfirmDialog(null,"Do you want to realy Exit?", "Are You Sure?",JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		
	}
}
