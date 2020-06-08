import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import java.util.*;
import java.io.*;
import java.sql.*;
class Newreg implements ActionListener
{
	JFrame jf;
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	JTextField jt1,jt2,jt3,jt4;
	JPasswordField jp1,jp2;
	JButton jb;
	int res;
		
	Newreg()
	{
		jf=new JFrame("registration form");
		jf.setBounds(300,300,400,400);
		
		jl1 = new JLabel("Registration Form");
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(jl1);
		
		jl2 = new JLabel("Name :- ");
		jt1 = new JTextField(20);
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(jl2);
		p2.add(jt1);

		jl3=new JLabel("Login :- ");
		jt2=new JTextField(20);
		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(jl3);
		p3.add(jt2);
		
		jl4 = new JLabel("Password :- ");
		jp1 = new JPasswordField(20);
		p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		p4.add(jl4);
		p4.add(jp1);
		
		jl5 = new JLabel("Confirm Password :- ");
		jp2 = new JPasswordField(20);
		p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		p5.add(jl5);
		p5.add(jp2);

		
		jl6 = new JLabel("Captcha :- ");
		jt4 = new JTextField(5);
		Random r=new Random();
		int a=r.nextInt(100)+10;
		int b=r.nextInt(100)+10;
		res=a+b;
		jt4.setText(a+"+"+b);
		
		jt3 = new JTextField(20);
		p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		p6.add(jl6);
		p6.add(jt4);
		p6.add(jt3);
		
		jb = new JButton("Submit");
		
		p7 = new JPanel();
				p7.setLayout(new FlowLayout());
			p7.add(jb);
		
		
		jf.setLayout(new GridLayout(7,1));
		jf.add(p1);
		jf.add(p2);
		jf.add(p3);
		jf.add(p4);
		jf.add(p5);
		jf.add(p6);
		jf.add(p7);
		p7.add(jb);
		

		jf.setLayout(new GridLayout(7,1));
		jf.add(p1);
		jf.add(p2);
		jf.add(p3);
		jf.add(p4);
		jf.add(p5);
		jf.add(p6);
		jf.add(p7);
				
		jf.setVisible(true);
		jb.addActionListener(this);
		

	}
	public void actionPerformed(ActionEvent ae)
	{
		
		String p1=jp1.getText();
		String p2=jp2.getText();
		String t1=jt1.getText();
		String t2=jt2.getText();
		String c1=jt3.getText();
		String c2=jt4.getText();
		JFrame jf1=new JFrame("Registration Form");
		jf1.setBounds(300,300,400,400);
		if(p1.equals("") || p2.equals("")  || t1.equals("")  || t2.equals("")  || c1.equals("") || c2.equals("") )
		{
			JOptionPane.showMessageDialog(null,"All fields are mandatory","Message",JOptionPane.PLAIN_MESSAGE);
                        jt1.setText("");
			jt2.setText("");
			jt3.setText("");
			jp2.setText("");
			jp1.setText("");                         		
		}
		
		else
		{
		
			if(p1.length()<6)
			{
				JOptionPane.showMessageDialog(null,"Password length should be greater than 6 characters...","Message",JOptionPane.PLAIN_MESSAGE);
				jp1.setText("");
				jp2.setText("");
			}
			boolean flag1=false,flag2=false,flag3=false;
			String str=p1;
			for(int i=0;i<str.length();i++)
			{
					if(flag1==false && (str.charAt(i)>=65 && str.charAt(i)<=91))
					flag1=true;
					if(flag2==false &&(str.charAt(i)>=48 && str.charAt(i)<=57))
					flag2=true;
					if(flag3==false &&(str.charAt(i)>=33 && str.charAt(i)<=47))
					flag3=true;			
			}
			if(flag1==false && flag2==false && flag3==false)	
			{
				JOptionPane.showMessageDialog(null,"Password must contain one capital letter and one digit and one special symbol...","Message",JOptionPane.PLAIN_MESSAGE);
				jp1.setText("");
				jp2.setText("");
			}
			else
			{
				if(!(p1.equals(p2)))
				{
					JOptionPane.showMessageDialog(null,"Password and confirm password must be same...","Message",JOptionPane.PLAIN_MESSAGE);
					jp1.setText("");
					jp2.setText("");
				}
				else
				{
					int c=Integer.parseInt(jt3.getText());
					if(c!=res)
					{
						JOptionPane.showMessageDialog(null,"Capicha result is wrong...","Message",JOptionPane.PLAIN_MESSAGE);
						jt3.setText("");
					}
					else
					{
try{

			Connection con;
		

	String username,psw;		

		
		Class.forName("org.postgresql.Driver");
		
		con=DriverManager.getConnection("jdbc:postgresql://linux1/tybsc31","tybsc31","");
		JOptionPane.showMessageDialog(null,"driver register...");
		
		JOptionPane.showMessageDialog(null,"connection successfully!");
		
			Object src=ae.getSource();
		Statement stmt=con.createStatement();
		ResultSet rs;
		PreparedStatement ps1=con.prepareStatement("insert into newuser values(?,?)");
		
		if(src.equals(jb))
		{
			username=jt2.getText();
					psw=jp1.getText();				
						ps1.setString(1,username);
		
			ps1.setString(2,psw);
						ps1.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record Inserted");
		
		}


				}
			
			catch(Exception e)
			{
				System.out.println(e);
			}					
				

	JOptionPane.showMessageDialog(null,"Registration successful...","Message",JOptionPane.PLAIN_MESSAGE);
		
		}
	}

	}
}
		


	}
		
		
	public static void main(String args[]) 
	{
			Newreg h=new Newreg();
		
	}
}
