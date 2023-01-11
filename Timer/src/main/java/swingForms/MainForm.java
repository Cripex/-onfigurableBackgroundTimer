package swingForms;

import lombok.Setter;
import swingForms.timerWindow.TimerForm;
import swingForms.timerWindow.WindowsCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class MainForm extends JFrame {

//    private static final String timerSetupPropertyFile = "src/main/resources/saved_timer.properties";
    private static final String timerSetupPropertyFile = "saved_timer.properties";
    private static Properties properties = null;
    @Setter
    private static TimerForm currentTimerForm;

    private static CreateNewTimerForm createNewWindow = null;

    private javax.swing.JButton createNewButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton locationEditButton;
    private javax.swing.JLabel notificationLabel;
    private javax.swing.JButton resetTimerButton;
    private javax.swing.JButton startTimerButton;
    private javax.swing.JButton stopTimerButton;

    private volatile boolean isButtonsEnabled = false;


    public MainForm(WindowsCreator creator) {

        //Получение корневой папки, в которой расположены файл .jar (архив) и папка с ресурсами
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String textURL = url.getPath();
        int lastIndexOfSlash = textURL.lastIndexOf("/");
        String parentFolderAbsolutePath = textURL.substring(0, lastIndexOfSlash);
        System.out.println(parentFolderAbsolutePath);

        properties = initTimerProperties();

        initComponents();
        checkSavedTimer();
        setVisible(true);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) {

                TimerForm timerForm = WindowsCreator.getInstance().getCurrentTimerForm();

                //Если кнопки не активны и закрыта панель создания таймера, но окно таймера открыто
                if(!isButtonsEnabled && (createNewWindow == null || !createNewWindow.isDisplayable()) &&
                        timerForm != null && timerForm.getJFrame().isDisplayable() && timerForm.getJFrame().isVisible()) {
                    for(Component component : jPanel1.getComponents()) { component.setEnabled(true); }
                    isButtonsEnabled = true;
                }

                //Если кнопки не активны и закрыты все окна
                else if(!isButtonsEnabled && (createNewWindow == null || !createNewWindow.isDisplayable()) &&
                        (timerForm == null || !timerForm.getJFrame().isDisplayable())) {
                    notificationLabel.setText("Для начала работы создайте новый таймер!");
                    notificationLabel.setBackground(new java.awt.Color(255,102,102));
                }

                //Если кнопки активны, панель созания таймера и окно таймера закрыты
                else if(isButtonsEnabled && (timerForm == null || !timerForm.getJFrame().isDisplayable()) &&
                        (createNewWindow == null || !createNewWindow.isDisplayable())) {
                    disableButtons("Для начала работы создайте новый таймер!", new java.awt.Color(255,102,102));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }


    private void checkSavedTimer() {

        //if saved timer exist
        if(properties.getProperty("timer_window.save_settings.is_saved").trim().equals("true")) {
            currentTimerForm = WindowsCreator.getInstance().getNewTimerForm(timerSetupPropertyFile, false);

            currentTimerForm.rebuildJFrame(true);
            currentTimerForm.setVisible(true);

            this.enableButtons("Для управления воспользуйтесь кнопками справа", new java.awt.Color(255, 255, 153));
            MainForm.setCurrentTimerForm(currentTimerForm);

            return;
        }

        disableButtons("Сохранений не найдено! Создайте новый таймер", new java.awt.Color(255,102,102));
    }


    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        createNewButton = new javax.swing.JButton();
        locationEditButton = new javax.swing.JButton();
        notificationLabel = new javax.swing.JLabel();
        resetTimerButton = new javax.swing.JButton();
        startTimerButton = new javax.swing.JButton();
        stopTimerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(910, 45));
        setMinimumSize(new java.awt.Dimension(910, 45));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(910, 45));
        jPanel1.setMinimumSize(new java.awt.Dimension(910, 45));
        jPanel1.setPreferredSize(new java.awt.Dimension(910, 45));

        createNewButton.setBackground(new java.awt.Color(204, 204, 204));
        createNewButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createNewButton.setText("Создать новый");
        createNewButton.setMaximumSize(new java.awt.Dimension(145, 25));
        createNewButton.setMinimumSize(new java.awt.Dimension(145, 25));
        createNewButton.setPreferredSize(new java.awt.Dimension(145, 25));
        createNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewButtonActionPerformed(evt);
            }
        });

        locationEditButton.setBackground(new java.awt.Color(204, 204, 204));
        locationEditButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        locationEditButton.setText("Переместить");
        locationEditButton.setMaximumSize(new java.awt.Dimension(130, 25));
        locationEditButton.setMinimumSize(new java.awt.Dimension(130, 25));
        locationEditButton.setPreferredSize(new java.awt.Dimension(130, 25));
        locationEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationEditButtonActionPerformed(evt);
            }
        });

        notificationLabel.setBackground(new java.awt.Color(255, 102, 102));
        notificationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notificationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notificationLabel.setText(" Чтобы закрыть приложение остановите таймер!");
        notificationLabel.setMaximumSize(new java.awt.Dimension(330, 25));
        notificationLabel.setMinimumSize(new java.awt.Dimension(330, 25));
        notificationLabel.setOpaque(true);
        notificationLabel.setPreferredSize(new java.awt.Dimension(330, 25));

        resetTimerButton.setBackground(new java.awt.Color(102, 153, 255));
        resetTimerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resetTimerButton.setText("Сброс");
        resetTimerButton.setMaximumSize(new java.awt.Dimension(75, 25));
        resetTimerButton.setMinimumSize(new java.awt.Dimension(75, 25));
        resetTimerButton.setPreferredSize(new java.awt.Dimension(75, 25));
        resetTimerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTimerButtonActionPerformed(evt);
            }
        });

        startTimerButton.setBackground(new java.awt.Color(0, 204, 102));
        startTimerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        startTimerButton.setText("Старт");
        startTimerButton.setMaximumSize(new java.awt.Dimension(75, 25));
        startTimerButton.setMinimumSize(new java.awt.Dimension(75, 25));
        startTimerButton.setPreferredSize(new java.awt.Dimension(75, 25));
        startTimerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTimerButtonActionPerformed(evt);
            }
        });

        stopTimerButton.setBackground(new java.awt.Color(255, 153, 153));
        stopTimerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stopTimerButton.setText("Стоп");
        stopTimerButton.setMaximumSize(new java.awt.Dimension(75, 25));
        stopTimerButton.setMinimumSize(new java.awt.Dimension(75, 25));
        stopTimerButton.setPreferredSize(new java.awt.Dimension(75, 25));
        stopTimerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopTimerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(createNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locationEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(notificationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetTimerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startTimerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopTimerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(createNewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(notificationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(locationEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stopTimerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startTimerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resetTimerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }


    private void locationEditButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(currentTimerForm.isMovingModeActive()) {
            currentTimerForm.disableMovingMode();

            locationEditButton.setBackground(new java.awt.Color(204,204,204));
            locationEditButton.setText("Переместить");
        }
        else {
            currentTimerForm.enableMovingMode();

            locationEditButton.setBackground(new java.awt.Color(102,103,255));
            locationEditButton.setText("Закрепить");
        }
    }


    private void createNewButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(createNewWindow != null && createNewWindow.isDisplayable()) { createNewWindow.dispose(); }
        createNewWindow = WindowsCreator.getInstance().getNewCreateNewTimerForm(timerSetupPropertyFile, this, currentTimerForm);
        disableButtons("Открыто окно создания нового таймера!", new java.awt.Color(255,102,102));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void resetTimerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        currentTimerForm.resetTimer();
        startTimerButton.setEnabled(true);
        stopTimerButton.setEnabled(true);
        resetTimerButton.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        notificationLabel.setText("Для управления воспользуйтесь кнопками справа");
        notificationLabel.setBackground(new java.awt.Color(255, 255, 153));
    }


    private void startTimerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        currentTimerForm.startTimer();
        startTimerButton.setEnabled(false);
        stopTimerButton.setEnabled(true);
        resetTimerButton.setEnabled(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        notificationLabel.setText(" Чтобы закрыть приложение остановите таймер!");
        notificationLabel.setBackground(new Color(255,102,102));
    }


    private void stopTimerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        currentTimerForm.stopTimer();
        startTimerButton.setEnabled(true);
        stopTimerButton.setEnabled(false);
        resetTimerButton.setEnabled(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        notificationLabel.setText("Для управления воспользуйтесь кнопками справа");
        notificationLabel.setBackground(new java.awt.Color(255, 255, 153));
    }


    private Properties initTimerProperties() {

        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/" + timerSetupPropertyFile);
            properties.load(inputStream);
        } catch (IOException e) { e.printStackTrace(); }

        return properties;
    }


    protected void disableButtons(String notificationText, Color color) {

        //Управление режимом перемещения окна таймера
        locationEditButton.setEnabled(false);
        currentTimerForm.disableMovingMode();
        locationEditButton.setBackground(new java.awt.Color(204,204,204));
        locationEditButton.setText("Переместить");

        createNewButton.setEnabled(true);
        resetTimerButton.setEnabled(false);
        startTimerButton.setEnabled(false);
        stopTimerButton.setEnabled(false);

        if(notificationText == null || notificationText.trim().equals("")) {
            notificationLabel.setEnabled(true);
        }
        else {
            notificationLabel.setEnabled(true);
            notificationLabel.setBackground(color);
            notificationLabel.setText(notificationText);
        }
        isButtonsEnabled = false;
    }


    protected void enableButtons(String notificationText, Color color) {

        createNewButton.setEnabled(true);
        locationEditButton.setEnabled(true);
        resetTimerButton.setEnabled(true);
        startTimerButton.setEnabled(true);
        stopTimerButton.setEnabled(true);

        if(notificationText == null || notificationText.trim().equals("")) {
            notificationLabel.setEnabled(true);
        }
        else {
            notificationLabel.setEnabled(true);
            notificationLabel.setBackground(color);
            notificationLabel.setText(notificationText);
        }
        isButtonsEnabled = true;
    }
}
