import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

class Insert extends JFrame implements ActionListener
{
	DefaultTableModel model = new DefaultTableModel();

	JPanel p,p1,p2,p3;
	JButton b1,b2;
	JComboBox c1;
	JLabel l1,l2;
	int columnCount ;
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
	ResultSetMetaData meta=null;
	JTable table;
	Insert()
	{
				setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("2.jpg")));

		setLayout(null);
		l1 = new JLabel("Insert Records");
		l1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,40));
		
		l2 = new JLabel("Table Name : ");
		l2.setFont(new Font("Serif",Font.ITALIC,20));
		c1 = new JComboBox();

		String[][] data =new String[25][2];
		String[] colHeads = {"Attribute Name","Value"};		
		 table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		model.addColumn("Attribute Name");
		model.addColumn("Values");
		    		
		b1 = new JButton("Display Query");
		b2 = new JButton("Back");
		
		add(l1);
		add(l2);
		add(c1);
		add(jsp);
		add(b1);
		add(b2);

		l1.setBounds(330,50,350,50);
		l2.setBounds(350,140,180,30);
		c1.setBounds(535,140,100,30);
		jsp.setBounds(200,200,600,300);
		b1.setBounds(350,530,200,30);
		b2.setBounds(560,530,100,30);
		c1.addActionListener(this);
		b1.addActionListener(this);	
		b2.addActionListener(this);
		setSize(1000,1000);
		setTitle("INSERT");
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
		if(ae.getSource()==c1)
		{
			model.setRowCount(0);
			
			try
			{
				String s2=(String)c1.getSelectedItem();
			
				st=cn.createStatement();
				String s="SELECT * FROM "+s2;

				ResultSet rs = st.executeQuery(s);
				ResultSetMetaData rsmd = rs.getMetaData();
				columnCount = rsmd.getColumnCount();
				int j=0;
				for (int i = 1; i <=columnCount;i++) 
				{
					String name = rsmd.getColumnName(i);
			model.insertRow(j++, new Object[]{name}); 
			
				} 
				model.insertRow(j++, new Object[]{"...","..."});  
			}
			catch(Exception e)
			{
				JFrame f=new JFrame();
				System.out.println(e);
				JOptionPane.showMessageDialog(f,e, "Invalid", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if (ae.getSource() == b2)
		{
			Menu y=new Menu();
		
		}
		if (ae.getSource() == b1)
		{
			int n= model.getRowCount();
			String sql="",sql1="";
			String r="",r1="" ;
			String ins="";
			String s1[]=new String[n+1];
			String s2[]=new String[n+1];
			String s22="";
			int count,j=0,i;
			System.out.println(n);
			int k=0;

			try
			{
				JFrame f=new JFrame();
				s22=(String)c1.getSelectedItem();
				st=cn.createStatement();
				String s="SELECT * FROM "+s22;
				ResultSet rs = st.executeQuery(s);
				ResultSetMetaData rsmd = rs.getMetaData();
				columnCount = rsmd.getColumnCount();
				for (i = 1; i <= columnCount;i++) 
				{
					s1[k] = rsmd.getColumnName(i);        
					k++;
				}
				for (i = 0; i <n;i++)  
				{
					
			s2[j++] = (String) model.getValueAt(i,1);
					System.out.println(s2[j-1]);
					        
				}
				
				for (i=0;i<columnCount;i++)
				{
					sql+=s1[i]+",";
					sql1+="'"+s2[i]+"'"+",";
				}
				StringBuilder sb;
				n=sql.length();	
				sb = new StringBuilder(sql);
				sb.deleteCharAt(n-1);
				r = sb.toString();
				n=sql1.length();	
				sb = new StringBuilder(sql1);
				sb.deleteCharAt(n-1);
				r1 = sb.toString();
				ins+="insert into "+s22+"("+r+") values"+"("+r1+");";
				System.out.println(ins);
				st=cn.createStatement();
				j=st.executeUpdate(ins);
				if(j>0)
				{
					JOptionPane.showMessageDialog(f,ins, "Valid", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else
				System.out.println("insert another data primary key");
			}
			catch(Exception e)
			{
				JFrame f=new JFrame();
				System.out.println(e);
				   JOptionPane.showMessageDialog(f,e, "Invalid", JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}
	public static void main(String args[])
	{
		Insert i=new Insert();
	}
}
