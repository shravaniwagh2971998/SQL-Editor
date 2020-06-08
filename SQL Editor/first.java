import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class first extends JFrame implements ActionListener
{
	JLabel lblun,lblpsw;
	JTextField txtun;
	JPasswordField txtpsw;
	JButton jok,jcancel,jnew,jforget;
	Font f;
	
	first()
	{
		setLayout(null);
		setTitle("sql editor ");
 
		JLabel image=new JLabel();
                		image.setIcon(new ImageIcon("k.jpg"));
                		image.setBounds(0,-120,1000,1000);
		
		lblun=new JLabel("SQl Editor");
		
		
		
		jok=new JButton("GO");
		
		jok.setToolTipText("Click here to go");
		
		add(lblun);
		add(jok);
		add(image);	
		jok.addActionListener(this);
f=new Font("Arial",Font.BOLD,40);		
		
		lblun.setFont(f);
		
		lblun.setBounds(350,200,600,50);
		
		jok.setBounds(380,320,100,30);
		
		setDefaultCloseOperation(2);
		setSize(1022,1010);
		setResizable(false);
		setLocation(0,0);
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e)
    	{
    			
			
			if(jok==e.getSource())	
			{
				Loginpage ii=new Loginpage();
			}
			
			
	}
	
	
	
	public static void main(String args[])
	{

		first p111=new first();
		
	}
}

