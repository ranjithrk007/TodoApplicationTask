import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
class Picker {
    public static String result="";
    public  void picker() {
        JLabel label = new JLabel("Selected Date:");
        JLabel label1 = new JLabel("Enter the Details:");
        final JTextField text = new JTextField(20);
        final JTextArea text1 = new JTextArea(5, 20);
        final JCheckBox importantc = new JCheckBox("Important Event");
        final JCheckBox personalc = new JCheckBox("personal Event");
        importantc.setText("important");
        personalc.setText("personal");
        JButton b = new JButton("Popup");
        JButton b1 = new JButton("Save Details");
        final DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM));
        dateTimePicker.setTimeFormat(DateFormat.getTimeInstance(DateFormat.MEDIUM));
        final Date date = new Date();
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "oviya1002");
                    String detail = text.getText();
                    String detail1 = text1.getText();
                    String event=null;
                    String event1 = null;
                    if (importantc.isSelected()==true){
                         event = "important";
                    }else {
                        event = "null";
                    }
                    if (personalc.isSelected()==true){
                        event1 = "personal";
                    }else {
                        event1 = "null";
                    }
                    String query = "insert into todo (dt,details,important,personal)values(?,?,?,?)";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, detail);
                    stmt.setString(2, detail1);
                    stmt.setString(3,event);
                    stmt.setString(4,event1);
                    int set = stmt.executeUpdate();
                    if (set > 0) {
                        JOptionPane.showMessageDialog(null, "updated successfull");
                    } else {
                        JOptionPane.showMessageDialog(null, "insert valid data");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
//        b2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "root");
//                    Statement stmt = conn.createStatement();
//                    ResultSet rs = stmt.executeQuery("select * from plan order by details");
//                    StringBuffer st = new StringBuffer();
//                    while (rs.next()) {
//                        st.append(rs.getString(1) + "  " + rs.getString(2));
//                        st.append("\n");
//                    }
//                    text2.setText(st.toString());
//                    conn.close();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
        JPanel p = new JPanel();
        p.add(label);
        p.add(text);
        p.add(b);
        p.add(label1);
        p.add(text1);
        p.add(b1);
        p.add(importantc);
        p.add(personalc);
        final JFrame f = new JFrame();
        f.setSize(400, 400);
        f.add(p);
        f.setVisible(true);
        f.setVisible(true);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dateTimePicker.dothis();
                text.setText(result);
            }
        });
    }
}