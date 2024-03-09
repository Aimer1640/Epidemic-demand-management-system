package Need;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class one extends JFrame {
    JLabel timer;
    public one( ) {
//        super("时钟");
        Container c = getContentPane( );
        timer = new JLabel("时钟",JLabel.CENTER);
        c.add(timer);
        timer.setFont(new Font("宋体", Font.BOLD, 170));
        setSize(1000, 250);
        setBounds(500, 610, 1117, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        one td = new one( );
        new myTimer(td).start( );
    }
}

class myTimer extends Thread {
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

