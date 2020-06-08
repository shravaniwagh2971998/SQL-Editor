import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.*;
class alter extends JFrame implements ActionListener
{
	String s;
	JPanel p,p1,p2,p3;
	JButton b1,b2,b3;
	JTextField t1,t2;
	JComboBox c1,c2,c3;
	JLabel l1,l2,l3,l4;
	Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
		static PreparedStatement ps=null;
	ResultSetMetaData rsmd=null;
	//static PreparedStatement ps=null;
	ResultSetMetaData meta=null;
  DefaultTableModel model = new DefaultTableModel();
JTable table ;

	alter()
	{
				setLayout(null);
		setContentPane(new JLabel(new ImageIcon("2.jpg")));
		//setLayout(null);
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		//p = new JPanel();
		l1 = new JLabel("ALTER TABLE");

		//p1 = new JPanel(new FlowLayout());
		l2 = new JLabel("Table Name : ");
		c1 = new JComboBox();

		//p2 = new JPanel();		
		l3 = new JLabel("Column Name :");
		t1 = new JTextField(15);
		c2 = new JComboBox();
			c2.addItem("int");
			c2.addItem("char");
			c2.addItem("varchar");
			c2.addItem("long int");
			c2.addItem("numeric");
		l4 = new JLabel("Size : ");
		t2 = new JTextField(10);
		c3 = new JComboBox();
			c3.addItem("NOT NULL");
			c3.addItem("NULL");
		
		//p3 = new JPanel();
		b1 = new JButton("Back");
		b2 = new JButton("Display Query");
		b3 = new JButton("Cancel");

		add(l1);
		add(l2);
		add(c1);
		add(l3);
		add(t1);
		add(c2);
		add(l4);
		add(t2);
		add(c3);
		add(b1);
		add(b2);
		add(b3);		

		l1.setBounds(450,50,200,50);
		l2.setBounds(350,120,150,30);
		c1.setBounds(450,120,120,30);
		l3.setBounds(150,200,170,30);
		t1.setBounds(250,200,100,30);
		c2.setBounds(370,200,100,30);
		l4.setBounds(490,200,50,30);
		t2.setBounds(540,200,70,30);
		c3.setBounds(630,200,90,30);
		b1.setBounds(250,400,100,30);
		b2.setBounds(380,400,150,30);
		b3.setBounds(550,400,100,30);

		b1.addActionListener(this);	
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		c1.addActionListener(this);	
		c2.addActionListener(this);
		c3.addActionListener(this);
		

		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tablename();
	}

		void tablename()
	{
		try
	   	{
			//Class.forName("com.mysql.jdbc.Driver");
		 	//cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tybcs","root","root");
Class.forName("org.postgresql.Driver");
		//JOptionPane.showMessageDialog(null,"driver register...");
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
			//System.out.println(e);
			JFrame f=new JFrame();
			System.out.println(e);
			JOptionPane.showMessageDialog(f,e, "Invalid", JOptionPane.ERROR_MESSAGE);

		}
	}//fun

	public void actionPerformed(ActionEvent e)
    	{
    			if(b1==e.getSource())
				{
					Menu o=new Menu();
					
				}
			
			
		if(e.getSource()==c1)
		{
/*			model.setRowCount(0);
			//model.setRowCount(40);

			try
			{


			String s2=(String)c1.getSelectedItem();
//System.out.println(s2);
	  		
st=cn.createStatement();
String s="SELECT * FROM "+s2;

ResultSet rs = st.executeQuery(s);
ResultSetMetaData rsmd = rs.getMetaData();
int columnCount = rsmd.getColumnCount();
int j=0;
		for (int i = 1; i <= columnCount;i++) 
{
  String name = rsmd.getColumnName(i);
model.insertRow(j++, new Object[]{name});
  
} 
}
catch(Exception re)
{
	System.out.println(re);
}*/

}

if (e.getSource() ==b2)
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
s22=(String)c1.getSelectedItem();
/*st=cn.createStatement();
String s="SELECT * FROM "+s22;
ResultSet rs = st.executeQuery(s);
ResultSetMetaData rsmd = rs.getMetaData();
int columnCount = rsmd.getColumnCount();
for (i = 1; i <= columnCount;i++) 
{
  s1[k] = rsmd.getColumnName(i);

 System.out.println(s1[k]);         
k++;
}
*/

/*for (i = 0; i <columnCount;i++)  
{
s2[j] = (String) model.getValueAt(i,1);
System.out.println(s2[j]);
j++;         
}
//s2[j-1]="priya";
//System.out.println(s2[j-1]);
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
//System.out.println(r);
//System.out.println(r1);*/


String cn1=t1.getText();
String datat=(String)c2.getSelectedItem();
String size=t2.getText();
String consta=(String)c3.getSelectedItem();

ins+="alter table " + s22 + " add "+ cn1 +" "+ datat +"(" +size+ ")" ;
System.out.println(ins);

st=cn.createStatement();
j=st.executeUpdate(ins);
if(j>0)
JOptionPane.showMessageDialog(null,"alter");
else
JOptionPane.showMessageDialog(null," insert another data primary key");

}
catch(Exception be)
{
System.out.println(be);
}
}

} 

public static void main(String args[])
{
alter u1=new alter();
}

}
