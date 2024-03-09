package Need;

import Dao.needDao;
import User.Need;
import util.dbutil;
import util.stringutil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;

public class addNeed extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    addNeed frame = new addNeed();
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
    public addNeed() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1013, 702);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("发布需求");
        label.setFont(new Font("楷体", Font.PLAIN, 30));
        label.setBounds(425, 42, 128, 59);
        contentPane.add(label);

        JLabel lblNewLabel_1 = new JLabel("需求ID:");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 23));
        lblNewLabel_1.setBounds(272, 117, 106, 44);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(435, 128, 184, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("用户名称:");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 23));
        lblNewLabel_2.setBounds(272, 208, 106, 24);
        contentPane.add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(435, 209, 184, 27);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("需求物品：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 23));
        lblNewLabel_3.setBounds(272, 282, 128, 24);
        contentPane.add(lblNewLabel_3);

        textField_2 = new JTextField();
        textField_2.setBounds(435, 283, 184, 27);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("联系方式：");
        lblNewLabel_4.setForeground(Color.BLACK);
        lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 23));
        lblNewLabel_4.setBounds(272, 362, 128, 24);
        contentPane.add(lblNewLabel_4);

        textField_3 = new JTextField();
        textField_3.setBounds(435, 363, 184, 27);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("紧急程度：");
        lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 23));
        lblNewLabel_5.setBounds(272, 443, 128, 24);
        contentPane.add(lblNewLabel_5);

        textField_4 = new JTextField();
        textField_4.setBounds(435, 444, 184, 27);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JButton btnNewButton = new JButton("发布");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Need needIms = new Need();

                String id = textField.getText();
                String name = textField_1.getText();
                String NeedThing = textField_2.getText();
                String Tel = textField_3.getText();
                String Grade = textField_4.getText();
                //注册时，判断输入文本框的值不能为空
                if(stringutil.isEmpty(id)) {
                    JOptionPane.showMessageDialog(null, "需求ID不能为空");
                    return;
                }
                else if(stringutil.isEmpty(name)){
                    JOptionPane.showMessageDialog(null, "用户名称不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(NeedThing)){
                    JOptionPane.showMessageDialog(null, "需求物品不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(Tel)){
                    JOptionPane.showMessageDialog(null, "联系电话不能为空！");
                    return;
                }

                //将用户输入的信息封装到userMessage类里面
                needIms.setNeedId(id);
                needIms.setUserName(name);

                needIms.setTel(Tel);
                needIms.setNeedThing(NeedThing);
                needIms.setGrade(Grade);

                dbutil dbutil = new dbutil();
                needDao needdao = new needDao();

                try {
                    Connection con = dbutil.getCon();

                    //先判断需求Id在是否存在，若存在，重新输入
                    ResultSet currentId = needdao.needCheck(con, id);
                    if(currentId.next()) {
                        JOptionPane.showMessageDialog(null, "需求Id已存在，请重新输入");
                        return;
                    }

                    //若不存在，可以进行插入
                    int current = needdao.add(con,needIms);

                    //提示框，若返回值是1，添加成功
                    if(current == 1) {
                        JOptionPane.showMessageDialog(null, "发布成功");
                        //frame.dispose();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "发布失败！");
                        return;
                    }
                }catch(Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(333, 545, 123, 29);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("返回");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainNeedwindow ct=new mainNeedwindow();
                ct.setVisible(true);
                dispose();//关闭当前界面
            }
        });
        btnNewButton_1.setBounds(520, 545, 123, 29);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
            }
            public void ancestorMoved(AncestorEvent event) {
            }
            public void ancestorRemoved(AncestorEvent event) {
            }
        });
        lblNewLabel.setIcon(new ImageIcon("D:\\work\\src\\image\\img2.jpg"));
        lblNewLabel.setBounds(-126, 0, 1117, 646);
        contentPane.add(lblNewLabel);
    }

}
