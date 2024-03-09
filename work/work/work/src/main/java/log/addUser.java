package log;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Dao.userDao;
import User.User;
import util.dbutil;
import util.stringutil;

public class addUser {

    public static void addUser() {
        JFrame frame = new JFrame("注册界面");
        frame.setLayout(null);
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        JPanel repan = new JPanel();
        JPanel pan5 = new JPanel();

        JTextField textId = new JTextField();
        JTextField textName = new JTextField();
        JPasswordField passwordfield = new JPasswordField();
        JPasswordField repasswordfield = new JPasswordField();

        JLabel label1 = new JLabel("用 户 注 册 ");
        JLabel label2 = new JLabel("账    号  ");
        JLabel label3 = new JLabel("用 户 名  ");
        JLabel label4 = new JLabel("密    码  ");
        JLabel label5 = new JLabel("再次输入密码  ");

        JButton button1 = new JButton("提交");

        Font font = new Font("楷体",Font.BOLD,50);//标题字体大小
        Font f = new Font("楷体",Font.BOLD,25);//提示框字体大小

        textId.setPreferredSize(new Dimension(200,30));
        textName.setPreferredSize(new Dimension(200,30));
        passwordfield.setPreferredSize(new Dimension(200,30));
        repasswordfield.setPreferredSize(new Dimension(200,30));
        button1.setPreferredSize(new Dimension(90,40));

        label1.setFont(font);
        label2.setFont(f);
        textId.setFont(f);
        label3.setFont(f);
        textName.setFont(f);
        label4.setFont(f);
        passwordfield.setFont(f);
        label5.setFont(f);
        repasswordfield.setFont(f);
        button1.setFont(f);

        pan1.add(label1);
        pan2.add(label2);
        pan2.add(textId);
        pan3.add(label3);
        pan3.add(textName);
        pan4.add(label4);
        pan4.add(passwordfield);
        repan.add(label5);
        repan.add(repasswordfield);//重复密码
        pan5.add(button1);

        pan1.setBounds(235,50, 430, 60);
        pan2.setBounds(235,170, 430, 50);
        pan3.setBounds(235,240, 430, 50);
        pan4.setBounds(235,310, 430, 50);
        repan.setBounds(235,380, 430, 50);
        pan5.setBounds(450,440,100, 50);

        frame.add(pan1);
        frame.add(pan2);
        frame.add(pan3);
        frame.add(pan4);
        frame.add(repan);
        frame.add(pan5);

        //事件监听
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User userMessage = new User();
                String id = textId.getText().toString();
                String name = textName.getText().toString();
                String password = new String(passwordfield.getPassword());
                String repassword = new String(repasswordfield.getPassword());


                while(!checkName(name)) {
                    JOptionPane.showMessageDialog(null, "用户名不合法，请重新输入大写字母开头的8个字母字符");
                    return;
//                    System.out.println("用户名不合法，请重新输入：");
                }

                while(!checkPwd(password)) {
                    JOptionPane.showMessageDialog(null, "密码不合法，请重新输入8位数字");
                    return;
//                    System.out.println("密码不合法，请重新输入：");
                }

                if(!password.equals(repassword)) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入");
                    return;
                }
                if(stringutil.isEmpty(id)) {
                    JOptionPane.showMessageDialog(null, "账号不能为空");
                    return;
                }
                else if(stringutil.isEmpty(name)){
                    JOptionPane.showMessageDialog(null, "用户名不能为空！");
                    return;
                }
                else if(stringutil.isEmpty(password)){
                    JOptionPane.showMessageDialog(null, "密码不能为空！");
                    return;
                }
                //将用户输入的信息封装到userMessage类里
                userMessage.setUserId(id);
                userMessage.setUsername(name);
                userMessage.setPassword(password);
                dbutil dbutil = new dbutil();
                userDao userdao = new userDao();
                try {
                    Connection con = dbutil.getCon();
                    int current = userdao.add(con,userMessage);
                    if(current != 0) {
                        JOptionPane.showMessageDialog(null, "注册成功");
                        frame.dispose();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "账号已存在！");
                        return;
                    }
                }catch(Exception evt) {
                    evt.printStackTrace();
                }
            }
        });
        frame.setBounds(500, 150, 950, 650);
        frame.setVisible(true);
    }
    public static boolean checkName(String name) {
        String regExp = "^[A-Z][a-z]{7}$";
        if(name.matches(regExp)) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean checkPwd(String pwd) {
        String regExp = "^[0-9]{8,}$";
        if(pwd.matches(regExp)) {
            return true;
        }
        return false;
    }
}
