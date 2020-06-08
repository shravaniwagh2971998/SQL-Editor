import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
class select extends JFrame implements ActionListener
{
DefaultListModel aName = new DefaultListModel();
	String s;
	JButton b1,b2,b3,b4,b5,b6;
	JTextField t1,t2;
	JComboBox c1,c2,c3,c4,c5,c6;
	JLabel l1,l2,l3,l4,l5;
	JTextArea a1,a2;
	JRadioButton r[]= new JRadioButton[2];
	ButtonGroup grp1;
Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
	 DefaultTableModel model = new DefaultTableModel();
JList atrlist ;
	int columnCount;
JTable table;JScrollPane jsp;

	public select()
	{
				setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("2.jpg")));

		setLayout(null);
		l1 = new JLabel("Select Records");
		l1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,50));
		l2 = new JLabel("Table name : ");
		l2.setFont(new Font("Serif",Font.ITALIC,18));
		c1 = new JComboBox();
				a1 = new JTextArea(3,15);


		c2 = new JComboBox();

		a2 = new JTextArea(3,15);
String s1[]={"One Attribute","And","Or"};
		c6 = new JComboBox(s1);
		r[0]=new JRadioButton("and");
		r[1]=new JRadioButton("or");
		grp1 = new ButtonGroup();
		for(int i=0;i<2;i++)
		{
			add(r[i]);
			grp1.add(r[i]);
			r[i].setFont(new Font("Serif",Font.ITALIC,20));
		}

		l3 = new JLabel("Select Attribute for Diplay :");
		l3.setFont(new Font("Serif",Font.BOLD,16));
		c2= new JComboBox();
		c3 = new JComboBox();
			c3.addItem("=");
			c3.addItem("<");
			c3.addItem("<=");
			c3.addItem(">");
			c3.addItem(">=");
c4= new JComboBox();
		c5 = new JComboBox();
			c5.addItem("=");
			c5.addItem("<");
			c5.addItem("<=");
			c5.addItem(">");
			c5.addItem(">=");

		l4 = new JLabel("Value1 :");
		l4.setFont(new Font("Serif",Font.BOLD,14));
		t1 = new JTextField(20);
t2 = new JTextField(20);
l5 = new JLabel("Value2 :");
		l5.setFont(new Font("Serif",Font.BOLD,14));
				String[][] data =new String[25][4];
		String[] colHeads = {"Attribute Name","Operator","Value","and/or"};
		table = new JTable(model);
		jsp = new JScrollPane(table);

		b1 = new JButton("Display Query");
		b2 = new JButton("Back");



		l1.setBounds(280,50,420,50);

		l2.setBounds(150,150,130,30);
		c1.setBounds(285,150,90,30);
l3.setBounds(380,150,200,30);
 add(l3);

		c6.setBounds(20,260,100,30);
		c2.setBounds(135,260,100,30);
		c3.setBounds(240,260,85,30);
		l4.setBounds(330,260,70,30);
		t1.setBounds(400,260,100,30);

		c4.setBounds(490,260,100,30);
		c5.setBounds(590,260,85,30);
		l5.setBounds(690,260,70,30);
		t2.setBounds(770,260,100,30);
c4.setVisible(false);
c5.setVisible(false);
l5.setVisible(false);
t2.setVisible(false);


		jsp.setBounds(250,330,500,230);
		b1.setBounds(375,570,150,30);
		b2.setBounds(530,570,100,30);

		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		add(l1);
		add(l2);
		add(c1);
		add(b1);
		add(a1);
		add(b2);
		add(c2);
		add(a2);
		add(c6);
		add(l3);
		add(c3);
		add(c4);add(c5);
		add(l4);add(l5);
		add(t1);add(t2);
		add(jsp);
	tablename();
c1.addActionListener(this);
c6.addActionListener(this);
b1.addActionListener(this);
b2.addActionListener(this);

	}
public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

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

	public void actionPerformed(ActionEvent ae)
	{
if(ae.getSource()==b2)
{
	Menu t1=new Menu();
}

if(ae.getSource()==c6)
{
String s=(String)c6.getSelectedItem();
if(s.compareTo("And")==0 || s.compareTo("Or")==0)
{
c4.setVisible(true);
c5.setVisible(true);
l5.setVisible(true);
t2.setVisible(true);
}
else
{
c4.setVisible(false);
c5.setVisible(false);
l5.setVisible(false);
t2.setVisible(false);
}
}

if(ae.getSource()==c1)
	{
try
{
c2.removeAllItems();
c4.removeAllItems();
aName.clear();
String s2=(String)c1.getSelectedItem();
st=cn.createStatement();
String s="SELECT * FROM "+s2;
ResultSet rs = st.executeQuery(s);
ResultSetMetaData rsmd = rs.getMetaData();
columnCount = rsmd.getColumnCount();
int j=0;
for (int i = 1; i <= columnCount;i++)
{
String name = rsmd.getColumnName(i);
c2.addItem(name);
c4.addItem(name);
aName.addElement(name);
}
atrlist = new JList(aName);
atrlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
   
 atrlist.setSelectedIndex(1);

   JScrollPane atrlistScrollPane = new JScrollPane(atrlist);
add(atrlist);
add(atrlistScrollPane);
atrlist.setBounds(560,90,200,150);
}catch(Exception e){System.out.println(e);}

			}

if(ae.getSource()==b1)
	{
model.setRowCount(0);
model.setColumnCount(0);
String select[]=new String[20];
String from,sql="",r="";
String col,op,val,col1,op1,val1;

String s=(String)c6.getSelectedItem();
if(s.compareTo("And")==0 || s.compareTo("Or")==0)
{

try{

from=(String)c1.getSelectedItem();

 int[] selectedIx = atrlist.getSelectedIndices();

    for (int i = 0; i < selectedIx.length; i++)
{
    Object o = atrlist.getModel().getElementAt(selectedIx[i]);
select[i]=(String)o;
System.out.println(select[i]);
    }
col=(String)c2.getSelectedItem();
op=(String)c3.getSelectedItem();
val=t1.getText();
col1=(String)c4.getSelectedItem();
op1=(String)c5.getSelectedItem();
val1=t2.getText();

 for (int i = 0; i < selectedIx.length; i++)
{
sql+=select[i]+",";
}
System.out.println(sql);
StringBuilder sb;
int n=sql.length();
sb = new StringBuilder(sql);
sb.deleteCharAt(n-1);
r = sb.toString();
System.out.println(r);
sql="";
if(s.compareTo("And")==0 )
{
sql="select "+r+" from "+from+" where " +col+""+op+" '"+val+"'    and " +col1+""+op1+" '"+val1+"';";
}
else
{
sql="select "+r+" from "+from+" where " +col+""+op+" '"+val+"'    or " +col1+""+op1+" '"+val1+"';";

}
System.out.println(sql);
st=cn.createStatement();
ResultSet rs=st.executeQuery(sql);
ResultSetMetaData rsmd = rs.getMetaData();
System.out.println(rsmd.getColumnCount());
table = new JTable(buildTableModel(rs));
jsp = new JScrollPane(table);
jsp.setBounds(250,330,500,230);
add(jsp);


}catch(Exception e){System.out.println(e);}



}
else
{
try{
from=(String)c1.getSelectedItem();

 int[] selectedIx = atrlist.getSelectedIndices();

    for (int i = 0; i < selectedIx.length; i++)
{
    Object o = atrlist.getModel().getElementAt(selectedIx[i]);
select[i]=(String)o;
System.out.println(select[i]);
    }
col=(String)c2.getSelectedItem();
op=(String)c3.getSelectedItem();
val=t1.getText();
 for (int i = 0; i < selectedIx.length; i++)
{
sql+=select[i]+",";
}
System.out.println(sql);
StringBuilder sb;
int n=sql.length();
sb = new StringBuilder(sql);
sb.deleteCharAt(n-1);
r = sb.toString();
System.out.println(r);
sql="";
sql="select "+r+" from "+from+" where " +col+""+op+" '"+val+"';";
System.out.println(sql);
st=cn.createStatement();
ResultSet rs=st.executeQuery(sql);
ResultSetMetaData rsmd = rs.getMetaData();
System.out.println(rsmd.getColumnCount());
 table = new JTable(buildTableModel(rs));
jsp = new JScrollPane(table);
jsp.setBounds(250,330,500,230);
add(jsp);


}catch(Exception e){System.out.println(e);}
}
	}

	}
	public static void main(String args[])
	{
		select t=new select();
	}
}

