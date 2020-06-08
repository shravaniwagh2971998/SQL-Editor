import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
class view extends JFrame implements ActionListener
{
 DefaultTableModel model = new DefaultTableModel();
	String s;
	JPanel p,p1,p2,p3;
	JButton b1,b2;
	JComboBox c1;
	JLabel l1,l2;
Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	static PreparedStatement ps=null;
JTable table ;JScrollPane jsp;
	public view()
	{
			setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("2.jpg")));

		setLayout(null);
		l1 = new JLabel("View Records");
		l1.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,40));

		l2 = new JLabel("Table Name : ");
		l2.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,20));
		c1 = new JComboBox();

		b1 = new JButton("View Records");
		b2 = new JButton("Back");
	b1.addActionListener(this);
	
	b2.addActionListener(this);

		add(l1);
		add(l2);
		add(c1);
		add(b1);
		add(b2);
tablename();
		l1.setBounds(350,50,300,50);
		l2.setBounds(350,140,180,30);
		c1.setBounds(535,140,100,30);
		b1.setBounds(350,530,200,30);
		b2.setBounds(560,530,100,30);

		setSize(1000,1000);
		setTitle("VIEW");

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

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


if(ae.getSource()==b1)
{
model.setColumnCount(0);
model.setRowCount(0);
int r1;
	String r,sql2="",sql="",name="";
try
{
String s2=(String)c1.getSelectedItem();

st=cn.createStatement();
String s="SELECT * FROM "+s2;
ResultSet rs = st.executeQuery(s);

   table = new JTable(buildTableModel(rs));
jsp = new JScrollPane(table);

 	jsp.setBounds(200,200,600,300);
add(jsp);

}catch(Exception e){}
}
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
	public static void main(String args[])
	{
		view j=new view();
	}
}
