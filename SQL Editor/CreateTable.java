import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class CreateTable extends JFrame implements ActionListener,ItemListener
{
int r=0;
String tb;
String tablename1;
	String sql="",sql1="";
	
JRadioButton r1;
	JButton b1,b2,b3;
	JTextField t1,t2,t3;
	JComboBox c1,c2,c3,c4;
	JLabel l,l1,l2,l3,l4,l5,l6;
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
	ResultSetMetaData rsmd=null;
	
	ResultSetMetaData meta=null;
  DefaultTableModel model = new DefaultTableModel();
JTable table ;
	public CreateTable()
	{
			setLayout(new BorderLayout());
		
		setContentPane(new JLabel(new ImageIcon("2.jpg")));
		setLayout(null);
		
		l = new JLabel("Create Table");
		l.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,40));
		l.setForeground(Color.YELLOW);
		l1 = new JLabel("Tablename : ");	
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Serif",Font.ITALIC,18));
		t1 = new JTextField(15);
		
		setLayout(new FlowLayout());
		l2 = new JLabel("Attribute name :");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Serif",Font.ITALIC,18));
		t2 = new JTextField(15);
		c1 = new JComboBox();
			c1.addItem("int");
			c1.addItem("longint");
			c1.addItem("char");
			c1.addItem("varchar");
		l3 = new JLabel("size :");
		l3.setFont(new Font("Serif",Font.ITALIC,18));
		l3.setForeground(Color.WHITE);
		t3 = new JTextField(10);
		c2 = new JComboBox();
			c2.addItem("NULL");
			c2.addItem("NOT NULL");
			c2.addItem("primary key");

r1=new JRadioButton("Create Relation");
		l4 = new JLabel("Reference Table :");
		l4.setFont(new Font("Serif",Font.ITALIC,18));
		l4.setForeground(Color.WHITE);

		c3 = new JComboBox();
l6 = new JLabel("Select column :");
		l6.setFont(new Font("Serif",Font.ITALIC,18));
		l6.setForeground(Color.WHITE);

		c4 = new JComboBox();
		setLayout(null);
		b1 = new JButton("Add");
		
		String[][] data =new String[25][4];
		String[] colHeads = {"Attribute Name","Datatype","Size","Constraint"};
		table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		model.addColumn("Attribute Name");
    model.addColumn("Datatype");
    model.addColumn("Size");
    model.addColumn("Constraint");

		b2 = new JButton("Display Query");
		b3 = new JButton("Back");
	
		add(l);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(c1);
		add(l3);
		add(t3);
		add(c2);
		add(l4);
		add(c3);
add(l6);
		add(c4);
		add(b1);
		add(jsp);
		add(b2);
		add(b3);
		add(r1);
		l.setBounds(350,50,350,50);
		l1.setBounds(350,150,150,30);
		t1.setBounds(510,150,120,30);
		l2.setBounds(50,220,170,30);
		t2.setBounds(220,220,100,30);
		c1.setBounds(325,220,90,30);
		l3.setBounds(430,220,50,30);
		t3.setBounds(485,220,70,30);
		c2.setBounds(560,220,95,30);
		r1.setBounds(670,220,100,30);
		l4.setBounds(770,220,100,30);
		c3.setBounds(875,220,95,30);


		l6.setBounds(770,260,100,30);
		c4.setBounds(875,260,95,30);


		b1.setBounds(450,290,90,30);
		jsp.setBounds(250,350,500,200);
		b2.setBounds(375,620,150,30);
		b3.setBounds(530,620,100,30);
		r1.addItemListener(this);
		c3.addActionListener(this);
		b1.addActionListener(this);	
		b2.addActionListener(this);
		b3.addActionListener(this);
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tablename();
l4.setVisible(false);
c3.setVisible(false);
l6.setVisible(false);
c4.setVisible(false);

	}
public void itemStateChanged(ItemEvent e) 
{
if(r1.isSelected()==true)
{
l4.setVisible(true);
c3.setVisible(true);
l6.setVisible(true);
c4.setVisible(true);
 }
else
{
l4.setVisible(false);
c3.setVisible(false);
l6.setVisible(false);
c4.setVisible(false);
}           
             
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
c3.addItem(rs.getString("TABLE_NAME"));
				
				}
			}
		
		}	
		catch(Exception e)
	
		{
	
			System.out.println(e);
	
		}

	}//fun	
	public void actionPerformed(ActionEvent ae)
	{
if(ae.getSource()==c3)
{
try
{
c4.removeAllItems();
String s2=(String)c3.getSelectedItem();

	  		
st=cn.createStatement();
String s="SELECT * FROM "+s2;

ResultSet rs = st.executeQuery(s);
ResultSetMetaData rsmd = rs.getMetaData();

  String name = rsmd.getColumnName(1);
c4.addItem(name);
  

}catch(Exception e){}
}
		if (ae.getSource() == b3)
		{
		  Menu t=new Menu();
			
		}
if (ae.getSource() == b2)
		{
int n=0;
tb=t1.getText();
StringBuilder sb;
String resultString ;
if(r1.isSelected()==false)
{
try
{

n=sql.length();	
sb = new StringBuilder(sql);
sb.deleteCharAt(n-1);
resultString = sb.toString();
sql1="create table "+ tb +" "+"("+resultString+");";
System.out.println(sql1);

int i=st.executeUpdate(sql1);
System.out.println(i);
if(i==0)
{
JOptionPane.showMessageDialog(null,"Table created");
}
model.setRowCount(0);
model.setColumnCount(0);

}
catch(SQLException e){System.out.println(e);}
catch(Exception e1){System.out.println(e1);}
}
else
{
try
{
String s=(String)c3.getSelectedItem();
String ss=(String)c4.getSelectedItem();
n=sql.length();	
sb = new StringBuilder(sql);
sb.deleteCharAt(n-1);
resultString = sb.toString();
sql1="create table "+ tb +" "+"("+resultString+","+ss+" int"+" references "+s+"("+ss+")"+");";
System.out.println(sql1);

int i=st.executeUpdate(sql1);
System.out.println(i);
if(i==0)
{
System.out.println("created");
}
}
catch(SQLException e){System.out.println(e);}
catch(Exception e1){System.out.println(e1);}
}
}


		
		if(ae.getSource() == b1)
		{
		      
		   try
		   {
			String tablename=t1.getText();
			String Attributename=t2.getText();
			String name=(String)c1.getSelectedItem();
			String name1=(String)c2.getSelectedItem();
			String size=t3.getText();
			
			System.out.println(""+tablename);
			System.out.println(""+Attributename);
			System.out.println(""+name);
			System.out.println(""+name1);
			System.out.println(""+size);

			if(name.equals("int")||name.equals("float"))
			
				sql+=Attributename+" "+name+" "+name1+" ,";
			else
		
sql+=Attributename+" "+name+"("+size+") "+name1+" ,";
System.out.println(sql);
model.insertRow(r++, new Object[]{Attributename,name,size,name1});


			}
			catch(Exception e)
			{
			}
		    						
		}
	}
public static void main(String args[])
{
CreateTable u=new CreateTable();
}
}
