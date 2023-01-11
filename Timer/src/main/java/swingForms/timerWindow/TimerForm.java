package swingForms.timerWindow;

import lombok.Getter;
import lombok.Setter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


@Setter
public class TimerForm {

    @Getter
    private JFrame jFrame;
    private JLabel timerLabel;
    private Properties properties;
    private String timerSetupPropertyFile;
    private TimerThread currentTimerThread = null;

    private boolean isMovingModeActive;
    private MouseAdapter windowMovingMouseAdapter;

    private int hourValue;
    private int minutValue;
    private int secondValue;
    private Integer timeAfterTimerStoped = null;

    private boolean isMainSoundOn;
    private int soundVolume = 80;
    private String mainSoundFilePath = "";
    private boolean isSecondSoundOn;
    private String secondSoundFilePath = "";

    private boolean isWindowSettingsCustom;
    private int windowWidth;
    private int windowHeight;
    private int windowLocationX;
    private int windowLocationY;

    private Color backgroundColor;
    private int backgroundTransparency;      //(0-256) 0-прозрачный
    private Color timerColor;
    private Font timerFont;



    public TimerForm(String timerSetupPropertyFile, boolean isNewTimerForm, WindowsCreator creator) {

        this.timerSetupPropertyFile = timerSetupPropertyFile;

        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/" + timerSetupPropertyFile);
            properties.load(inputStream);
        } catch (IOException e) { e.printStackTrace(); }

        //Получение настроек по умолчанию для всех полей
        setDefaultTimerSettings();

        //Получение кастомных настроек для некоторых полей
        boolean isTimerSettingsSaved = properties.getProperty("timer_window.save_settings.is_saved")
                .trim().equals("true");
        if(!isNewTimerForm && isTimerSettingsSaved) { setCustomTimerSettingsFromMemory(); }

        //Инициализация окна таймера
        initComponents();

    }



    /*
    ========================================================================================
    Методы инициализации и отрисовки компонентов
    ========================================================================================
     */
    private void initComponents() {

        jFrame = new JFrame();
        jFrame.setLayout(new GridBagLayout());
        jFrame.setUndecorated(true);
        jFrame.setSize(windowWidth, windowHeight);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        timerLabel = new JLabel();
        timerLabel.setFont(timerFont);
        timerLabel.setForeground(timerColor);
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setText(hourValue + " : " + minutValue + " : " + secondValue);
        timerLabel.setMaximumSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setMinimumSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setPreferredSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setOpaque(false);

        Container contentPane = jFrame.getContentPane();
        contentPane.setBackground(new Color(255,255,255,0));
        contentPane.add(timerLabel);
        jFrame.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(),backgroundColor.getBlue(), backgroundTransparency));
        jFrame.setLocation(windowLocationX, windowLocationY);

        jFrame.pack();
    }


    public void rebuildJFrame(boolean resetLocation) {

        int positionX;
        int positionY;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if(resetLocation) {
            positionX = dimension.width - windowWidth;
            positionY = dimension.height - windowHeight;
        }
        else  {
            positionX = jFrame.getX();
            positionY = jFrame.getY();
        }

        jFrame.dispose();
        jFrame = new JFrame();
        jFrame.setLayout(new GridBagLayout());
        jFrame.setUndecorated(true);
        jFrame.setSize(windowWidth, windowHeight);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        timerLabel = new JLabel();
        timerLabel.setFont(timerFont);
        timerLabel.setForeground(timerColor);
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setMaximumSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setMinimumSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setPreferredSize(new java.awt.Dimension(windowWidth, windowHeight));
        timerLabel.setOpaque(false);

        //Запись значений времени ЧЧ:ММ:СС в строки
        String hoursValueText = String.valueOf(hourValue);
        String minutsValueText = String.valueOf(minutValue);
        String secondsValueText = String.valueOf(secondValue);

        if(hourValue < 10) { hoursValueText = "0" + hoursValueText; }
        if(minutValue < 10) { minutsValueText = "0" + minutsValueText; }
        if(secondValue < 10) { secondsValueText = "0" + secondsValueText; }
        timerLabel.setText(hoursValueText + ":" + minutsValueText + ":" + secondsValueText);

        Container contentPane = jFrame.getContentPane();
        contentPane.setBackground(new Color(255,255,255,0));
        contentPane.add(timerLabel);
        jFrame.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(),backgroundColor.getBlue(), backgroundTransparency));
        jFrame.setLocation(windowLocationX, windowLocationY);


        jFrame.setLocation(positionX, positionY);

        jFrame.pack();
    }


    public void setVisible(boolean isVisible) { jFrame.setVisible(isVisible); }


    /*
    ========================================================================================
    Методы для управления таймером из других классов
    ========================================================================================
    */
    public void startTimer() {
        if(timeAfterTimerStoped == null) {
            currentTimerThread = new TimerThread(hourValue * 3600 + minutValue * 60 + secondValue);
        }
        else {
            currentTimerThread = new TimerThread(timeAfterTimerStoped.intValue());
        }
        currentTimerThread.start();
    }

    public void stopTimer() {
        if(currentTimerThread != null) { currentTimerThread.stopTimer(); }
        currentTimerThread = null;
    }

    public void resetTimer() {
        if(currentTimerThread != null) { currentTimerThread.stopTimer(); }
        timeAfterTimerStoped = null;
        rebuildJFrame(false);
        jFrame.setVisible(true);
    }


    public boolean isMovingModeActive() {
        return isMovingModeActive;
    }

    public void enableMovingMode() {

        windowMovingMouseAdapter = new MouseAdapter() {
            int xPosition;
            int yPosition;

            @Override
            public void mousePressed(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                jFrame.setLocation(e.getXOnScreen() - xPosition, e.getYOnScreen() - yPosition);
            }
        };

        jFrame.addMouseListener(windowMovingMouseAdapter);
        jFrame.addMouseMotionListener(windowMovingMouseAdapter);
        isMovingModeActive = true;
    }

    public void disableMovingMode() {
        jFrame.removeMouseListener(windowMovingMouseAdapter);
        jFrame.removeMouseMotionListener(windowMovingMouseAdapter);
        isMovingModeActive = false;
    }


    /*
     ========================================================================================
    Методы изменения и сохранения характеристик отрисовки таймера
    ========================================================================================
    */
    private void setDefaultTimerSettings() {

        hourValue = 0;
        minutValue = 0;
        secondValue = 10;

        isMainSoundOn = false;
        soundVolume = 70;
        mainSoundFilePath = null;
        isSecondSoundOn = false;
        secondSoundFilePath = null;

        isWindowSettingsCustom = false;
        windowWidth = 400;
        windowHeight = 200;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        windowLocationX = dimension.width - windowWidth - 10;
        windowLocationY = dimension.height - windowHeight - 10;

        backgroundColor = new Color(240, 240, 240);
        backgroundTransparency = 180;      //(0-256) 0-прозрачный
        timerColor = new Color(50, 50, 50);
        timerFont = new java.awt.Font("Tahoma", 0, 60);
    }


    private void setCustomTimerSettingsFromMemory() {

        hourValue = Integer.parseInt(properties.getProperty("timer_window.timer_value.hours"));
        minutValue = Integer.parseInt(properties.getProperty("timer_window.timer_value.minuts"));
        secondValue = Integer.parseInt(properties.getProperty("timer_window.timer_value.seconds"));

        //Sound Editor
        isMainSoundOn = properties.getProperty("timer_window.sound_editor.is_sound_activated").trim().equals("true");
        if(isMainSoundOn) {
            soundVolume =  Integer.parseInt(properties.getProperty("timer_window.sound_editor.volume_value"));

            String mainSoundPath = properties.getProperty("timer_window.sound_editor.first_sound_path");

            if(Files.isReadable(Path.of(mainSoundPath))) {
                this.mainSoundFilePath = mainSoundPath;
            }

            isSecondSoundOn = properties.getProperty("timer_window.sound_editor.is_second_sound_activated").trim().equals("true");
            if(isSecondSoundOn) {
                String secondSoundPath = properties.getProperty("timer_window.sound_editor.second_sound_path");
                if(Files.isReadable(Path.of(secondSoundPath))) {
                    this.secondSoundFilePath = secondSoundPath;
                }
            }
        }

        //Window Editor
        isWindowSettingsCustom = properties.getProperty("timer_window.window_editor.is_activated").trim().equals("true");
        if(isWindowSettingsCustom) {
            windowWidth = Integer.parseInt(properties.getProperty("timer_window.window_editor.size_x"));
            windowHeight = Integer.parseInt(properties.getProperty("timer_window.window_editor.size_y"));
            backgroundTransparency = Integer.parseInt(properties.getProperty("timer_window.window_editor.transparency_value"));

            backgroundColor = new Color(Integer.parseInt(properties.getProperty("timer_window.window_editor.background_color.r")),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.background_color.g")),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.background_color.b")));

            timerColor = new Color(Integer.parseInt(properties.getProperty("timer_window.window_editor.timer_color.r")),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.timer_color.g")),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.timer_color.b")));

            timerFont = new java.awt.Font(properties.getProperty("timer_window.window_editor.timer_font.font_name"),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.timer_font.style")),
                    Integer.parseInt(properties.getProperty("timer_window.window_editor.timer_font.font_size")));
        }
    }


    public void saveTimerSettingsToMemory() {

        properties.setProperty("timer_window.save_settings.is_saved", "true");

        properties.setProperty("timer_window.timer_value.hours", String.valueOf(hourValue));
        properties.setProperty("timer_window.timer_value.minuts", String.valueOf(minutValue));
        properties.setProperty("timer_window.timer_value.seconds", String.valueOf(secondValue));

        properties.setProperty("timer_window.sound_editor.is_sound_activated", isMainSoundOn == true ? "true" : "false");
        if(isMainSoundOn == true) {
            properties.setProperty("timer_window.sound_editor.volume_value", String.valueOf(soundVolume));
            properties.setProperty("timer_window.sound_editor.first_sound_path", mainSoundFilePath.trim());
            properties.setProperty("timer_window.sound_editor.is_second_sound_activated", isSecondSoundOn == true ? "true" : "false");
            if (isSecondSoundOn == true) {
                properties.setProperty("timer_window.sound_editor.second_sound_path", secondSoundFilePath.trim());
            }
        }

        properties.setProperty("timer_window.window_editor.is_activated", isWindowSettingsCustom == true ? "true" : "false");
        properties.setProperty("timer_window.window_editor.size_x", String.valueOf(windowWidth));
        properties.setProperty("timer_window.window_editor.size_y", String.valueOf(windowHeight));
        properties.setProperty("timer_window.window_editor.transparency_value", String.valueOf(backgroundTransparency));

        properties.setProperty("timer_window.window_editor.background_color.r", String.valueOf(backgroundColor.getRed()));
        properties.setProperty("timer_window.window_editor.background_color.g", String.valueOf(backgroundColor.getGreen()));
        properties.setProperty("timer_window.window_editor.background_color.b", String.valueOf(backgroundColor.getBlue()));

        properties.setProperty("timer_window.window_editor.timer_color.r", String.valueOf(timerColor.getRed()));
        properties.setProperty("timer_window.window_editor.timer_color.g", String.valueOf(timerColor.getGreen()));
        properties.setProperty("timer_window.window_editor.timer_color.b", String.valueOf(timerColor.getBlue()));

        properties.setProperty("timer_window.window_editor.timer_font.font_name", String.valueOf(timerFont.getFontName()));
        properties.setProperty("timer_window.window_editor.timer_font.style", String.valueOf(timerFont.getStyle()));
        properties.setProperty("timer_window.window_editor.timer_font.font_size", String.valueOf(timerFont.getSize()));

        try {
            properties.store(new FileOutputStream(timerSetupPropertyFile), null);
        } catch (IOException e) { e.printStackTrace(); }
    }



    /*
    ========================================================================================
    Встроенный класс. Отвечает за поток управления таймером.
    ========================================================================================
     */
    private class TimerThread extends Thread {

        private int timeInSeconds;
        private boolean isNegativeTimeOn = true;
        private volatile boolean isTimerActive = false;

        public TimerThread(int timeInSeconds) {

            this.timeInSeconds = timeInSeconds;
            isTimerActive = true;
            timeAfterTimerStoped = null;
        }

        private TimerThread(boolean isMainSoundOn, boolean isSecondSoundOn) {
            isNegativeTimeOn = false;
            TimerForm.this.isMainSoundOn = isMainSoundOn;
            TimerForm.this.isSecondSoundOn = isSecondSoundOn;

            if(isMainSoundOn && isSecondSoundOn) {
                this.timeInSeconds = 4;
                isTimerActive = true;
                timeAfterTimerStoped = null;
            }
            else if(isMainSoundOn) {
                this.timeInSeconds = 2;
                isTimerActive = true;
                timeAfterTimerStoped = null;
            }
        }

        @Override
        public void run() {

            while(isTimerActive) {

                timeInSeconds--;
                int localTimeInSeconds;
                int localHoursValue;
                int localMinutsValue;
                int localSecondsValue;

                if(timeInSeconds >= 0) {
                    localTimeInSeconds = timeInSeconds;
                    if(timeInSeconds == 0 && isMainSoundOn) {
                        playAudioFile(mainSoundFilePath);
                    }
                    else if(isSecondSoundOn && localTimeInSeconds > 0 && localTimeInSeconds <= 3 ) {
                        playAudioFile(secondSoundFilePath);
                    }
                }
                else {
                    jFrame.setBackground(new Color(255, 102, 102, backgroundTransparency));
                    localTimeInSeconds = -1 * timeInSeconds;
                }


                localHoursValue = localTimeInSeconds / 3600;
                localTimeInSeconds = localTimeInSeconds % 3600;

                localMinutsValue = localTimeInSeconds / 60;
                localTimeInSeconds = localTimeInSeconds % 60;

                localSecondsValue = localTimeInSeconds;

                //Запись значений времени ЧЧ:ММ:СС в строки
                String localHoursValueText = String.valueOf(localHoursValue);
                String localMinutsValueText = String.valueOf(localMinutsValue);
                String localSecondsValueText = String.valueOf(localSecondsValue);

                if(localHoursValue < 10) { localHoursValueText = "0" + localHoursValueText; }
                if(localMinutsValue < 10) { localMinutsValueText = "0" + localMinutsValueText; }
                if(localSecondsValue < 10) { localSecondsValueText = "0" + localSecondsValueText; }

                if(timeInSeconds >= 0) {
                    timerLabel.setText(localHoursValueText + ":" + localMinutsValueText + ":" + localSecondsValueText);
                }
                else {
                    timerLabel.setText("- " + localHoursValueText + ":" + localMinutsValueText + ":" + localSecondsValueText);
                }

                if(timeInSeconds <= 0 && !isNegativeTimeOn) {
                    isTimerActive = false;
                    continue;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }

        private void stopTimer() {
            timeAfterTimerStoped = timeInSeconds;
            isTimerActive = false;
        }


    }



    private void playAudioFile(String audioFilePath) {

        AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);

        AudioInputStream audioInputStream = null;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/" + audioFilePath);
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        SourceDataLine sourceDataLine = null;
        try {
            //Init output device sourceDataLine for output audio file
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            //Volume control
            FloatControl gainControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (0.35 * soundVolume - 30.0));
        } catch (LineUnavailableException e) { e.printStackTrace(); }


        if(audioInputStream != null && sourceDataLine != null) {
            new PlayAudioThread(audioInputStream, sourceDataLine).start();
        }
    }


    private class PlayAudioThread extends Thread {

        private byte[] tempBuffer = new byte[10000];
        private AudioInputStream audioInputStream;
        private SourceDataLine sourceDataLine;

        public PlayAudioThread(AudioInputStream audioInputStream, SourceDataLine sourceDataLine) {
            this.audioInputStream = audioInputStream;
            this.sourceDataLine = sourceDataLine;
        }

        @Override
        public void run() {

            try {
                int cnt;

                //returns -1 for empty stream
                while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                    if(cnt > 0) { sourceDataLine.write(tempBuffer, 0 ,cnt); }
                }

                sourceDataLine.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void startSoundSignalTest(boolean isMainSoundOn, boolean isSecondSoundOn, String mainSoundFilePath, String secondSoundFilePath, JButton soundCheckButton) {
        soundCheckButton.setEnabled(false);

        this.mainSoundFilePath = mainSoundFilePath;
        this.secondSoundFilePath = secondSoundFilePath;

        TimerThread testTimerThread = new TimerThread(isMainSoundOn, isSecondSoundOn);
        testTimerThread.start();

        soundCheckButton.setEnabled(true);
    }
}
