package log;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Dao.adminDao;
import Dao.userDao;
import Need.adminwindow;
import Need.mainNeedwindow;
import Need.updateNeed;
import User.Admin;
import User.User;
import util.dbutil;
import util.stringutil;

public class login{
    static JTextField textId = new JTextField();
    static JPasswordField passwordfield = new JPasswordField();
    static JComboBox<String> comBox = new JComboBox<String>();
    static String userName = null;
    static String adminName = null;
    static String id = null;
    static JFrame frame = new JFrame("登录界面");
    private JLabel validcode = new JLabel("验证码：");


    public static void login() {
        frame.setLayout(null);
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        JPanel pan5 = new JPanel();
        JPanel pan6 = new JPanel();

        comBox.addItem("普通用户");
        comBox.addItem("管理员");
        comBox.addItem("志愿者");

        JLabel label1 = new JLabel("疫情需求管理系统");
        JLabel label2 = new JLabel("身    份  ");
        JLabel label3 = new JLabel("账    号  ");
        JLabel label4 = new JLabel("密    码  ");

        JButton button1 = new JButton("登录");
        JButton button2 = new JButton("注册");

        Font font = new Font("楷体",Font.BOLD,50);//标题字体大小
        Font f = new Font("楷体",Font.BOLD,25);//提示框字体大小

        comBox.setPreferredSize(new Dimension(200,30));
        textId.setPreferredSize(new Dimension(200,30));
        passwordfield.setPreferredSize(new Dimension(200,30));
        button1.setPreferredSize(new Dimension(90,40));
        button2.setPreferredSize(new Dimension(90,40));

        label1.setFont(font);
        label2.setFont(f);
        comBox.setFont(f);
        label3.setFont(f);
        textId.setFont(f);
        label4.setFont(f);
        passwordfield.setFont(f);
        button1.setFont(f);
        button2.setFont(f);

        pan1.add(label1);
        pan2.add(label2);
        pan2.add(comBox);
        pan3.add(label3);
        pan3.add(textId);
        pan4.add(label4);
        pan4.add(passwordfield);
        pan5.add(button1);
        pan6.add(button2);

        pan1.setBounds(235,50, 430, 60);
        pan2.setBounds(235,170, 430, 50);
        pan3.setBounds(235,240, 430, 50);
        pan4.setBounds(235,310, 430, 50);
        pan5.setBounds(330,380,100, 50);
        pan6.setBounds(480,380,100, 50);


        frame.add(pan1);
        frame.add(pan2);
        frame.add(pan3);
        frame.add(pan4);
        frame.add(pan5);
        frame.add(pan6);

        //登录
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logCheck();
            }
        });
        //注册
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser.addUser();
            }
        });

        frame.setBounds(500, 150, 900, 650);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //获取输入文本框的值，传到数据库
    public static void logCheck() {
        String userId = textId.getText().toString();
        String Password = new String(passwordfield.getPassword());
        if(stringutil.isEmpty(userId)) {
            JOptionPane.showMessageDialog(null, "账号不能为空");
            return;
        }
        if(stringutil.isEmpty(Password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }
        Connection con = null;
        dbutil dbutil = new dbutil();
        userDao userdao = new userDao();
        User userMessage = new User();
        userMessage.setUserId(userId);
        userMessage.setPassword(Password);
        String box = (String)comBox.getSelectedItem();//获取下拉框的文字
        if(box.equals("普通用户")) {
            try {
                con = dbutil.getCon();
                User currentUser = userdao.login(con,userMessage);
                if(currentUser != null ) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    userName = currentUser.getUsername();
                    id = currentUser.getUserId();
                    mainNeedwindow ct=new mainNeedwindow();
                    ct.setVisible(true);
                    frame.dispose();//关闭当前界面
//                    userNeed.userNeed();
//                    mainNeedwindow.mainNeedwindow();
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                }
            }catch(Exception evt) {
                evt.printStackTrace();
            }
        }
        adminDao admindao = new adminDao();
        Admin adminMessage = new Admin();
        adminMessage.setAdminId(userId);
        adminMessage.setPassword(Password);

        if(box.equals("管理员")) {
            try {
                con = dbutil.getCon();
                Admin currentUser = admindao.adminLogin(con,adminMessage);
                if(currentUser != null ) {
                    JOptionPane.showMessageDialog(null, "管理员登录成功");
                    adminName = currentUser.getAdminname();
                    id = currentUser.getAdminId();
                    frame.dispose();//关闭当前界面

                    adminwindow ct=new adminwindow();
                    ct.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                }
            }catch(Exception evt) {
                evt.printStackTrace();
            }
        }
    }
    // 比对成功后，获取数据库中的用户名
    public static String userName() {
        return userName;
    }
    public static String adminName() {
        return adminName;
    }
    public static String id() {
        return id;
    }
}