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


public class updateNeed extends JFrame{
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    updateNeed frame = new updateNeed();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public updateNeed() {
        getContentPane().setLayout(null);
        setBounds(500, 100, 1117, 765);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 104, 1050, 261);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 15));
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u9700\u6C42ID", "\u7528\u6237\u540D", "\u8054\u7CFB\u65B9\u5F0F", "\u9700\u6C42\u7269\u54C1", "\u7D27\u6025\u7A0B\u5EA6"
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

        JLabel lblid = new JLabel("请输入要更新的需求ID：");
        lblid.setFont(new Font("楷体", Font.PLAIN, 23));
        lblid.setBounds(302, 34, 269, 55);
        getContentPane().add(lblid);

        textField = new JTextField();
        textField.setBounds(586, 50, 96, 27);
        getContentPane().add(textField);
        textField.setColumns(10);
        button.setBounds(383, 662, 123, 29);
        getContentPane().add(button);

        JLabel lblNewLabel = new JLabel("用户名称");
        lblNewLabel.setBounds(563, 390, 81, 21);
        getContentPane().add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setBounds(690, 387, 96, 27);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("联系方式");
        lblNewLabel_1.setBounds(234, 466, 81, 21);
        getContentPane().add(lblNewLabel_1);

        textField_2 = new JTextField();
        textField_2.setBounds(347, 463, 96, 27);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("需求物品");
        lblNewLabel_2.setBounds(563, 466, 81, 21);
        getContentPane().add(lblNewLabel_2);

        textField_3 = new JTextField();
        textField_3.setBounds(690, 463, 96, 27);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("紧急程度");
        lblNewLabel_3.setBounds(234, 549, 81, 21);
        getContentPane().add(lblNewLabel_3);

        textField_4 = new JTextField();
        textField_4.setBounds(347, 546, 96, 27);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("备用（不写入内容）");
        lblNewLabel_4.setBounds(563, 549, 81, 21);
        getContentPane().add(lblNewLabel_4);

        textField_5 = new JTextField();
        textField_5.setBounds(690, 546, 96, 27);
        getContentPane().add(textField_5);
        textField_5.setColumns(10);

        JLabel label = new JLabel("输入更新内容：");
        label.setFont(new Font("楷体", Font.PLAIN, 23));
        label.setBounds(274, 380, 185, 46);
        getContentPane().add(label);

        JButton button_1 = new JButton("更新");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Need needIms = new Need();

                String grade = textField.getText();
                String needId = textField_1.getText();
                String userName = textField_2.getText();
                String tel=textField_3.getText();
                String needThing = textField_4.getText();


                //注册时，判断输入文本框的值不能为空
                if(stringutil.isEmpty(needId)) {
                    JOptionPane.showMessageDialog(null, "要更改的需求不能为空");
                    return;
                }
                else if(stringutil.isEmpty(userName)){
                    JOptionPane.showMessageDialog(null, "用户名称不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(tel)){
                    JOptionPane.showMessageDialog(null, "联系电话不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(needThing)){
                    JOptionPane.showMessageDialog(null, "需求物品不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(grade)){
                    JOptionPane.showMessageDialog(null, "出版社不能为空！");
                    return;
                }
                //将用户输入的信息封装到userMessage类里面
                needIms.setNeedId(needId);
                needIms.setUserName(userName);
                needIms.setTel(tel);
                needIms.setNeedThing(needThing);
                needIms.setGrade(grade);

                dbutil dbutil = new dbutil();
                needDao needdao = new needDao();

                try {
                    Connection con = dbutil.getCon();
                    int current = needdao.updateims(con,needIms);

                    //提示框，若返回值是1，添加成功
                    if(current == 1) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败,请检查图书Id是否正确！");
                        return;
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