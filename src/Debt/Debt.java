package Debt;


import net.proteanit.sql.DbUtils;
//import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;


public class Debt extends JFrame {

    public Connection conn = null;
    public Statement statement = null;
    public ResultSet resultSet = null;
    String url = "jdbc:postgresql://localhost:5432/univer";
    String user = "admin";
    String password = "admin";
    private JPanel msgJPanel;
    // showTable ();

    private JTextField textField1;
    private JButton addButton;
    private JPanel Panel;
    private JTable table;
    private JTextField textField4;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel jdn;
    private JPanel DebtPanel;
    private JButton delateButton;
    private JTextField jpCald;
    private JTextField textField5;
    private JLabel labelSearch;
    private JLabel label;
    private JTextField texField6;
    private JTextField textField7;
    private JButton addButton2;
    private JButton subButton;
    private JTextField textField8;
    private JScrollPane scrolp;
    private JButton ubdatebutton;
    private JComboBox comboBox1;
    private JLabel lNum;
    private JLabel id;
    private JLabel labelLastName;
    private JLabel labelFirsName;
    private JLabel labelDebt;
    private JButton languageButton;
    private JTextField textFieldToday;


    //How to connect databese
    public Connection connectDb() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/univer", "admin", "admin");
            statement = conn.createStatement();
            String sql = "SELECT*FROM " + '"' + "notebook" + '"';
            resultSet = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return conn;
    }

    public Debt() {

        Date date = Calendar.getInstance().getTime();
      //  Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy");

        String today = formatter.format(date);
        System.out.println("Today : " + today);
        label.setText(today);
       // label.setText(String.valueOf(now));
        //  textFieldToday.setText(today);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String object[][] = {{"Id", "LastName", "FistName", "Debt"}};
        String column[] = {" ", " ", " ", " "};
        model.setDataVector(object, column);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectDb();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(new Object[]{textField3.getText(), textField1.getText(), textField4.getText(), textField2.getText()});
                try {
                    String sql = "INSERT INTO public.notebook(id, lastname, firstname, debt)" +
                            "VALUES (?, ?, ?, ?)";
                    System.out.println(sql);


                    PreparedStatement add = conn.prepareStatement(sql);
                    add.setInt(1, parseInt(textField3.getText()));
                    add.setString(2, textField1.getText());
                    add.setString(3, textField4.getText());
                    add.setInt(4, parseInt(textField2.getText()));

                    textField3.setText("");
                    textField1.setText("");
                    textField4.setText("");
                    textField2.setText("");
                    int row = add.executeUpdate();

                } catch (SQLException | HeadlessException ex) {

                    JOptionPane.showMessageDialog(null, ex);
                }

            }

        });


        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //    JFrame f=new JFrame();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

                String tableId = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
                String tablelName = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
                String tablefName = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
                String tableCal = tableModel.getValueAt(table.getSelectedRow(), 3).toString();
                //  String tablejpcald=tableModel.getValueAt(table.getSelectedRow(),4).toString();


                textField3.setText(tableId);
                textField1.setText(tablelName);
                textField4.setText(tablefName);
                textField2.setText(tableCal);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        delateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectDb();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                //tableModel.addRow(new Object[]{textField3.getText(), textField1.getText(), textField4.getText(), textField2.getText()});
                try {
                    int SelectedRow = table.getSelectedRow();
                    String sql;
                    sql = "DELETE FROM public.notebook WHERE id=?";
                    statement = conn.createStatement();
                    //tableModel.removeRow(SelectedRow);
                    PreparedStatement add = conn.prepareStatement(sql);


                    add.setInt(1, parseInt(textField3.getText()));
                    textField3.setText("");
                    textField1.setText("");
                    textField4.setText("");
                    textField2.setText("");
                    add.executeQuery();
                    showTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Delated");
                }

            }
        });

        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int one = Integer.parseInt(texField6.getText());
                int two = Integer.parseInt(textField7.getText());
                String ans = String.valueOf(one + two);
                textField8.setText(ans);
                texField6.setText("");
                textField7.setText("");

            }
        });
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int one = Integer.parseInt(texField6.getText());
                int two = Integer.parseInt(textField7.getText());

                String ans = String.valueOf(one - two);
                textField8.setText(ans);
                texField6.setText("");
                textField7.setText("");
            }
        });

        ubdatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectDb();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.addRow(new Object[]{textField3.getText(), textField1.getText(), textField4.getText(), textField2.getText()});
                try {

                    String sql;
                    sql = "UPDATE notebook SET id=?, lastname=?, firstname=?, debt=?";


                    PreparedStatement add = conn.prepareStatement(sql);
                    add.setInt(1, parseInt(textField3.getText()));
                    add.setString(2, textField1.getText());
                    add.setString(3, textField4.getText());
                    add.setInt(4, parseInt(textField2.getText()));
                    add.setInt(5, parseInt(textField3.getText()));
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");

                    int row = add.executeUpdate();

                } catch (SQLException | HeadlessException ex) {

                    JOptionPane.showMessageDialog(null, ex);
                }
            }


            //   return conn;
        });
        showTable();
        DefaultTableModel tableModel;
        textField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
           //     super.keyReleased(e);
              //  DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
             //   tableModel.addRow(new Object[]{textField3.getText(), textField1.getText(), textField4.getText(), textField2.getText()});

                try {


                   String name = textField5.getText().trim();
                   if(!name.equals("")) {
                       String searchQuery = ("SELECT * FROM  notebook WHERE  lastname like '%" + name + "%' or firstname like '%" + name + "%' LIMIT 5");
                       PreparedStatement pst = conn.prepareStatement(searchQuery);
                       ResultSet rs = pst.executeQuery();
                       table.setModel(DbUtils.resultSetToTableModel(rs));
                     /*  while (rs.next()){
                           int id=rs.getInt(1);
                           String last=rs.getString(2);
                           String nam=rs.getString(3);
                           int debt=rs.getInt(4);

                           User user=new User(id,last,nam,debt);*/


                      // }
                   }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectVal = comboBox1.getSelectedItem().toString();
                lNum.setText(selectVal);
                if (selectVal.equals("UZ")) labelLastName.setText("Familiyasi");
                if (selectVal.equals("UZ")) labelFirsName.setText("Ismi");
                if (selectVal.equals("UZ")) labelDebt.setText("Qarzi");
                if (selectVal.equals("UZ")) labelSearch.setText("Qidirish");
                if(selectVal.equals("UZ")) jdn.setText("QARZDORLIK_DAFTARI");
                if(selectVal.equals("UZ")) addButton.setText("Saqlash");
                if(selectVal.equals("UZ")) delateButton.setText("Uchirish");
                if(selectVal.equals("UZ")) ubdatebutton.setText("Taxrirlash");
                if(selectVal.equals("UZ")) addButton2.setText("Qushish");
                if(selectVal.equals("UZ")) subButton.setText("Ayirish");

                  if(selectVal.equals("RUS")) labelLastName.setText("Ochestva");
                  if(selectVal.equals("RUS")) labelFirsName.setText("Imya");
                 if(selectVal.equals("RUS")) labelDebt.setText("Dolg");
                if(selectVal.equals("RUS")) labelSearch.setText("Iskat");
                if(selectVal.equals("RUS")) jdn.setText("DOLGAVAYA_KNIJKA");
                if(selectVal.equals("RUS")) addButton.setText("Soxranit");
                if(selectVal.equals("RUS")) delateButton.setText("Udalit");
                if(selectVal.equals("RUS")) ubdatebutton.setText("Obnovit");
                if(selectVal.equals("RUS")) addButton2.setText("Dobavit");
                if(selectVal.equals("RUS")) subButton.setText("Umnojit");

                if(selectVal.equals("ENG")) labelLastName.setText("FirtName");
                if(selectVal.equals("ENG")) labelFirsName.setText("LastName");
                if(selectVal.equals("ENG")) labelDebt.setText("Debt");
                if(selectVal.equals("ENG")) labelSearch.setText("Search");
                if(selectVal.equals("ENG")) jdn.setText("DEBT_NOTEBOOK");
                if(selectVal.equals("ENG")) addButton.setText("Save");
                if(selectVal.equals("ENG")) delateButton.setText("Delete");
                if(selectVal.equals("ENG")) ubdatebutton.setText("Update");
                if(selectVal.equals("ENG")) addButton2.setText("Add");
                if(selectVal.equals("ENG")) subButton.setText("Sub");

            else lNum.setText(" ");


            }


        });
    }

    public void findNotebook() {


    }

    private void showTable() {

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/univer", "admin", "admin");
            String sql = "SELECT id, lastname, firstname, debt\n" +
                    "\tFROM public.notebook  order by id asc;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void setData() {


    }

    public static void main(String[] args) {
        Debt bigDebt = new Debt();
        bigDebt.connectDb();
        JFrame frame = new JFrame("Debt");
        frame.setContentPane(new Debt().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(650, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        bigDebt.setData();
        JTextField textField5 = bigDebt.textField5;


    }


}