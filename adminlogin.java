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

public class adminlogin extends JFrame implements MouseListener,ItemListener,ActionListener {

	JPasswordField key;
	String passk="sonu";
	JButton jb,next,next1;
	JCheckBox dis;
	JPasswordField pass;Font ff;
	adminlogin()
	{
		
		super("Welcome to Online Voting System:Admin Login");
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
		
		
		JLabel log=new JLabel("Welcome to Admin Portal!!!");
		setLayout(null);
		log.setBounds(320,10,380,300);
		log.setForeground(Color.DARK_GRAY);
		log.setFont(new Font("Serif",Font.BOLD,30));
		add(log);
		
		JLabel log1=new JLabel("Feel Proviledged...");
		setLayout(null);
		log1.setBounds(430,50,180,300);
		log1.setForeground(Color.red);
		log1.setFont(new Font("Serif",Font.BOLD,20));
		add(log1);
		
		JLabel ins1=new JLabel("Enter Passkey:");
		setLayout(null);
		ins1.setBounds(255,305,370,50);
		ins1.setForeground(Color.black);
		ins1.setFont(new Font("Serif",Font.BOLD,20));
		add(ins1);
		
		
		key=new JPasswordField();
		key.setBounds(415,315,310,30);
		key.setBackground(Color.decode("#A0A0A4"));
		add(key);
		
		next=new JButton("Next");
		next.setBounds(450,370,200,35);
	
		next.addActionListener(this);
		add(next);
		
		next1=new JButton("Go Back to User Portal");
		next1.setBounds(330,520,300,45);
		
		next1.addActionListener(this);
		next1.setBackground(Color.green);
		add(next1);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,10,1024,700);
		setVisible(true);
		
		setLayout(null);
		//left side
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
		if(e.getSource()==next1)
		{
			this.setVisible(false);
			this.dispose();
			first n=new first();
			//	n.
			//n.setBackground(Color.black);
				n.setDefaultCloseOperation(EXIT_ON_CLOSE);
				n.setBounds(100,10,1024,700);
				n.setVisible(true);
				
				n.setLayout(null);
		}
		else if(e.getSource()==next)
		{
			
			String pkey=key.getText();
			
			if(pkey.isEmpty())
			{
			JOptionPane.showMessageDialog(this, "PassKey Field is empty!!!");
			return ;
			}
			else
			{
				if(pkey.equals(passk))
				{
			this.setVisible(false);
			this.dispose();
			try {
				new admindash();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "PassKey mismatch or You are not admin!!!");
					return ;
				}
			}
		}
		
	}
	
}
