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

public class voting extends JFrame implements ActionListener,ItemListener{

	//private static final String JRadioButton = null;
	JButton prof,vot,update,exi,home,select,sub;
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
	voting(){}
	voting(int uid) throws Exception {
		
		super("Welcome to Online Voting System:Voting Zone");
		//System.out.println(uid);
		setResizable(false);
		
	
		
		this.uid=uid;
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
		
		
		String pr="<html><u><span style=\"color:#2A0000\">Voting Zone</u></html>";
		JLabel log1=new JLabel(pr);
		log1.setBounds(500,180,380,30);
		log1.setForeground(Color.DARK_GRAY);
		log1.setFont(new Font("Elephant",Font.BOLD,22));
		add(log1);

		JLabel log2=new JLabel("Ongoing Voting Events:-");
		log2.setBounds(300,215,380,30);
		log2.setForeground(Color.DARK_GRAY);
		log2.setFont(new Font("Arial",Font.BOLD,20));
		add(log2);
		
		int utype;
		if(getdetails("cardno").equals("NA"))
			utype=0;
		else
			utype=1;
		int l=0;
			if(utype==0)
		rs=st.executeQuery("Select count(*) from voting where type="+utype);
			else
				rs=st.executeQuery("Select count(*) from voting");
		if(rs.first())
		{
			l=rs.getInt(1);
			
		}
		if(utype==0)
		rs=st.executeQuery("Select id,quest,live,expire from voting where type="+utype);
		else
			rs=st.executeQuery("Select id,quest,live,expire from voting");
		
		
		
		que.add("-----------------Select Event from drop-down list--------------------");
		
		
		int i=1,qqid=0;
		
		while(rs.next())
		{
			
			qqid=rs.getInt(1);
			///System.out.println("qqid="+qqid+rs.getRow());
			
			rst=stt.executeQuery("Select id from voted where qid="+qqid+" and uid="+uid);
			if(rst.next()==false)
			{
			String lv=rs.getString(3),exp=rs.getString(4);
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date lvt=sdf.parse(lv),expt=sdf.parse(exp);
		//	System.out.println(lvt+" "+exp+"for"+rs.getRow());
			if(new java.util.Date().getTime()>lvt.getTime()&&new java.util.Date().getTime()<expt.getTime())
			{
				//System.out.println("added");
			que.addElement(rs.getString(2));
			qid.add(qqid);
			expire.addElement(exp);
			}
			}
			
			
		//	System.out.println(qid[i]+que[i]);
			
		}
		cb=new JComboBox(que);
		cb.setBounds(300, 260, 500, 40);
		
		add(cb);
		
		select=new JButton("Select");
		select.setBounds(420,310,150,35);
		select.addActionListener(this);
		add(select);
		
		sub=new JButton("Vote Now");
		sub.addActionListener(this);
		add(sub);
		
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
		if(e.getSource()!=exi&&e.getSource()!=select&&e.getSource()!=sub)
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
			
			if(qid.size()>=cb.getSelectedIndex())
		idx=qid.elementAt(cb.getSelectedIndex()-1);
			//System.out.println(cb.getSelectedIndex()+qid.elementAt(cb.getSelectedIndex()-1));
		int y=390;
		//JRadioButton jb[]=new JRadioButton[5];
		if(idx>0)
		{
		try {
			rs=st.executeQuery("select link.id,cand,voting.quest from link left join voting on qid=voting.id  where qid="+idx);
			while(rs.next())
			{
				if(rs.getRow()==1)
				{
					ccont=new JLabel("->"+rs.getString(3));
					ccont.setBounds(338,380,500,35);
					ccont.setFont(new Font("Serif",Font.ITALIC,20));
					add(ccont); 
					
					JLabel ccont1=new JLabel("Expires on:"+expire.elementAt(cb.getSelectedIndex()-1));
					ccont1.setBounds(638,350,500,35);
					ccont1.setFont(new Font("Serif",Font.ITALIC,18));
					add(ccont1); 
				}
				jb[i-1]=new JRadioButton(rs.getString(2));
				y+=30;
				jb[i-1].setBounds(338,y,500,35);
				jb[i-1].addItemListener(this);
				jb[i-1].setSelected(false);
				bg.add(jb[i-1]);
				add(jb[i-1]);
				cid[i-1]=rs.getInt(1);
				
				
			//	System.out.println("cid="+cid[i-1]+"i="+i);
				i++;	
			}
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
		
		sub.setBounds(420,y+50,150,35);
		revalidate();
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
			System.out.println(opt);
			System.out.println(cid[opt]);
			int tot=0;
			try {
				rs=st.executeQuery("Select total from link where id="+cid[opt]);
				if(rs.first())
					tot=rs.getInt(1);
				tot++;
			
				int x=st.executeUpdate("Update link set total="+tot+" where id="+cid[opt]);
				if(x>0)
				{
					int xx=st.executeUpdate("Insert into voted set qid="+idx+", uid="+uid);
					if(xx>0)
					{
						JOptionPane.showMessageDialog(this,"Congrats!!!You have successfully voted!!!");
						this.setVisible(false);
						this.dispose();
						new voting(uid);
						
					}
				}
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		for(int j=0;j<i-1;j++)
		{
			if(jb[j].isSelected())
			{
				opt=j;
			}
		}
	}
}
