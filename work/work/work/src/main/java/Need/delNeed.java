package Need;

import Dao.needDao;
import User.Need;
import util.dbutil;
import util.stringutil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class delNeed extends JFrame{
    private JTable table;
    private JTextField textField;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    delNeed frame = new delNeed();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public delNeed() {
        getContentPane().setLayout(null);
        setBounds(500, 100, 1117, 765);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 104, 1050, 543);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 15));
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u7528\u6237ID", "\u7528\u6237\u540D", "\u8054\u7CFB\u65B9\u5F0F", "\u9700\u6C42\u7269\u54C1", "\u7D27\u6025\u7A0B\u5EA6"
                }
        ));
        scrollPane.setViewportView(table);

        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                dbutil dbutil = new dbutil();
                needDao needdao = new needDao();
                Need needMessage = new Need();

                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                dtm.setRowCount(0);//初始化为0行
                try {
                    con = dbutil.getCon();
                    ResultSet currentneed = needdao.list(con,needMessage);

                    while(currentneed.next()) {
                        Vector v = new Vector();
                        v.add(currentneed.getString("needId"));
                        v.add(currentneed.getString("userName"));
                        v.add(currentneed.getString("tel"));
                        v.add(currentneed.getString("needThing"));
                        v.add(currentneed.getString("grade"));
//                        if(currentneed.getString("grade").equals("0")) {
//                            v.add("很紧急");
//                        } else {
//                            v.add("较紧急");
//                        }
                        dtm.addRow(v);
                    }

                }catch(Exception evt) {
                    evt.printStackTrace();
                }
            }
        });

        JLabel lblid = new JLabel("请输入要删除的需求ID：");
        lblid.setFont(new Font("楷体", Font.PLAIN, 23));
        lblid.setBounds(302, 34, 269, 55);
        getContentPane().add(lblid);

        textField = new JTextField();
        textField.setBounds(586, 50, 96, 27);
        getContentPane().add(textField);
        textField.setColumns(10);
        button.setBounds(383, 662, 123, 29);
        getContentPane().add(button);

        JButton button_1 = new JButton("删除");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textid = textField.getText();
                if(stringutil.isEmpty(textid)) {
                    JOptionPane.showMessageDialog(null, "图书账号不能为空！");
                    return;
                }

                Connection con = null;
                dbutil dbutil = new dbutil();
                needDao needDao = new needDao();

                try {
                    con = dbutil.getCon();
                    int currentUser = needDao.delete(con,textid);//若找并删除，返回值为1

                    if(currentUser == 1) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "未找到该图书！");
                    }
                }catch(Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
        button_1.setBounds(551, 662, 123, 29);
        getContentPane().add(button_1);
    }

}