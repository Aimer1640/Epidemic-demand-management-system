package Need;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getNew extends JFrame {

    private JPanel contentPane;
    private File file1 = null;
    /**
     * Launch the application.
     */
    JLabel timer;
    public void one( ) {
//        super("时钟");
        Container c = getContentPane();
        timer = new JLabel("时钟", JLabel.CENTER);
        c.add(timer);
        timer.setFont(new Font("宋体", Font.BOLD, 170));
        setSize(1000, 250);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    getNew frame = new getNew();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        one td = new one( );
        new myTimer(td).start( );

    }
    static class myTimer extends Thread {
        one tb;
        myTimer(one tb) {
            this.tb = tb;
        }
        public void run( ) {
            while (true) {
                SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
                String s = dateformat.format(new Date( ));
                tb.timer.setText(s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace( );
                }
            }
        }
    }
    /**
     * Create the frame.
     */

    public getNew() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 1117, 565);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 50, 1050, 400);
        contentPane.add(textArea);

        JButton button = new JButton("获取今日通知");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {   //以缓冲区字符流方式读取文件内容
                    file1 = new File("D:/work/work/src/main/java/Need/today.txt");
                    FileReader fr = new FileReader(file1);
                    BufferedReader br = new BufferedReader(fr);
                    String aline;
                    while ((aline=br.readLine()) != null)//按行读取文本
                        textArea.append(aline+"\r\n");
                    fr.close();
                    br.close();
                }catch (IOException ioe){
                    System.out.println(ioe);
                }
            }
        });
        button.setBounds(350, 462, 191, 39);
        contentPane.add(button);

        JButton btnNewButton = new JButton("返回");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainNeedwindow ct=new mainNeedwindow();
                ct.setVisible(true);
                dispose();//关闭当前界面
            }
        });
        btnNewButton.setBounds(550, 464, 168, 39);
        contentPane.add(btnNewButton);
    }



}
