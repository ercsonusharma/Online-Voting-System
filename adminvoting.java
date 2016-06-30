package onlinevs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.border.AbstractBorder;

public class adminvoting extends JFrame implements ActionListener,ItemListener{

	//private static final String JRadioButton = null;
	JButton prof,vot,update,exi,home,select,sub,sub1;
	JLabel ccont;int i=1;
	int opt=-1;
	int idx=0;
	JRadioButton jb[]=new JRadioButton[10];
	JComboBox cb;
	int cid[]=new int[10];
	Connection con=null;
	Vector<String> que=new Vector();
	Vector<String> expire=new Vector();
	Statement st=null,stt=null;
	ResultSet rs=null,rst=null;
	Vector<Integer> qid=new Vector();
	static int uid;

	adminvoting() throws Exception {
		
		super("Welcome to Online Voting System:Admin Portal:Manage voting Events");
		//System.out.println(uid);
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
		connection();
		String mes="<html>&nbsp;<span style=\"font-size:18px\">Welcome,</span><br><p>&nbsp;&nbsp;&nbsp;&nbsp;Admin</p></html>";
		
		JLabel wel=new JLabel(mes);
	
		wel.setBounds(50,200,200,75);
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
		
		
		
		String pr="<html><u><span style=\"color:#2A0000\">Manage Voting Zone</u></html>";
		JLabel log1=new JLabel(pr);
		log1.setBounds(450,180,380,30);
		log1.setForeground(Color.DARK_GRAY);
		log1.setFont(new Font("Elephant",Font.BOLD,22));
		add(log1);

		JLabel log2=new JLabel("Present Voting Events:-");
		log2.setBounds(300,215,380,30);
		log2.setForeground(Color.DARK_GRAY);
		log2.setFont(new Font("Arial",Font.BOLD,20));
		add(log2);
		
			int l=0;
				rs=st.executeQuery("Select count(*) from voting");
		if(rs.first())
		{
			l=rs.getInt(1);
			
		}
	
		rs=st.executeQuery("Select id,quest,live,expire from voting");
		
		
		
		que.add("-----------------Select Event from drop-down list--------------------");
		
		
		int i=1,qqid=0;
		
		while(rs.next())
		{
			
			qqid=rs.getInt(1);
			///System.out.println("qqid="+qqid+rs.getRow());
			
			
				//System.out.println("added");
			que.addElement(rs.getString(2));
			qid.add(qqid);
			
			
			
			
		//	System.out.println(qid[i]+que[i]);
			
		}
		cb=new JComboBox(que);
		cb.setBounds(300, 260, 500, 40);
		
		add(cb);
		
		select=new JButton("Select");
		select.setBounds(350,310,130,35);
		select.addActionListener(this);
		add(select);
		
		sub=new JButton("Extend Expiry");
		sub.setBounds(500,310,140,35);
		sub.addActionListener(this);
		add(sub);
		
		sub.setEnabled(false);
		sub1=new JButton("Delete");
		sub1.setBounds(660,310,130,35);
		sub1.addActionListener(this);
		add(sub1);
		sub1.setEnabled(false);
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
			stt=con.createStatement();
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
	
		
		ButtonGroup bg=new ButtonGroup();
		if(e.getSource()!=exi&&e.getSource()!=select&&e.getSource()!=sub&&e.getSource()!=sub1)
		{
		this.setVisible(false);
		this.dispose();
		}
		
		
		if(e.getSource()==select)
		{
			i=1;
			if(cb.getSelectedIndex()!=0)
			{
			
			int ch=JOptionPane.showConfirmDialog(this, "You have opted to choose for"+que.elementAt(cb.getSelectedIndex()),"Are you Sure?",JOptionPane.YES_NO_OPTION);
			if(ch==JOptionPane.YES_OPTION)
			{
			select.setEnabled(false);
			sub.setEnabled(true);
			sub1.setEnabled(true);
			if(qid.size()>=cb.getSelectedIndex())
		idx=qid.elementAt(cb.getSelectedIndex()-1);
			//System.out.println(cb.getSelectedIndex()+qid.elementAt(cb.getSelectedIndex()-1));
		int y=350;
		//JRadioButton jb[]=new JRadioButton[5];
		if(idx>0)
		{
		try {
			
			
			
			
			String lv="Live on: ";
			String ex="Expires on: ";
			rs=st.executeQuery("Select live,expire from voting where id="+idx);
			if(rs.next())
			{
				lv=lv+rs.getString(1);
				ex=ex+rs.getString(2);
				
			}
			String not1="<html><u>Status</u></html>";
JLabel stt1=new JLabel(not1);
			
			stt1.setBounds(470,354,100,20);
			stt1.setFont(new Font("Arial",Font.BOLD,19));
			add(stt1);
			
			String not="<html><span style=\"font-size:11px\">"+lv+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ex+" </span></html>";
			JLabel stt=new JLabel(not);
			
			stt.setBounds(350,365,700,40);
			stt.setFont(new Font("Arial",Font.ITALIC,12));
			add(stt);
			
			rs=st.executeQuery("select cand,total from link where qid="+idx);
			
			while(rs.next())
			{
			
					ccont=new JLabel(rs.getString(1));
					ccont.setBounds(338,y+i*40,200,35);
					ccont.setFont(new Font("Serif",Font.ITALIC,20));
					add(ccont); 
					
					JLabel ccont1=new JLabel(""+rs.getInt(2)+"");
					ccont1.setBounds(638,y+i*40,50,35);
					ccont1.setFont(new Font("Serif",Font.ITALIC,18));
					add(ccont1); 
					i++;
				}
				
				
				
			//	System.out.println("cid="+cid[i-1]+"i="+i);
				
			
			//for(int j=0;j<i;j++)
				//bg.add(jb[j]);
			//System.out.println("i="+i);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Invalid Selection!!!");
			return ;
		}
		
		//revalidate();
		repaint();
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Select an Option First,if it is present in List!!!");
				return ;
			}
		}
		
		if(e.getSource()==sub)	
		{
			if(idx>0)
			{
				String nw=JOptionPane.showInputDialog(this,"Enter the new Exipry/Deadline:yyyy-mm-dd hh:mm:ss","Setting up new Expiry",JOptionPane.OK_CANCEL_OPTION);
				if(nw.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Empty field");
					return;
				}
				else
				{
					try {
						int x=st.executeUpdate("Update voting set expire=\""+nw+"\" where id="+idx);
						if(x>0)
						{
							JOptionPane.showMessageDialog(this, "Deadline extended for the selected event!!!");
							this.dispose();
							new adminvoting();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		}
		if(e.getSource()==sub1)	
		{
			if(idx>0)
			{
//System.out.println(idx);
				int ch=JOptionPane.showConfirmDialog(this, "You have opted to delete event:"+que.elementAt(cb.getSelectedIndex()),"Are you Sure?",JOptionPane.YES_NO_OPTION);
				if(ch==JOptionPane.YES_OPTION)
				{
				
				try{
					connection();
				int x=st.executeUpdate("Delete from voting where id="+idx);
				
				if(x>0)
				{
					
						int y=st.executeUpdate("Delete from link where qid="+idx);
						if(y>0)
						{
							JOptionPane.showMessageDialog(this, "Event removed from the Panel!!!");
							this.dispose();
							new adminvoting();
						}
					
				}
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
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
		if(e.getSource()==exi)
		{
			
		
			int reply=JOptionPane.showConfirmDialog(null,"Do you want to realy Exit?", "Are You Sure?",JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
