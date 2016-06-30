package onlinevs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;

public class first extends JFrame implements MouseListener,ItemListener,ActionListener {

	JTextField name,dob,card;
	JButton jb,next,next1;
	JCheckBox dis;
	JPasswordField pass;Font ff;
	first()
	{
		
		super("Welcome to Online Voting System");
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
		
		
		JLabel log=new JLabel("Welcome!!!");
		setLayout(null);
		log.setBounds(450,10,180,300);
		log.setForeground(Color.DARK_GRAY);
		log.setFont(new Font("Serif",Font.BOLD,30));
		add(log);
		
		JLabel log1=new JLabel("Instructions:-");
		setLayout(null);
		log1.setBounds(130,50,180,300);
		log1.setForeground(Color.red);
		log1.setFont(new Font("Serif",Font.BOLD,20));
		add(log1);
		
		String ins="<html><p>1).Only such canditates are allowed to vote for an election who have a Voter - Id Card<br>&nbsp;&nbsp;&nbsp;&nbsp; issued by Govt. of India."
				+ "<br>2).However, those canditates who doesnot satisfy the above criteria but having an age <br>&nbsp;&nbsp;&nbsp;&nbsp;above 18 years can vote for the survey conducted by"
				+ " admin<br>3).You must have an account registered to access the voting system.If so,then Click the<br>&nbsp;&nbsp;&nbsp;&nbsp; Next button below to login."
				+ "<br>4). If you don't have an account registered then Click on New User Registration link on  <br>&nbsp;&nbsp;&nbsp;&nbsp; Next Window."
				+ "<br>5). If you have forgotten your password, the you can recover it from Next Window by <br>&nbsp;&nbsp;&nbsp;&nbsp; Clicking on Forgot Password link.</p></html>";
		JLabel ins1=new JLabel(ins);
		setLayout(null);
		ins1.setBounds(135,205,970,300);
		ins1.setForeground(Color.black);
		ins1.setFont(new Font("Serif",Font.BOLD,20));
		add(ins1);
		
		String disc="I have read the above instructions carefully and also meet the above Eligibility Criteria.";
		dis=new JCheckBox(disc);
		dis.setOpaque(false);
		dis.setBounds(135, 510, 970, 25);
		dis.addItemListener(this);
		add(dis);
		
		next=new JButton("Next");
		next.setBounds(700,550,200,35);
		next.setEnabled(false);
		next.addActionListener(this);
		add(next);
		
		next1=new JButton("Admin Portal");
		next1.setBounds(390,600,200,45);
		
		next1.addActionListener(this);
		next1.setBackground(Color.green);
		add(next1);
		
		//left side
	}
		
	
	public static void main(String...k)
	{
		first n=new first();
	//	n.
	//n.setBackground(Color.black);
		n.setDefaultCloseOperation(EXIT_ON_CLOSE);
		n.setBounds(100,10,1024,700);
		n.setVisible(true);
		
		n.setLayout(null);
		
	}

	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.setEnabled(false);
		this.setVisible(false);
		this.dispose();
		new register();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setCursor(Cursor.HAND_CURSOR);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		this.setCursor(Cursor.DEFAULT_CURSOR);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if(dis.isSelected())
		{
			next.setEnabled(true);
		}
		else
			next.setEnabled(false);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next)
		{
			this.setVisible(false);
			this.dispose();
			login n=new login();
		
				n.setDefaultCloseOperation(EXIT_ON_CLOSE);
				n.setBounds(100,10,1024,700);
				n.setVisible(true);
				n.setLayout(null);
				n.choice();
				n.password();
				n.newuser();
		}
		else if(e.getSource()==next1)
		{
			this.setVisible(false);
			this.dispose();
			new adminlogin();
		}
		
	}
	
}
