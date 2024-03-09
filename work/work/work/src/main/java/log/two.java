package log;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Need.mainNeedwindow;
import User.User;
import log.three;
import Dao.userDao;
import util.dbutil;

public class two extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lb_backgroud;// 背景
    private JLabel label = new JLabel("目标管理系统");
    private JLabel username = new JLabel("用户名：");
    private JLabel password = new JLabel("密    码：");
    private JLabel validcode = new JLabel("验证码：");
    private JPanel jp1 = new JPanel();// 标题面板
    private JTextField jtf_user = new JTextField();
    private JPasswordField jpf_pwd = new JPasswordField();
    private JTextField jtf_code = new JTextField();
    private JButton btn_login = new JButton("登录");
    private JButton btn_regist = new JButton("注册");
    private three vcode;

    public two() {
        showUI();
        BtnLoginAddActionListener();
        setBackgroudImage();
    }

    /**
     * 初始化登录窗体背景
     */
    private void setBackgroudImage() {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("images/beijing1.jpg"); // 添加图片
        lb_backgroud = new JLabel(img);
        this.getLayeredPane().add(lb_backgroud, new Integer(Integer.MIN_VALUE));
        lb_backgroud.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

    private void showUI() {
        this.setTitle("南华大学目标管理系统");
        this.setSize(450, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        Container c = this.getContentPane();
        c.setLayout(null);

        label.setFont(new Font("宋体", 0, 30));
        // jp1.setBackground(Color.gray);
        jp1.add(label);
        jp1.setBounds(100, 30, 250, 45);
        jp1.setOpaque(false);
        c.add(jp1);

        username.setBounds(80, 90, 60, 40);
        password.setBounds(80, 140, 60, 40);
        validcode.setBounds(80, 190, 60, 40);

        jtf_user.setBounds(140, 90, 180, 40);
        jtf_user.setOpaque(false);
        jpf_pwd.setBounds(140, 140, 180, 40);
        jpf_pwd.setOpaque(false);
        jtf_code.setBounds(140, 190, 100, 40);

        btn_login.setBounds(120, 250, 80, 40);
        setJButton(btn_login);
        btn_regist.setBounds(220, 250, 80, 40);
        setJButton(btn_regist);

        c.add(username);
        c.add(password);
        c.add(validcode);
        c.add(jtf_user);
        c.add(jpf_pwd);
        c.add(jtf_code);
        vcode = new three();
        vcode.setBounds(240, 190, 80, 40);
        c.add(vcode);
        c.add(btn_login);
        c.add(btn_regist);

        this.setVisible(true);

    }

    /**
     * 为了适应各种不同尺寸大小的窗口都能显示在屏幕中间位置，相当于setLocationRelativeTo(null) private void
     * setCenter(){ Toolkit toolkit = Toolkit.getDefaultToolkit(); Dimension d =
     * toolkit.getScreenSize(); int x = (int)(d.getWidth()-getWidth())/2; int y
     * = (int)(d.getHeight()-getHeight())/2; this.setLocation(x, y); }
     */

    /**
     * 给登录按钮添加监听
     */
    public void BtnLoginAddActionListener() {
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isValidCodeRight()) {
                    JOptionPane.showMessageDialog(two.this, "验证码错误！");
                }
                if (isValidCodeRight()) {

                    String userName = jtf_user.getText();
                    String passWord = String.valueOf(jpf_pwd.getPassword());
                    User user = new User();
                    user.setUsername(userName);
                    user.setPassword(passWord);
                    Connection con = null;
                    dbutil dbutil = new dbutil();
                    userDao userdao = new userDao();
                    User userMessage = new User();
                    Boolean flag = false;
                    try {
                        con = dbutil.getCon();
                        User currentUser = userdao.login(con,userMessage);
                        if(currentUser != null ) {
                            JOptionPane.showMessageDialog(null, "登录成功");
                            userName = currentUser.getUsername();
//                            id = currentUser.getUserId();
                            mainNeedwindow ct=new mainNeedwindow();
                            ct.setVisible(true);
//                            frame.dispose();//关闭当前界面
//                    userNeed.userNeed();
//                    mainNeedwindow.mainNeedwindow();
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                        }
                    }catch(Exception evt) {
                        evt.printStackTrace();
                    }
//                    try {
//                        flag = userDao.login(con,userMessage);
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }

                    // 判断
                    if (!flag) {
                        JOptionPane.showMessageDialog(two.this,
                                "用户名或密码错误，请重输！");
                    } else {
                        System.out.println("登录成功！！！");
                        mainNeedwindow ct=new mainNeedwindow();
                        ct.setVisible(true);
//                        frame.dispose();
                    }
                }
            }
        });
    }

    /**
     * 给注册按钮添加监听
     */
    public void BtnRegistAddActionListener() {
        btn_regist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * 设置按钮风格：透明
     *
     * @param btn
     */
    private void setJButton(JButton btn) {
        btn.setBackground(new Color(102, 0, 204));// 紫色
        btn.setFont(new Font("Dialog", Font.BOLD, 24));
        btn.setOpaque(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * 验证码的校验
     *
     * @return
     */
    public boolean isValidCodeRight() {

        if (jtf_code == null) {
            return false;
        }
        if (vcode == null) {
            return true;
        }
        if (vcode.getCode().equals(jtf_code.getText())) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        new two();


    }
}
