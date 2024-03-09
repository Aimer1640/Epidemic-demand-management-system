package Need;

import log.addUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import log.login;
import log.addUser;

public class mainNeedwindow extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainNeedwindow frame = new mainNeedwindow();
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
    public mainNeedwindow() {
        setBackground(SystemColor.activeCaption);
        setForeground(SystemColor.activeCaption);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1117, 765);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("用户需求");
        menu.setFont(new Font("楷体", Font.BOLD, 22));
        menu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
            }
        });
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("查看需求");

        menuItem.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNeed ct=new showNeed();
                ct.setVisible(true);
            }
        });
        menu.add(menuItem);

        JMenuItem menuItem_1 = new JMenuItem("查询需求");
        menuItem_1.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findNeed ct=new findNeed();
                ct.setVisible(true);
            }
        });
        menu.add(menuItem_1);

        JMenuItem menuItem_4 = new JMenuItem("发布需求");
        menuItem_4.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNeed nd=new addNeed();
                nd.setVisible(true);
            }
        });
        menu.add(menuItem_4);

        JMenuItem menuItem_5 = new JMenuItem("修改需求");
        menuItem_5.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateNeed nd=new updateNeed();
                nd.setVisible(true);
            }
        });
        menu.add(menuItem_5);

        JMenu menu_1 = new JMenu("账号操作");
        menu_1.setFont(new Font("楷体", Font.BOLD, 22));
        menuBar.add(menu_1);

        JMenuItem menuItem_2 = new JMenuItem("注册账号");
        menuItem_2.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser.addUser();
            }
        });
        menu_1.add(menuItem_2);

        JMenuItem menuItem_6 = new JMenuItem("退出登录");
        menuItem_6.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login.login();
            }
        });
        menu_1.add(menuItem_6);

        JMenu menu_2 = new JMenu("关于");
        menu_2.setFont(new Font("楷体", Font.BOLD, 22));
        menuBar.add(menu_2);

        JMenuItem menuItem_3 = new JMenuItem("查看公告");
        menuItem_3.setFont(new Font("楷体", Font.PLAIN, 18));
        menuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNew ct=new getNew();
                one td = new one( );
                new getNew.myTimer(td).start( );
                ct.setVisible(true);
            }
        });
        menu_2.add(menuItem_3);
        contentPane = new JPanel();
        ImageIcon im = new ImageIcon("D:/work/src/image/img1.jpg");
        contentPane.setForeground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 4));
        setContentPane(contentPane);

        JButton btnNewButton = new JButton("查看需求");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                showNeed.showNeed();
                showNeed ct=new showNeed();
                ct.setVisible(true);
            }
        });
        btnNewButton.setBounds(217, 169, 183, 85);
        btnNewButton.setFont(new Font("楷体", Font.PLAIN, 23));


        JButton btnNewButton_1 = new JButton("发布需求");
        btnNewButton_1.setBounds(217, 420, 183, 87);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNeed nd=new addNeed();
                nd.setVisible(true);
            }
        });
        btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 23));

        JButton btnNewButton_2 = new JButton("查询需求");

        btnNewButton_2.setBounds(467, 168, 191, 86);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findNeed nd=new findNeed();
                nd.setVisible(true);

            }
        });
        btnNewButton_2.setFont(new Font("楷体", Font.PLAIN, 23));

        JButton btnNewButton_3 = new JButton("查看公告");
        btnNewButton_3.setBounds(467, 422, 191, 83);
        btnNewButton_3.setFont(new Font("楷体", Font.PLAIN, 23));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNew ct=new getNew();
                one td = new one( );
                new getNew.myTimer(td).start( );
                ct.setVisible(true);
            }
        });

        JButton btnNewButton_5 = new JButton("修改需求");
        btnNewButton_5.setBounds(723, 168, 183, 86);
        btnNewButton_5.setFont(new Font("楷体", Font.PLAIN, 23));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateNeed nd=new updateNeed();
                nd.setVisible(true);
            }
        });
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-95, -127, 1482, 806);
        lblNewLabel.setIcon(new ImageIcon("D:\\work\\work\\src\\main\\java\\image\\img2.jpg"));
//        lblNewLabel.setBounds(-95, -127, 1482, 806);
//        lblNewLabel.setIcon(new ImageIcon("D:\\work\\src\\main\\java\\image\\img2.jpg"));
        contentPane.setLayout(null);
        contentPane.add(btnNewButton_1);
        contentPane.add(btnNewButton);
        contentPane.add(btnNewButton_3);
        contentPane.add(btnNewButton_2);
        contentPane.add(btnNewButton_5);

        JButton btnNewButton_4 = new JButton("退出登录");
        btnNewButton_4.setFont(new Font("楷体", Font.PLAIN, 23));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();//关闭当前界面
            }
        });
        btnNewButton_4.setBounds(723, 422, 183, 83);
        contentPane.add(btnNewButton_4);
        contentPane.add(lblNewLabel);
    }
}
