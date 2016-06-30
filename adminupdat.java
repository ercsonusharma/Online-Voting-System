package onlinevs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class adminupdat extends JFrame implements ActionListener{

	JTextArea que;
	JTextField opt,choice[]=new JTextField[4],live,exp;
	JRadioButton type1,type2;
	JButton prof,vot,update,exi,home,fin;
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	static int uid;
	adminupdat() throws Exception {
		
		super("Welcome to Online Voting System:Admin Portal:Add voting Events");
	//	System.out.println(uid);
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
		
		
		JLabel log=new JLabel("Welcome to Admin Portal");
		setLayout(null);
		log.setBounds(390,0,580,300);
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
		String mes="<html>&nbsp;<span style=\"font-size:18px\">Welcome,</span><br><p>&nbsp;&nbsp;&nbsp;&nbsp;Admin</p></html>";
		JLabel wel=new JLabel(mes);
	
		wel.setBounds(50,220,200,75);
		wel.setForeground(Color.DARK_GRAY);
		wel.setFont(new Font("Serif",Font.BOLD,16));
		add(wel);
		
		
		
		home=new JButton("Home");
		home.setBounds(50,290,160,35);
		home.addActionListener(this);
		add(home);
		
		prof=new JButton("Manage Pofile");
		prof.setBounds(50,340,160,35);
		prof.addActionListener(this);
		add(prof);
		
		vot=new JButton("Manage Voting Zone");
		vot.setBounds(50,390,160,35);
		vot.addActionListener(this);
		add(vot);
		
		update=new JButton("Add Events");
		update.setBounds(50,440,160,35);
		update.addActionListener(this);
		add(update);
		
		exi=new JButton("Exit");
		exi.setBounds(50,490,160,35);
		exi.addActionListener(this);
		add(exi);
		
		
		String pr="<html><u><span style=\"color:#2A0000\">Add Voting Events</u></html>";
		JLabel log1=new JLabel(pr);
		log1.setBounds(450,180,380,30);
		log1.setForeground(Color.DARK_GRAY);
		log1.setFont(new Font("Elephant",Font.BOLD,22));
		add(log1);

		
		Font ff=new Font("Serif",Font.BOLD,20);
		JLabel nname=new JLabel("Question:-");
		nname.setBounds(300,218,300,25);
		nname.setFont(ff);
		add(nname);
		que=new JTextArea();
		que.setBounds(420,218,350,85);
		add(que);
		
		JLabel ddob=new JLabel("Voting Type:");
		ddob.setBounds(300,310,300,25);
		ddob.setFont(ff);
		add(ddob);
		type1=new JRadioButton("1)Adult");
		type1.setBounds(420,310,250,25);
		add(type1);
	
		
		
		type2=new JRadioButton("2)Non-Adult");
		type2.setBounds(420,350,250,25);
		add(type2);
		

		JLabel mmail=new JLabel("Choice:");
		mmail.setBounds(300,390,300,25);
		mmail.setFont(ff);
		//mmail.setFont(ff);
		
		add(mmail);
		
		for(int i=0;i<4;i++)
		{
			JLabel mmail1=new JLabel((i+1)+".)");
			mmail1.setBounds(390,390+i*40,30,25);
			mmail1.setFont(ff);
			
			add(mmail1);
			choice[i]=new JTextField();
			choice[i].setBounds(420,390+i*40,350,35);
			add(choice[i]);
		}
		

		fin=new JButton("Add Now");
		fin.setBounds(820,590,160,35);
		fin.addActionListener(this);
		add(fin);
		
		JLabel mmail2=new JLabel("Live at:");
		mmail2.setBounds(300,570,80,25);
		mmail2.setFont(ff);
		
		add(mmail2);
		
		live=new JTextField("yyyy-mm-dd hh:mm:ss");
		live.setBounds(420,570,350,35);
		add(live);
		
		JLabel mmail3=new JLabel("Expire at:");
		mmail3.setBounds(300,610,100,25);
		mmail3.setFont(ff);
		
		add(mmail3);
		
		exp=new JTextField("yyyy-mm-dd hh:mm:ss");
		exp.setBounds(420,610,350,35);
		add(exp);
		
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
		
		if(e.getSource()!=exi&&e.getSource()!=fin)
		{
		this.setVisible(false);
		this.dispose();
		}
		if(e.getSource()==prof)
		{
			//System.out.println("prof");
			try {
				
				new adminprofile();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==home)
		{
			try {
				new admindash();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==vot)
		{
			try {
				new adminvoting();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==update)
		{
			try {
				new adminupdat();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==fin)
		{
			if(que.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Question field is Empty!!!");
				return;
			}
			
			if(choice[0].getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "At least one choice is necessary!!!");
				return;
			}

			if(live.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Live Field is empty!!!");
				return;
			}
			if(exp.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Expire Field is empty!!!");
				return;
			}
			
			if((!type1.isSelected())&&(!type2.isSelected()))
			{
				JOptionPane.showMessageDialog(this, "Select the type!!!");
				return;
			}
			int type=0;
			connection();
			if(type1.isSelected())
				type=1;
			
			try {
				boolean done=false;
				int x=st.executeUpdate("Insert into voting set quest=\""+que.getText()+"\",type="+type+" ,live=\""+live.getText()+"\""+",expire=\""+exp.getText()+"\"");
				int qid=0;
				rs=st.executeQuery("Select id from voting where quest=\""+que.getText()+"\" and type="+type+" and live=\""+live.getText()+"\""+" and expire=\""+exp.getText()+"\"");
				if(rs.next())
					qid=rs.getInt(1);
				if(x>0)
				{
					for(int j=0;j<4;j++)
					{
						if(!choice[j].getText().isEmpty())
						{
							int c=st.executeUpdate("insert into link set cand=\""+choice[j].getText()+"\""+",qid="+qid+",total=0");
							if(c>0)
								done=true;
						}
					}
				}
				if(done)
				{
					JOptionPane.showMessageDialog(this, "Event Successfully added!!!");
					this.dispose();
					new adminupdat();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
