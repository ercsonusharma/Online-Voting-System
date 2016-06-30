package onlinevs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.swing.*;

class register extends JFrame implements ActionListener{
	JTextField name,dob,card,mail,cont,ssec;
	JButton jb,jb1;
	JPasswordField pass1,pass2;Font ff;
		
	register()
	{
		
			super("Welcome to Online Voting System:Register");
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
			
			
			
			JLabel log=new JLabel("Register");
			setLayout(null);
			log.setBounds(450,10,180,300);
			log.setForeground(Color.BLUE);
			log.setFont(new Font("Serif",Font.BOLD,30));
			add(log);
			
			JLabel log1=new JLabel("Kindly Note that all the Fields are mandatory except Voter-id card number!!!");
			setLayout(null);
			log1.setBounds(250,50,600,300);
			log1.setForeground(Color.red);
			log1.setFont(new Font("Serif",Font.BOLD,18));
			add(log1);
			String st="<html>If you lie under the 2nd criteria mentioned in instructions,then fill Voter id-card number with NA or leave it blank.</html>";
			JLabel log2=new JLabel(st);
			setLayout(null);
			log2.setBounds(150,80,750,300);
			log2.setForeground(Color.red);
			log2.setFont(new Font("Serif",Font.BOLD,16));
			add(log2);
			
			//font for jfields
			ff=new Font("Serif",Font.BOLD,20);
			JLabel nname=new JLabel("Name (In Block Letters)");
			nname.setBounds(200,268,300,25);
			nname.setFont(ff);
			add(nname);
			name=new JTextField();
			name.setBounds(420,268,350,25);
			add(name);
			
			JLabel ddob=new JLabel("DOB (dd/mm/yy)");
			ddob.setBounds(200,310,300,25);
			ddob.setFont(ff);
			add(ddob);
			dob=new JTextField();
			dob.setBounds(420,310,250,25);
			add(dob);
		
			JLabel ccard=new JLabel("Voter Id Number:");
			ccard.setBounds(200,350,300,25);
			ccard.setFont(ff);
			add(ccard);
			card=new JTextField();
			card.setBounds(420,350,250,25);
			add(card);
			

			JLabel mmail=new JLabel("E-Mail:");
			mmail.setBounds(200,390,300,25);
			mmail.setFont(ff);
			add(mmail);
			mail=new JTextField();
			mail.setBounds(420,390,350,25);
			add(mail);
			
			
			JLabel ppas=new JLabel("Password:");
			ppas.setBounds(200,430,300,25);
			ppas.setFont(ff);
			add(ppas);
			pass1=new JPasswordField();
			pass1.setBounds(420,430,250,25);
			add(pass1);
			
			JLabel ppas1=new JLabel("Confirm Password:");
			ppas1.setBounds(200,470,300,25);
			ppas1.setFont(ff);
			add(ppas1);
			pass2=new JPasswordField();
			pass2.setBounds(420,470,250,25);
			add(pass2);
			
			JLabel ccont=new JLabel("Contact Number:");
			ccont.setBounds(200,510,300,25);
			ccont.setFont(ff);
			add(ccont);
			cont=new JTextField();
			cont.setBounds(420,510,250,25);
			add(cont);
			String sq="<html>Your First School Name:<br>(Security Question)</html>";
			JLabel sec=new JLabel(sq);
			sec.setBounds(200,550,300,55);
			sec.setFont(ff);
			add(sec);
			ssec=new JTextField();
			ssec.setBounds(420,550,350,25);
			add(ssec);
			
			jb=new JButton("Enter");
			jb.setBounds(460,610,200,40);
			jb.addActionListener(this);
			add(jb);
			
			jb1=new JButton("Go Back");
			jb1.setBounds(250,610,200,40);
			jb1.addActionListener(this);
			add(jb1);
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(100,10,1024,700);
			setVisible(true);
			setLayout(null);
			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb)
		{
			
			//validate name
			
			int i;boolean empty=true;
			String nname=name.getText(),ddob=dob.getText(),mmail=mail.getText(),con=cont.getText(),passd1=pass1.getText(),passd2=pass2.getText(),
					ccard=card.getText(),sq=ssec.getText();	
				if(!nname.isEmpty())
				{
					for(i=0;i<nname.length();++i)
					{
						if(nname.charAt(i)!=' ')
						{
							empty=false;
							break;
						}
					}
					if(empty||nname.charAt(0)==' ')
					{
						JOptionPane.showMessageDialog(this,"Invalid Name");
						return;
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"Name Field is empty!!!");
					return;
				}	
				
				if(!sq.isEmpty())
				{
					for(i=0;i<sq.length();++i)
					{
						if(sq.charAt(i)!=' ')
						{
							empty=false;
							break;
						}
					}
					if(empty||sq.charAt(0)==' ')
					{
						JOptionPane.showMessageDialog(this,"Invalid School Name");
						return;
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"School Name Field is empty!!!");
					return;
				}	
				//validate dob
				int dd=0,mm=0,yy=0;
					if(!ddob.isEmpty())
					{
						
						if(ddob.length()!=10)
						{
							JOptionPane.showMessageDialog(this,"Invalid Date of Birth");
							return;
						}
						StringTokenizer st=new StringTokenizer(ddob,"/");
						while(st.hasMoreTokens())
						{
							if(st.countTokens()==3)
							{
								dd=Integer.parseInt(st.nextToken());
							}
							else if(st.countTokens()==2)
							{
								mm=Integer.parseInt(st.nextToken());
							}
							else if(st.countTokens()==1)
							{
								yy=Integer.parseInt(st.nextToken());
							}
						}
						if(dd>31||mm>12||yy>2015)
						{
							JOptionPane.showMessageDialog(this,"Invalid Date of Birth");
							return;
						}
						if(2014-yy<18)
						{
							JOptionPane.showMessageDialog(this,"You are under 18.Age should be >=18 as on 01/01/2015");
							return;
						}	
						
					}
					else 
					{
						JOptionPane.showMessageDialog(this,"DOB Field is empty!!!");
						return;
					}	
					//validate email
					if(!mmail.isEmpty())
					{
						for(i=0;i<mmail.length();++i)
						{
							if(mmail.charAt(i)=='@')
							{
								empty=false;
								break;
							}
							
						}
						if(empty||mmail.charAt(0)==' ')
						{
							JOptionPane.showMessageDialog(this,"Invalid E-Mail");
							return;
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(this,"E-Mail Field is empty!!!");
						return;
					}	
					if(!passd1.isEmpty()&&(!passd2.isEmpty()))
					{
						if(passd1.equals(passd2))
						{
							if(passd1.length()<8)
							{
							
									JOptionPane.showMessageDialog(this,"Password must be of atleast 8 characters");
									return;
								
							}
						for(i=0;i<passd1.length();++i)
						{
							if(passd1.charAt(i)!=' ')
							{
								empty=false;
								break;
							}
						}
						if(empty||passd1.charAt(0)==' ')
						{
							JOptionPane.showMessageDialog(this,"Invalid Password");
							return;
						}
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Password donot match");
							return;
						}
					}
					
					else 
					{
						JOptionPane.showMessageDialog(this,"Password Field is empty!!!");
						return;
					}
					if(!con.isEmpty())
					{
						if(con.length()<10)
						{
							JOptionPane.showMessageDialog(this,"Invalid Contact No.!!!");
							return;
						}
							
					}
					else 
					{
						JOptionPane.showMessageDialog(this,"Contact Field is empty!!!");
						return;
					}
					try {
						doreg ob=new doreg(ccard,nname,mm,dd,yy,mmail,passd1,con,sq);
						if(ob.don())
						{
							this.setVisible(false);
							this.dispose();
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
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			
		}
		else if(e.getSource()==jb1)
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
	}
}
