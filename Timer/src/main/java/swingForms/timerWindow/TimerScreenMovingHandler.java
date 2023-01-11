package swingForms.timerWindow;

import lombok.Getter;
import swingForms.CreateNewTimerForm;
import swingForms.MainForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimerScreenMovingHandler {

    private static final TimerScreenMovingHandler timerScreenMovingHandler = new TimerScreenMovingHandler();

    private JFrame timerFormJFrame;
    private Color timerFormBackgroundColor;
    private MouseListener timerMovingMouseListener;

    private JFrame screenMovingFrame;
    @Getter
    private static volatile boolean isWorking;
    private static TimerScreenMovingThread timerScreenMovingThread;
    private volatile boolean isTimerWindowCaptured;


    public static TimerScreenMovingHandler getInstance() {
        return timerScreenMovingHandler;
    }

    private TimerScreenMovingHandler() {

        screenMovingFrame = new JFrame();
        screenMovingFrame.setLayout(new GridBagLayout());
        screenMovingFrame.setUndecorated(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenMovingFrame.setSize(dimension.width, dimension.height);
        screenMovingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        screenMovingFrame.setBackground(new Color(240, 240,240, 100));
        screenMovingFrame.setLocation(0, 0);

        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Tahoma", 1, 36));
        timerLabel.setForeground(new Color(10, 10,10));
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setText("Перетяните окно таймера в нужное место");
        timerLabel.setMaximumSize(new java.awt.Dimension(dimension.width, dimension.height));
        timerLabel.setMinimumSize(new java.awt.Dimension(dimension.width, dimension.height));
        timerLabel.setPreferredSize(new java.awt.Dimension(dimension.width, dimension.height));
        timerLabel.setOpaque(false);

        Container contentPane = screenMovingFrame.getContentPane();
        contentPane.setBackground(new Color(255,255,255,0));
        contentPane.add(timerLabel);

        screenMovingFrame.pack();
    }


    protected void startTimerWindowMove(TimerForm timerForm) {

        if(timerScreenMovingThread != null) { stopTimerWindowMove(); }
        timerScreenMovingThread = new TimerScreenMovingThread();

        timerFormJFrame = timerForm.getJFrame();
        timerFormBackgroundColor = timerFormJFrame.getBackground();

        timerMovingMouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                isTimerWindowCaptured = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isTimerWindowCaptured = false;
            }
        };
        timerForm.getJFrame().addMouseListener(timerMovingMouseListener);

        screenMovingFrame.setVisible(true);

        CreateNewTimerForm createNewTimerForm = WindowsCreator.getInstance().getCurrentCreateNewTimerForm();
        if(createNewTimerForm != null && createNewTimerForm.isDisplayable()) {
            createNewTimerForm.setVisible(true);
        }

        MainForm mainForm = WindowsCreator.getInstance().getCurrentMainForm();
        mainForm.setVisible(true);

        timerForm.getJFrame().setVisible(true);

        isWorking = true;
        timerScreenMovingThread.start();
    }


    protected void stopTimerWindowMove() {
        isWorking = false;

        timerScreenMovingThread = null;

        timerFormJFrame.removeMouseListener(timerMovingMouseListener);
        timerFormJFrame.setBackground(timerFormBackgroundColor);

        screenMovingFrame.setVisible(false);
    }


    private class TimerScreenMovingThread extends Thread {

        @Override
        public void run() {

            while (isWorking) {
                if(isTimerWindowCaptured) {
                    Point mousePosition = screenMovingFrame.getMousePosition();
                    System.out.println(mousePosition.x + " - " + mousePosition.y);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
