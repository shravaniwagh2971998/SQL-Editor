
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.sql.*;
import javax.swing.table.*;
class delete extends JFrame implements ActionListener
{
	String s;
	JButton b1,b2,b3;
	JTextField t1;
	JComboBox c1,c2,c3,c4;
	JLabel l1,l2,l3,l4;
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
	ResultSetMetaData meta=null;
	
	public delete()
	{
				setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("2.jpg")));

		
		setLayout(null);
		l1 = new JLabel("Delete Record");
		l1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,40));

		l2 = new JLabel("Table Name : ");
		l2.setFont(new Font("Serif",Font.ITALIC,20));
		c1 = new JComboBox();
		
		l3 = new JLabel("Attribute Name :");
		l3.setFont(new Font("Serif",Font.ITALIC,20));
		c2 = new JComboBox();
			
		c3 = new JComboBox();
			c3.addItem("=");
			c3.addItem("<");
			c3.addItem("<=");
			c3.addItem(">");
			c3.addItem(">=");
			c3.addItem("==");
		l4 = new JLabel("Value :");
		l4.setFont(new Font("Serif",Font.ITALIC,20));
		t1 = new JTextField(15);
		c4 = new JComboBox();
			c4.addItem("NULL");
			c4.addItem("NOT NULL");

		b1 = new JButton("Back");
		b2 = new JButton("Display Query");
		b3 = new JButton("add");
		
		add(l1);		
		add(l2);
		add(c1);
		add(l3);
		add(c2);
		add(c3);
		add(l4);
		add(t1);
		add(b1);
		add(b2);
	
		l1.setBounds(350,50,400,40);
		l2.setBounds(300,160,180,30);
		c1.setBounds(510,160,100,30);
		l3.setBounds(300,220,200,30);
		c2.setBounds(510,220,100,30);
		c3.setBounds(620,220,100,30);
		l4.setBounds(300,280,100,30);
		t1.setBounds(510,280,80,30);
		c4.setBounds(510,280,80,30);
		b1.setBounds(300,400,80,30);
		b2.setBounds(390,400,150,30);
		b3.setBounds(550,400,80,30);
	
		b1.addActionListener(this);	
		b2.addActionListener(this);
		c1.addActionListener(this);
		setSize(1000,1000);
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
		System.out.println(e);
		}
	}//fun	
	
void attributename()
		{
			try
			{
					String name=(String)c1.getSelectedItem();
					String sql="select * from "+name;
				
					Statement s=cn.createStatement();	
					ResultSet rs=s.executeQuery(sql);
					ResultSetMetaData rsmd=rs.getMetaData();

					
					int n=rsmd.getColumnCount();
					System.out.println("count:"+n);
						
					for(int i=1;i<=n;i++)
					{
						
						c2.addItem(rsmd.getColumnName(i));
					}

			}
			catch(Exception e)
			{
			}
} 
	public void actionPerformed(ActionEvent ae)
	{
		JFrame f = new JFrame();		
		
		
			if (ae.getSource() == c1)
			{
					try
					{

c2.removeAllItems();

						attributename();
				 	}
				 	catch(Exception e)
					{
					}	
			}	
			
			if (ae.getSource() == b2)
			{
				
					try
					{
						String name=(String)c1.getSelectedItem();
						String attributename=(String)c2.getSelectedItem();	
						String val=t1.getText();
						String opname=(String)c3.getSelectedItem();
						String sql;
						sql="Delete from "+name+" where "+attributename+opname+"'"+val+"'";
						int selection=JOptionPane.showConfirmDialog(f, "Delete from  "+name+" where "+attributename+opname+"'"+val+"';","Confirm Query", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);	
						 if(selection == JOptionPane.OK_OPTION)
						{
							int x=st.executeUpdate(sql);
						 
						   if(x==1)
			 	              {
					           JOptionPane.showMessageDialog(f, "Record is deleted successfully", "Valid", JOptionPane.INFORMATION_MESSAGE);	
						     tablename();
				               }
				 	        else
					       {
						 JOptionPane.showMessageDialog(f, "Record from "+name+" is not deleted", "Valid", JOptionPane.ERROR_MESSAGE);	
						
					       }
						}					
				 	}
				 	catch(Exception e)
					{
					}	
			}	
				
		if (ae.getSource() == b1)
		{
		  Menu s=new Menu();
			
		}
	}	

	

	public static void main(String args[])
	{
		delete y=new delete();
	}
}
