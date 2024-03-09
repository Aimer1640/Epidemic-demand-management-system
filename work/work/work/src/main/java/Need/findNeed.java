package Need;

import Dao.needDao;
import User.Need;
import util.dbutil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class findNeed extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    findNeed frame = new findNeed();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public findNeed() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1185, 748);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("查询需求");
        label.setFont(new Font("楷体", Font.PLAIN, 25));
        label.setBounds(500, 15, 109, 70);
        contentPane.add(label);

//        JComboBox comboBox = new JComboBox();
//        comboBox.addItem("需求ID");
//        comboBox.addItem("用户名");
//        comboBox.addItem("需求物品");
//
//       // comboBox.setModel(new DefaultComboBoxModel(new String[] {"需求ID", "用户名", "需求物品"}));
//        comboBox.setBounds(601, 92, 109, 39);
//        contentPane.add(comboBox);


        JLabel lblid = new JLabel("用户名称：");
        lblid.setBounds(214, 101, 81, 21);
        contentPane.add(lblid);

        textField = new JTextField();
        textField.setBounds(287, 97, 109, 29);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("需求物品：");
        label_1.setBounds(471, 101, 101, 21);
        contentPane.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(554, 97, 116, 29);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel label_2 = new JLabel("需求ID：");
        label_2.setBounds(768, 101, 101, 21);
        contentPane.add(label_2);

        textField_2 = new JTextField();
        textField_2.setBounds(861, 98, 101, 28);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(124, 147, 925, 458);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u9700\u6C42ID", "\u7528\u6237\u540D\u79F0", "\u8054\u7CFB\u65B9\u5F0F", "\u9700\u6C42\u7269\u54C1", "\u7D27\u6025\u7A0B\u5EA6"
                }
        ));
        scrollPane.setViewportView(table);

        JButton button = new JButton("查询");
        //监听
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取下拉框文字
//                String box = (String)comboBox.getSelectedItem();
                Connection con = null;
                dbutil dbutil = new dbutil();
                needDao needdao = new needDao();
                Need needIms = new Need();

                String username = textField.getText().toString();
                String needThing = textField_1.getText().toString();
                String needId = textField_2.getText().toString();
//                String grade = textField_2.getText().toString();

                needIms.setUserName(username);
                needIms.setNeedThing(needThing);
                needIms.setNeedId(needId);
//                needIms.setGrade(grade);
//                if(box.equals("需求ID")){
//                    String needId = textField.getText().toString();
//                    needIms.setNeedId(needId);
//                }
//                if (box.equals("用户ID")){
//                    String username = textField.getText().toString();
//                    needIms.setNeedName(username);
//                }
//                if (box.equals("用户ID")){
//                    String needThing = textField.getText().toString();
//                    needIms.setNeedName(needThing);
//                }
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.setRowCount(0);//初始化为0行
//                table.setFont(f2);
                try {
                    con = dbutil.getCon();
                    ResultSet userneed = needdao.list(con,needIms);

                    while(userneed.next()) {
                        Vector v = new Vector();
                        v.add(userneed.getString("needId"));
                        v.add(userneed.getString("userName"));
                        v.add(userneed.getString("tel"));
                        v.add(userneed.getString("needThing"));
                        if(userneed.getString("grade").equals("0")) {
                            v.add("较紧急");
                        } else {
                            v.add("很紧急");
                        }
                        dtm.addRow(v);
                    }

                }catch(Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
        button.setBounds(434, 631, 123, 29);
        contentPane.add(button);

        JButton button_1 = new JButton("返回");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainNeedwindow ct=new mainNeedwindow();
                ct.setVisible(true);
                dispose();//关闭当前界面
            }
        });
        button_1.setBounds(593, 631, 123, 29);
        contentPane.add(button_1);
    }
}
