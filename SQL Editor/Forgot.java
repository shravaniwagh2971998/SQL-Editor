import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class Forgot extends JFrame implements ActionListener
{
JLabel lblun,lblpsw,lblpsw1;
	JTextField txtun;
	JPasswordField txtpsw,txtpsw1;
	JButton jlogin,jcancel;
	Font f;
	
	Forgot()
	{
		setLayout(null);
		setTitle("Fogotpassword Form ");
 
		JLabel image=new JLabel();
                		image.setIcon(new ImageIcon("2.jpg"));
                		image.setBounds(0,-120,1000,1000);
		
		lblun=new JLabel("Username");
		lblpsw=new JLabel("new Password");	
			lblpsw1=new JLabel("confirmPassword");

		lblun.setForeground(Color.BLACK);
		lblpsw.setForeground(Color.BLACK);
		lblpsw1.setForeground(Color.BLACK);

		txtun=new JTextField(60);
		txtpsw=new JPasswordField(60);
		txtpsw1=new JPasswordField(60);
		

		
		jlogin=new JButton("ok");
		jcancel=new JButton("Cancel");
		
		jlogin.setToolTipText("Click here to ok");
		jcancel.setToolTipText("Click here to exit");
		
		
		add(lblun);
		add(lblpsw);
		add(lblpsw1);
		add(txtun);
		add(txtpsw);
		add(txtpsw1);
		add(jlogin);
		add(jcancel);
		
		add(image);	
	jlogin.addActionListener(this);
		jcancel.addActionListener(this);

		f=new Font("Arial",Font.BOLD,18);		
		
		lblun.setFont(f);
		lblpsw.setFont(f);
			lblpsw1.setFont(f);
		


		
		lblun.setBounds(300,200,200,30);
		lblpsw.setBounds(300,250,200,30);	
		lblpsw1.setBounds(300,300,280,30);

		txtun.setBounds(500,200,200,30);
		txtpsw.setBounds(500,250,200,30);
		txtpsw1.setBounds(500,300,200,30);

		jlogin.setBounds(380,350,100,30);
		jcancel.setBounds(520,350,100,30);

		setDefaultCloseOperation(2);
		setSize(1022,1010);
		setResizable(false);
		setLocation(0,0);
		setVisible(true);
	}//con
	
	public void actionPerformed(ActionEvent e)
    	{
			if(jcancel==e.getSource())	
			{
				Loginpage r=new Loginpage();
			}
			
    		    		if(jlogin==e.getSource())
				{
					
					if(txtun.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null," Enter userName");
					}
					else if(txtpsw.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null," Enter Password");
					}
					else if(txtpsw1.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null," Enter confirm Password");
					}
					else 
					{
						try{

			Connection con;
		

	String username,psw;		

		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection("jdbc:postgresql://linux1/tybsc31","tybsc31","");
			Statement stmt=con.createStatement();
		
	

        String p3 = txtpsw.getText();
        String p4 = txtpsw1.getText();	

     ResultSet   rs=stmt.executeQuery("select * from newuser where username ='"+txtun.getText()+"' ");

        while(rs.next())
        {
                if(p3.equals(p4))
                {
           PreparedStatement  ps=con.prepareStatement("Update newuser SET psw = ? where username =? ");
                    ps.setString(1,p4);
ps.setString(2,txtun.getText());
                    ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Password Changed");

                }
                else
                    {
            JOptionPane.showMessageDialog(null,"Please check your Password n type Again");
            txtpsw.setText("");
            txtpsw1.setText("");
            txtun.setText("");
                    }
	}
}
            catch(Exception pe)
            {JOptionPane.showMessageDialog(null,pe);

               }
}
	

	}					

}
	public static void main(String args[])
	{

		Forgot p11=new Forgot();
		
	}
}
