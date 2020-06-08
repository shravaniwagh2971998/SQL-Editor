import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.sql.*;
class drop extends JFrame implements ActionListener
{
	String s;
	JButton b1,b2,b3;
	JComboBox c1;
	JLabel l1,l2;
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
	ResultSetMetaData meta=null;
	

	public drop()
	{
				setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("2.jpg")));

		
		setLayout(null);
		l1 = new JLabel("Drop Table");
		l1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,40));

		l2 = new JLabel("Table Name : ");
		l2.setFont(new Font("Serif",Font.ITALIC,20));
		c1 = new JComboBox();

		b1 = new JButton("Back");
		b2 = new JButton("Display Query");
		b3 = new JButton("cancel");


		add(l1);
		add(l2);
		add(c1);
		add(b1);
		add(b2);
		add(b3);

		l1.setBounds(350,50,300,40);
		l2.setBounds(300,140,180,30);
		c1.setBounds(500,140,100,30);
		b1.setBounds(200,430,100,30);
		b2.setBounds(310,430,200,30);
		b3.setBounds(520,430,150,30);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setSize(1000,1000);
		setTitle("DROP TABLE");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tablename();

	}
	 void tablename()
	  {
		try
	   	{
			
Class.forName("org.postgresql.Driver");
		
		cn=DriverManager.getConnection("jdbc:postgresql://linux1/tybsc31","tybsc31","");

	  		if(cn==null)
				System.out.println(" no connection");
			else
			{

				st=cn.createStatement();
				DatabaseMetaData dbmt=cn.getMetaData();
				rs = dbmt.getTables(null, null, null,new String[] { "TABLE" });
				while(rs.next())
				{
					c1.addItem(rs.getString("TABLE_NAME"));
				}
			}
		}	catch(Exception e)
		{
			JFrame f=new JFrame();
				System.out.println(e);
				   JOptionPane.showMessageDialog(f,e, "Invalid", JOptionPane.ERROR_MESSAGE);
			
		}
	}//fun
	public void actionPerformed(ActionEvent ae)
	{
		JFrame f = new JFrame();
		if (ae.getSource() == b2)
		{
			try
			{
				
				String name=(String)c1.getSelectedItem();
				String sql;
				sql="DROP TABLE "+name;
				int selection=JOptionPane.showConfirmDialog(f,"you want to drop table","Confirm Query", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(selection == JOptionPane.OK_OPTION)
				{
					int x=st.executeUpdate(sql);
					
					String i=(String)c1.getSelectedItem();
					if(x==0)
					{
						
						c1.removeItem(i);
						JOptionPane.showMessageDialog(f,sql, "Valid", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			}
			catch(Exception e)
				{
				
				System.out.println(e);
				   JOptionPane.showMessageDialog(f,e, "Invalid", JOptionPane.ERROR_MESSAGE);

				}
		}
		if (ae.getSource() == b1)
		{
		  Menu g=new Menu();
			
		}
		if (ae.getSource() == b3)
		{
		 Menu g=new Menu();
			
		}
	}
	public static void main(String args[])
	{
		drop a=new drop();
	}
}
