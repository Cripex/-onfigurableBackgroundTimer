package swingForms;

import swingForms.timerWindow.TimerForm;
import swingForms.timerWindow.WindowsCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class CreateNewTimerForm extends JFrame {

    private javax.swing.JLabel backgroundColorPanel_Key_customB_Label;
    private javax.swing.JLabel backgroundColorPanel_Key_customG_Label;
    private javax.swing.JLabel backgroundColorPanel_Key_customR_Label;
    private javax.swing.JButton backgroundColorPanel_SelectCustom_Button;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton1;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton10;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton2;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton3;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton4;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton5;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton6;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton7;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton8;
    private javax.swing.JToggleButton backgroundColorPanel_ToggleButton9;
    private javax.swing.JTextField backgroundColorPanel_Value_customB_TextField;
    private javax.swing.JTextField backgroundColorPanel_Value_customG_TextField;
    private javax.swing.JTextField backgroundColorPanel_Value_customR_TextField;
    private javax.swing.JButton rootPanel_Save_Button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel offsetLabel0;
    private javax.swing.JLabel offsetLabel1;
    private javax.swing.JButton rootPanel_SoundEditorCheck_Button;
    private javax.swing.JCheckBox rootPanel_SoundEditorOn_CheckBox;
    private javax.swing.JPanel rootPanel_SoundEditor_Panel;
    private javax.swing.JPanel rootPanel_TimerEditor_Panel;
    private javax.swing.JButton rootPanel_TimerWindowEditorCheck_Button;
    private javax.swing.JCheckBox rootPanel_TimerWindowEditorOn_CheckBox;
    private javax.swing.JPanel rootPanel_TimerWindowEditor_Panel;
    private javax.swing.JButton soundEditor_DownloadMainSound_Button;
    private javax.swing.JButton soundEditor_DownloadSecondSound_Button;
    private javax.swing.JCheckBox soundEditor_SecondSoundOn_CheckBox;
    private javax.swing.JLabel soundEditor_VolumeEditor_Label;
    private javax.swing.JSlider soundEditor_VolumeEditor_Slider;
    private javax.swing.JPanel tabbedPane_BackgroundColor_Panel;
    private javax.swing.JPanel tabbedPane_TimerColor_Panel;
    private javax.swing.JPanel tabbedPane_TimerFont_Panel;
    private javax.swing.JLabel timerColorPanel_Key_customB_Label;
    private javax.swing.JLabel timerColorPanel_Key_customG_Label;
    private javax.swing.JLabel timerColorPanel_Key_customR_Label;
    private javax.swing.JButton timerColorPanel_SelectCustom_Button;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton1;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton10;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton2;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton3;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton4;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton5;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton6;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton7;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton8;
    private javax.swing.JToggleButton timerColorPanel_ToggleButton9;
    private javax.swing.JTextField timerColorPanel_Value_customB_TextField;
    private javax.swing.JTextField timerColorPanel_Value_customG_TextField;
    private javax.swing.JTextField timerColorPanel_Value_customR_TextField;
    private javax.swing.JLabel timerEditorPanel_Key_Hours_Label;
    private javax.swing.JLabel timerEditorPanel_Key_Minuts_Label;
    private javax.swing.JLabel timerEditorPanel_Key_Seconds_Label;
    private javax.swing.JTextField timerEditorPanel_Value_Hours_TextField;
    private javax.swing.JTextField timerEditorPanel_Value_Minuts_TextField;
    private javax.swing.JTextField timerEditorPanel_Value_Seconds_TextField;
    private javax.swing.JCheckBox timerFontPanel_IsBold_CheckBox;
    private javax.swing.JLabel timerFontPanel_Key_Font_Label;
    private javax.swing.JLabel timerFontPanel_Key_Size_Label;
    private javax.swing.JComboBox<String> timerFontPanel_Value_Font_ComboBox;
    private javax.swing.JTextField timerFontPanel_Value_Size_TextField;
    private javax.swing.JLabel timerWindowEditor_BackgroudTransparency_Label;
    private javax.swing.JSlider timerWindowEditor_BackgroudTransparency_Slider;
    private javax.swing.JLabel timerWindowEditor_Key_sizeX_Label;
    private javax.swing.JLabel timerWindowEditor_Key_sizeY_Label;
    private javax.swing.JTabbedPane timerWindowEditor_TabbedPane;
    private javax.swing.JTextField timerWindowEditor_Value_sizeX_TextField;
    private javax.swing.JTextField timerWindowEditor_Value_sizeY_TextField;
    private javax.swing.JLabel timerWindowEditor_sizeDescription_Label;

    private static TimerForm currentTimerForm;
    private static MainForm parentMainForm;
    private HashMap<Component, CheckAction> componentCheckActionHashMap = new HashMap<>();
    private String timerSetupPropertyFile;

    private JFileChooser fileChooser;

//    private final String MAIN_SOUND_PATH = "src/main/resources/timerSources/soundFiles/mainSound.wav";
//    private final String SECOND_SOUND_PATH = "src/main/resources/timerSources/soundFiles/secondSound.wav";
    private final String MAIN_SOUND_PATH = "timerSources/soundFiles/mainSound.wav";
    private final String SECOND_SOUND_PATH = "timerSources/soundFiles/secondSound.wav";

    private boolean isSoundCheckButtonBlocked = false;


    public CreateNewTimerForm(String timerSetupPropertyFile, MainForm parentMainForm, TimerForm currentTimerForm, WindowsCreator creator) {

        this.timerSetupPropertyFile = timerSetupPropertyFile;
        this.parentMainForm = parentMainForm;
        this.currentTimerForm = currentTimerForm;

        if(currentTimerForm != null && currentTimerForm.getJFrame().isDisplayable()) { currentTimerForm.getJFrame().dispose(); }

        fileChooser = new JFileChooser();

        initComponents();
        initComponentsCheckAction();
        initTextFieldsCheckAlgorithm(jPanel1);
        initColorButtons(tabbedPane_BackgroundColor_Panel, backgroundColorPanel_Value_customR_TextField,
                backgroundColorPanel_Value_customG_TextField, backgroundColorPanel_Value_customB_TextField,
                backgroundColorPanel_SelectCustom_Button);
        initColorButtons(tabbedPane_TimerColor_Panel, timerColorPanel_Value_customR_TextField,
                timerColorPanel_Value_customG_TextField, timerColorPanel_Value_customB_TextField,
                timerColorPanel_SelectCustom_Button);

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
//        printFontNamesList();
        this.setVisible(true);
    }


    private void initColorButtons(JPanel toggleColorButtonsPanel, JTextField fieldR, JTextField fieldG, JTextField fieldB, JButton buttonToSelectRGBcustomColor) {

        List<JToggleButton> colorButtons = new ArrayList<>();

        for(Component component : toggleColorButtonsPanel.getComponents()) {
            if(component.getClass().equals(JToggleButton.class)) {
                colorButtons.add((JToggleButton) component);
            }
        }

        for(JToggleButton colorButton : colorButtons) {
            colorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(JToggleButton currentColorButton : colorButtons) {
                        currentColorButton.setSelected(false);
                    }
                    colorButton.setSelected(true);
                    fieldR.setText(String.valueOf(colorButton.getBackground().getRed()));
                    fieldG.setText(String.valueOf(colorButton.getBackground().getGreen()));
                    fieldB.setText(String.valueOf(colorButton.getBackground().getBlue()));
                }
            });
        }
        buttonToSelectRGBcustomColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JToggleButton colorButton : colorButtons) {
                    colorButton.setSelected(false);
                }
            }
        });
    }


    private void printFontNamesList() {
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//        for(Font font : ge.getAllFonts()) {
//            timerFontPanel_Value_Font_ComboBox.addItem(font.getFontName());
//        }
//        timerFontPanel_Value_Font_ComboBox.setSelectedItem("Tahoma");
    }


    private boolean checkIsJTextFieldEmpty(JTextField textField) {

        String text = textField.getText().replaceAll("[^0-9]","");
        if(text.isEmpty()) {
            return true;
        }
        return false;
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        rootPanel_TimerEditor_Panel = new javax.swing.JPanel();
        timerEditorPanel_Key_Hours_Label = new javax.swing.JLabel();
        timerEditorPanel_Key_Minuts_Label = new javax.swing.JLabel();
        timerEditorPanel_Key_Seconds_Label = new javax.swing.JLabel();
        timerEditorPanel_Value_Hours_TextField = new javax.swing.JTextField();
        timerEditorPanel_Value_Minuts_TextField = new javax.swing.JTextField();
        timerEditorPanel_Value_Seconds_TextField = new javax.swing.JTextField();
        rootPanel_SoundEditor_Panel = new javax.swing.JPanel();
        soundEditor_SecondSoundOn_CheckBox = new javax.swing.JCheckBox();
        soundEditor_VolumeEditor_Slider = new javax.swing.JSlider();
        soundEditor_DownloadSecondSound_Button = new javax.swing.JButton();
        soundEditor_DownloadMainSound_Button = new javax.swing.JButton();
        soundEditor_VolumeEditor_Label = new javax.swing.JLabel();
        rootPanel_SoundEditorOn_CheckBox = new javax.swing.JCheckBox();
        rootPanel_SoundEditorCheck_Button = new javax.swing.JButton();
        rootPanel_TimerWindowEditor_Panel = new javax.swing.JPanel();
        timerWindowEditor_Value_sizeX_TextField = new javax.swing.JTextField();
        timerWindowEditor_Key_sizeX_Label = new javax.swing.JLabel();
        timerWindowEditor_Value_sizeY_TextField = new javax.swing.JTextField();
        timerWindowEditor_Key_sizeY_Label = new javax.swing.JLabel();
        timerWindowEditor_sizeDescription_Label = new javax.swing.JLabel();
        timerWindowEditor_TabbedPane = new javax.swing.JTabbedPane();
        tabbedPane_BackgroundColor_Panel = new javax.swing.JPanel();
        backgroundColorPanel_ToggleButton1 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton2 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton3 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton4 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton7 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton5 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton6 = new javax.swing.JToggleButton();
        backgroundColorPanel_SelectCustom_Button = new javax.swing.JButton();
        backgroundColorPanel_ToggleButton8 = new javax.swing.JToggleButton();
        backgroundColorPanel_Value_customR_TextField = new javax.swing.JTextField();
        backgroundColorPanel_Key_customR_Label = new javax.swing.JLabel();
        backgroundColorPanel_Key_customG_Label = new javax.swing.JLabel();
        backgroundColorPanel_Value_customG_TextField = new javax.swing.JTextField();
        backgroundColorPanel_Key_customB_Label = new javax.swing.JLabel();
        backgroundColorPanel_Value_customB_TextField = new javax.swing.JTextField();
        backgroundColorPanel_ToggleButton9 = new javax.swing.JToggleButton();
        backgroundColorPanel_ToggleButton10 = new javax.swing.JToggleButton();
        tabbedPane_TimerColor_Panel = new javax.swing.JPanel();
        timerColorPanel_ToggleButton1 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton2 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton3 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton4 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton5 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton6 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton7 = new javax.swing.JToggleButton();
        timerColorPanel_SelectCustom_Button = new javax.swing.JButton();
        timerColorPanel_ToggleButton8 = new javax.swing.JToggleButton();
        timerColorPanel_Value_customR_TextField = new javax.swing.JTextField();
        timerColorPanel_Key_customR_Label = new javax.swing.JLabel();
        timerColorPanel_Key_customG_Label = new javax.swing.JLabel();
        timerColorPanel_Value_customG_TextField = new javax.swing.JTextField();
        timerColorPanel_Key_customB_Label = new javax.swing.JLabel();
        timerColorPanel_Value_customB_TextField = new javax.swing.JTextField();
        timerColorPanel_ToggleButton9 = new javax.swing.JToggleButton();
        timerColorPanel_ToggleButton10 = new javax.swing.JToggleButton();
        tabbedPane_TimerFont_Panel = new javax.swing.JPanel();
        timerFontPanel_Value_Size_TextField = new javax.swing.JTextField();
        timerFontPanel_Key_Size_Label = new javax.swing.JLabel();
        timerFontPanel_Key_Font_Label = new javax.swing.JLabel();
        timerFontPanel_IsBold_CheckBox = new javax.swing.JCheckBox();
        timerWindowEditor_BackgroudTransparency_Slider = new javax.swing.JSlider();
        timerWindowEditor_BackgroudTransparency_Label = new javax.swing.JLabel();
        rootPanel_TimerWindowEditorOn_CheckBox = new javax.swing.JCheckBox();
        rootPanel_TimerWindowEditorCheck_Button = new javax.swing.JButton();
        offsetLabel1 = new javax.swing.JLabel();
        offsetLabel0 = new javax.swing.JLabel();
        rootPanel_Save_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(430, 500));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(430, 500));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(430, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(410, 736));
        jPanel1.setMinimumSize(new java.awt.Dimension(410, 736));
        jPanel1.setPreferredSize(new java.awt.Dimension(410, 736));

        rootPanel_TimerEditor_Panel.setBackground(new java.awt.Color(204, 204, 204));
        rootPanel_TimerEditor_Panel.setMaximumSize(new java.awt.Dimension(340, 113));
        rootPanel_TimerEditor_Panel.setMinimumSize(new java.awt.Dimension(340, 113));
        rootPanel_TimerEditor_Panel.setPreferredSize(new java.awt.Dimension(340, 113));

        timerEditorPanel_Key_Hours_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerEditorPanel_Key_Hours_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerEditorPanel_Key_Hours_Label.setText("ЧАСЫ");
        timerEditorPanel_Key_Hours_Label.setMaximumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Hours_Label.setMinimumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Hours_Label.setPreferredSize(new java.awt.Dimension(100, 20));

        timerEditorPanel_Key_Minuts_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerEditorPanel_Key_Minuts_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerEditorPanel_Key_Minuts_Label.setText("МИНУТЫ");
        timerEditorPanel_Key_Minuts_Label.setMaximumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Minuts_Label.setMinimumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Minuts_Label.setPreferredSize(new java.awt.Dimension(100, 20));

        timerEditorPanel_Key_Seconds_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerEditorPanel_Key_Seconds_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerEditorPanel_Key_Seconds_Label.setText("СЕКУНДЫ");
        timerEditorPanel_Key_Seconds_Label.setMaximumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Seconds_Label.setMinimumSize(new java.awt.Dimension(100, 20));
        timerEditorPanel_Key_Seconds_Label.setPreferredSize(new java.awt.Dimension(100, 20));

        timerEditorPanel_Value_Hours_TextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timerEditorPanel_Value_Hours_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerEditorPanel_Value_Hours_TextField.setText("0");
        timerEditorPanel_Value_Hours_TextField.setMaximumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Hours_TextField.setMinimumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Hours_TextField.setPreferredSize(new java.awt.Dimension(60, 60));

        timerEditorPanel_Value_Minuts_TextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timerEditorPanel_Value_Minuts_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerEditorPanel_Value_Minuts_TextField.setText("0");
        timerEditorPanel_Value_Minuts_TextField.setMaximumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Minuts_TextField.setMinimumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Minuts_TextField.setPreferredSize(new java.awt.Dimension(60, 60));

        timerEditorPanel_Value_Seconds_TextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timerEditorPanel_Value_Seconds_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerEditorPanel_Value_Seconds_TextField.setText("1");
        timerEditorPanel_Value_Seconds_TextField.setMaximumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Seconds_TextField.setMinimumSize(new java.awt.Dimension(60, 60));
        timerEditorPanel_Value_Seconds_TextField.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout rootPanel_TimerEditor_PanelLayout = new javax.swing.GroupLayout(rootPanel_TimerEditor_Panel);
        rootPanel_TimerEditor_Panel.setLayout(rootPanel_TimerEditor_PanelLayout);
        rootPanel_TimerEditor_PanelLayout.setHorizontalGroup(
                rootPanel_TimerEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rootPanel_TimerEditor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rootPanel_TimerEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(rootPanel_TimerEditor_PanelLayout.createSequentialGroup()
                                                .addComponent(timerEditorPanel_Key_Hours_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerEditorPanel_Key_Minuts_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerEditorPanel_Key_Seconds_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(rootPanel_TimerEditor_PanelLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(timerEditorPanel_Value_Hours_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(timerEditorPanel_Value_Minuts_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(timerEditorPanel_Value_Seconds_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rootPanel_TimerEditor_PanelLayout.setVerticalGroup(
                rootPanel_TimerEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanel_TimerEditor_PanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(rootPanel_TimerEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timerEditorPanel_Key_Minuts_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerEditorPanel_Key_Seconds_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerEditorPanel_Key_Hours_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rootPanel_TimerEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(timerEditorPanel_Value_Minuts_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerEditorPanel_Value_Hours_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerEditorPanel_Value_Seconds_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        rootPanel_SoundEditor_Panel.setBackground(new java.awt.Color(204, 204, 204));
        rootPanel_SoundEditor_Panel.setMaximumSize(new java.awt.Dimension(340, 184));
        rootPanel_SoundEditor_Panel.setMinimumSize(new java.awt.Dimension(340, 184));
        rootPanel_SoundEditor_Panel.setPreferredSize(new java.awt.Dimension(340, 184));

        soundEditor_SecondSoundOn_CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        soundEditor_SecondSoundOn_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        soundEditor_SecondSoundOn_CheckBox.setText("Сигнал обратного отсчета (3 сек.)");
        soundEditor_SecondSoundOn_CheckBox.setEnabled(false);
        soundEditor_SecondSoundOn_CheckBox.setMaximumSize(new java.awt.Dimension(312, 17));
        soundEditor_SecondSoundOn_CheckBox.setMinimumSize(new java.awt.Dimension(312, 17));
        soundEditor_SecondSoundOn_CheckBox.setOpaque(false);
        soundEditor_SecondSoundOn_CheckBox.setPreferredSize(new java.awt.Dimension(312, 17));
        soundEditor_SecondSoundOn_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundEditor_SecondSoundOn_CheckBoxActionPerformed(evt);
            }
        });

        soundEditor_VolumeEditor_Slider.setEnabled(false);
        soundEditor_VolumeEditor_Slider.setInverted(true);
        soundEditor_VolumeEditor_Slider.setMaximumSize(new java.awt.Dimension(312, 20));
        soundEditor_VolumeEditor_Slider.setMinimumSize(new java.awt.Dimension(312, 20));
        soundEditor_VolumeEditor_Slider.setPreferredSize(new java.awt.Dimension(312, 20));
        soundEditor_VolumeEditor_Slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                soundEditor_VolumeEditor_SliderMouseClicked(evt);
            }
        });

        soundEditor_DownloadSecondSound_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        soundEditor_DownloadSecondSound_Button.setText("Загрузить звук обратного отсчета (.wav)");
        soundEditor_DownloadSecondSound_Button.setEnabled(false);
        soundEditor_DownloadSecondSound_Button.setMaximumSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadSecondSound_Button.setMinimumSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadSecondSound_Button.setPreferredSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadSecondSound_Button.setRequestFocusEnabled(false);
        soundEditor_DownloadSecondSound_Button.setRolloverEnabled(false);
        soundEditor_DownloadSecondSound_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundEditor_DownloadSecondSound_ButtonActionPerformed(evt);
            }
        });

        soundEditor_DownloadMainSound_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        soundEditor_DownloadMainSound_Button.setText("Загрузить основной звук (формат .wav)");
        soundEditor_DownloadMainSound_Button.setEnabled(false);
        soundEditor_DownloadMainSound_Button.setMaximumSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadMainSound_Button.setMinimumSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadMainSound_Button.setPreferredSize(new java.awt.Dimension(312, 32));
        soundEditor_DownloadMainSound_Button.setRequestFocusEnabled(false);
        soundEditor_DownloadMainSound_Button.setRolloverEnabled(false);
        soundEditor_DownloadMainSound_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundEditor_DownloadMainSound_ButtonActionPerformed(evt);
            }
        });

        soundEditor_VolumeEditor_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        soundEditor_VolumeEditor_Label.setText("Громкость сигнала");
        soundEditor_VolumeEditor_Label.setMaximumSize(new java.awt.Dimension(312, 17));
        soundEditor_VolumeEditor_Label.setMinimumSize(new java.awt.Dimension(312, 17));
        soundEditor_VolumeEditor_Label.setPreferredSize(new java.awt.Dimension(312, 17));

        javax.swing.GroupLayout rootPanel_SoundEditor_PanelLayout = new javax.swing.GroupLayout(rootPanel_SoundEditor_Panel);
        rootPanel_SoundEditor_Panel.setLayout(rootPanel_SoundEditor_PanelLayout);
        rootPanel_SoundEditor_PanelLayout.setHorizontalGroup(
                rootPanel_SoundEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rootPanel_SoundEditor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rootPanel_SoundEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(soundEditor_DownloadMainSound_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soundEditor_DownloadSecondSound_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soundEditor_VolumeEditor_Slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soundEditor_VolumeEditor_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soundEditor_SecondSoundOn_CheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rootPanel_SoundEditor_PanelLayout.setVerticalGroup(
                rootPanel_SoundEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rootPanel_SoundEditor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(soundEditor_VolumeEditor_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundEditor_VolumeEditor_Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(soundEditor_DownloadMainSound_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(soundEditor_SecondSoundOn_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundEditor_DownloadSecondSound_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rootPanel_SoundEditorOn_CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        rootPanel_SoundEditorOn_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rootPanel_SoundEditorOn_CheckBox.setText("Сигнал окончания времени");
        rootPanel_SoundEditorOn_CheckBox.setMaximumSize(new java.awt.Dimension(208, 24));
        rootPanel_SoundEditorOn_CheckBox.setMinimumSize(new java.awt.Dimension(208, 24));
        rootPanel_SoundEditorOn_CheckBox.setPreferredSize(new java.awt.Dimension(208, 24));
        rootPanel_SoundEditorOn_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootPanel_SoundEditorOn_CheckBoxActionPerformed(evt);
            }
        });

        rootPanel_SoundEditorCheck_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rootPanel_SoundEditorCheck_Button.setText("Проверить");
        rootPanel_SoundEditorCheck_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rootPanel_SoundEditorCheck_Button.setMaximumSize(new java.awt.Dimension(114, 24));
        rootPanel_SoundEditorCheck_Button.setMinimumSize(new java.awt.Dimension(114, 24));
        rootPanel_SoundEditorCheck_Button.setPreferredSize(new java.awt.Dimension(114, 24));
        rootPanel_SoundEditorCheck_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootPanel_SoundEditorCheck_ButtonActionPerformed(evt);
            }
        });

        rootPanel_TimerWindowEditor_Panel.setBackground(new java.awt.Color(204, 204, 204));
        rootPanel_TimerWindowEditor_Panel.setMaximumSize(new java.awt.Dimension(340, 230));
        rootPanel_TimerWindowEditor_Panel.setMinimumSize(new java.awt.Dimension(340, 230));
        rootPanel_TimerWindowEditor_Panel.setPreferredSize(new java.awt.Dimension(340, 230));

        timerWindowEditor_Value_sizeX_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerWindowEditor_Value_sizeX_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerWindowEditor_Value_sizeX_TextField.setEnabled(false);
        timerWindowEditor_Value_sizeX_TextField.setMaximumSize(new java.awt.Dimension(48, 24));
        timerWindowEditor_Value_sizeX_TextField.setMinimumSize(new java.awt.Dimension(48, 24));
        timerWindowEditor_Value_sizeX_TextField.setPreferredSize(new java.awt.Dimension(48, 24));

        timerWindowEditor_Key_sizeX_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerWindowEditor_Key_sizeX_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerWindowEditor_Key_sizeX_Label.setText("X");
        timerWindowEditor_Key_sizeX_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        timerWindowEditor_Key_sizeX_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        timerWindowEditor_Key_sizeX_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        timerWindowEditor_Value_sizeY_TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerWindowEditor_Value_sizeY_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerWindowEditor_Value_sizeY_TextField.setEnabled(false);
        timerWindowEditor_Value_sizeY_TextField.setMaximumSize(new java.awt.Dimension(48, 24));
        timerWindowEditor_Value_sizeY_TextField.setMinimumSize(new java.awt.Dimension(48, 24));
        timerWindowEditor_Value_sizeY_TextField.setPreferredSize(new java.awt.Dimension(48, 24));

        timerWindowEditor_Key_sizeY_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerWindowEditor_Key_sizeY_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerWindowEditor_Key_sizeY_Label.setText("Y");
        timerWindowEditor_Key_sizeY_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        timerWindowEditor_Key_sizeY_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        timerWindowEditor_Key_sizeY_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        timerWindowEditor_sizeDescription_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerWindowEditor_sizeDescription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerWindowEditor_sizeDescription_Label.setText("Размер в пикселях");
        timerWindowEditor_sizeDescription_Label.setMaximumSize(new java.awt.Dimension(130, 24));
        timerWindowEditor_sizeDescription_Label.setMinimumSize(new java.awt.Dimension(130, 24));
        timerWindowEditor_sizeDescription_Label.setPreferredSize(new java.awt.Dimension(130, 24));

        timerWindowEditor_TabbedPane.setEnabled(false);
        timerWindowEditor_TabbedPane.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        timerWindowEditor_TabbedPane.setMaximumSize(new java.awt.Dimension(320, 90));
        timerWindowEditor_TabbedPane.setMinimumSize(new java.awt.Dimension(320, 90));
        timerWindowEditor_TabbedPane.setPreferredSize(new java.awt.Dimension(320, 90));

        tabbedPane_BackgroundColor_Panel.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane_BackgroundColor_Panel.setMaximumSize(new java.awt.Dimension(320, 60));
        tabbedPane_BackgroundColor_Panel.setMinimumSize(new java.awt.Dimension(320, 60));
        tabbedPane_BackgroundColor_Panel.setPreferredSize(new java.awt.Dimension(320, 60));

        backgroundColorPanel_ToggleButton1.setBackground(new java.awt.Color(51, 204, 255));
        backgroundColorPanel_ToggleButton1.setEnabled(false);
        backgroundColorPanel_ToggleButton1.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton1.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton1.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton2.setBackground(new java.awt.Color(0, 51, 255));
        backgroundColorPanel_ToggleButton2.setEnabled(false);
        backgroundColorPanel_ToggleButton2.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton2.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton2.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton3.setBackground(new java.awt.Color(255, 51, 51));
        backgroundColorPanel_ToggleButton3.setEnabled(false);
        backgroundColorPanel_ToggleButton3.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton3.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton3.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton4.setBackground(new java.awt.Color(153, 0, 204));
        backgroundColorPanel_ToggleButton4.setEnabled(false);
        backgroundColorPanel_ToggleButton4.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton4.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton4.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton7.setBackground(new java.awt.Color(0, 153, 102));
        backgroundColorPanel_ToggleButton7.setEnabled(false);
        backgroundColorPanel_ToggleButton7.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton7.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton7.setPreferredSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorPanel_ToggleButton7ActionPerformed(evt);
            }
        });

        backgroundColorPanel_ToggleButton5.setBackground(new java.awt.Color(255, 153, 102));
        backgroundColorPanel_ToggleButton5.setEnabled(false);
        backgroundColorPanel_ToggleButton5.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton5.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton5.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton6.setBackground(new java.awt.Color(255, 255, 102));
        backgroundColorPanel_ToggleButton6.setEnabled(false);
        backgroundColorPanel_ToggleButton6.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton6.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton6.setPreferredSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorPanel_ToggleButton6ActionPerformed(evt);
            }
        });

        backgroundColorPanel_SelectCustom_Button.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        backgroundColorPanel_SelectCustom_Button.setText("Выбрать");
        backgroundColorPanel_SelectCustom_Button.setEnabled(false);
        backgroundColorPanel_SelectCustom_Button.setMaximumSize(new java.awt.Dimension(74, 24));
        backgroundColorPanel_SelectCustom_Button.setMinimumSize(new java.awt.Dimension(74, 24));
        backgroundColorPanel_SelectCustom_Button.setPreferredSize(new java.awt.Dimension(74, 24));
        backgroundColorPanel_SelectCustom_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorPanel_SelectCustom_ButtonActionPerformed(evt);
            }
        });

        backgroundColorPanel_ToggleButton8.setBackground(new java.awt.Color(0, 102, 102));
        backgroundColorPanel_ToggleButton8.setEnabled(false);
        backgroundColorPanel_ToggleButton8.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton8.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton8.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_Value_customR_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backgroundColorPanel_Value_customR_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        backgroundColorPanel_Value_customR_TextField.setEnabled(false);
        backgroundColorPanel_Value_customR_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customR_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customR_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        backgroundColorPanel_Key_customR_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backgroundColorPanel_Key_customR_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundColorPanel_Key_customR_Label.setText("R");
        backgroundColorPanel_Key_customR_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customR_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customR_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        backgroundColorPanel_Key_customG_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backgroundColorPanel_Key_customG_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundColorPanel_Key_customG_Label.setText("G");
        backgroundColorPanel_Key_customG_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customG_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customG_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        backgroundColorPanel_Value_customG_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backgroundColorPanel_Value_customG_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        backgroundColorPanel_Value_customG_TextField.setEnabled(false);
        backgroundColorPanel_Value_customG_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customG_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customG_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        backgroundColorPanel_Key_customB_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backgroundColorPanel_Key_customB_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundColorPanel_Key_customB_Label.setText("B");
        backgroundColorPanel_Key_customB_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customB_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        backgroundColorPanel_Key_customB_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        backgroundColorPanel_Value_customB_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backgroundColorPanel_Value_customB_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        backgroundColorPanel_Value_customB_TextField.setEnabled(false);
        backgroundColorPanel_Value_customB_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customB_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        backgroundColorPanel_Value_customB_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        backgroundColorPanel_ToggleButton9.setBackground(new java.awt.Color(102, 102, 102));
        backgroundColorPanel_ToggleButton9.setEnabled(false);
        backgroundColorPanel_ToggleButton9.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton9.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton9.setPreferredSize(new java.awt.Dimension(24, 24));

        backgroundColorPanel_ToggleButton10.setBackground(new java.awt.Color(255, 255, 255));
        backgroundColorPanel_ToggleButton10.setEnabled(false);
        backgroundColorPanel_ToggleButton10.setMaximumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton10.setMinimumSize(new java.awt.Dimension(24, 24));
        backgroundColorPanel_ToggleButton10.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout tabbedPane_BackgroundColor_PanelLayout = new javax.swing.GroupLayout(tabbedPane_BackgroundColor_Panel);
        tabbedPane_BackgroundColor_Panel.setLayout(tabbedPane_BackgroundColor_PanelLayout);
        tabbedPane_BackgroundColor_PanelLayout.setHorizontalGroup(
                tabbedPane_BackgroundColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabbedPane_BackgroundColor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tabbedPane_BackgroundColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(tabbedPane_BackgroundColor_PanelLayout.createSequentialGroup()
                                                .addComponent(backgroundColorPanel_ToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_ToggleButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabbedPane_BackgroundColor_PanelLayout.createSequentialGroup()
                                                .addComponent(backgroundColorPanel_Key_customR_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_Value_customR_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_Key_customG_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_Value_customG_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_Key_customB_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_Value_customB_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(backgroundColorPanel_SelectCustom_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        tabbedPane_BackgroundColor_PanelLayout.setVerticalGroup(
                tabbedPane_BackgroundColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabbedPane_BackgroundColor_PanelLayout.createSequentialGroup()
                                .addGroup(tabbedPane_BackgroundColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(backgroundColorPanel_ToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_ToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabbedPane_BackgroundColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(backgroundColorPanel_Value_customR_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_Key_customR_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_Key_customG_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_Value_customG_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_Key_customB_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_Value_customB_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backgroundColorPanel_SelectCustom_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(7, Short.MAX_VALUE))
        );

        timerWindowEditor_TabbedPane.addTab("Цвет фона", tabbedPane_BackgroundColor_Panel);

        tabbedPane_TimerColor_Panel.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane_TimerColor_Panel.setMaximumSize(new java.awt.Dimension(320, 60));
        tabbedPane_TimerColor_Panel.setMinimumSize(new java.awt.Dimension(320, 60));
        tabbedPane_TimerColor_Panel.setPreferredSize(new java.awt.Dimension(320, 60));

        timerColorPanel_ToggleButton1.setBackground(new java.awt.Color(51, 204, 255));
        timerColorPanel_ToggleButton1.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton1.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton1.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton2.setBackground(new java.awt.Color(0, 51, 255));
        timerColorPanel_ToggleButton2.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton2.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton2.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton3.setBackground(new java.awt.Color(255, 51, 51));
        timerColorPanel_ToggleButton3.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton3.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton3.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton4.setBackground(new java.awt.Color(153, 0, 204));
        timerColorPanel_ToggleButton4.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton4.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton4.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton5.setBackground(new java.awt.Color(0, 153, 102));
        timerColorPanel_ToggleButton5.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton5.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton5.setPreferredSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerColorPanel_ToggleButton5ActionPerformed(evt);
            }
        });

        timerColorPanel_ToggleButton6.setBackground(new java.awt.Color(255, 153, 102));
        timerColorPanel_ToggleButton6.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton6.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton6.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton7.setBackground(new java.awt.Color(255, 255, 102));
        timerColorPanel_ToggleButton7.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton7.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton7.setPreferredSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerColorPanel_ToggleButton7ActionPerformed(evt);
            }
        });

        timerColorPanel_SelectCustom_Button.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        timerColorPanel_SelectCustom_Button.setText("Выбрать");
        timerColorPanel_SelectCustom_Button.setMaximumSize(new java.awt.Dimension(74, 24));
        timerColorPanel_SelectCustom_Button.setMinimumSize(new java.awt.Dimension(74, 24));
        timerColorPanel_SelectCustom_Button.setPreferredSize(new java.awt.Dimension(74, 24));
        timerColorPanel_SelectCustom_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerColorPanel_SelectCustom_ButtonActionPerformed(evt);
            }
        });

        timerColorPanel_ToggleButton8.setBackground(new java.awt.Color(0, 102, 102));
        timerColorPanel_ToggleButton8.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton8.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton8.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_Value_customR_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerColorPanel_Value_customR_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerColorPanel_Value_customR_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customR_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customR_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        timerColorPanel_Key_customR_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerColorPanel_Key_customR_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerColorPanel_Key_customR_Label.setText("R");
        timerColorPanel_Key_customR_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timerColorPanel_Key_customR_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customR_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customR_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        timerColorPanel_Key_customG_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerColorPanel_Key_customG_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerColorPanel_Key_customG_Label.setText("G");
        timerColorPanel_Key_customG_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timerColorPanel_Key_customG_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customG_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customG_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        timerColorPanel_Value_customG_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerColorPanel_Value_customG_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerColorPanel_Value_customG_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customG_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customG_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        timerColorPanel_Key_customB_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        timerColorPanel_Key_customB_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerColorPanel_Key_customB_Label.setText("B");
        timerColorPanel_Key_customB_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timerColorPanel_Key_customB_Label.setMaximumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customB_Label.setMinimumSize(new java.awt.Dimension(20, 24));
        timerColorPanel_Key_customB_Label.setPreferredSize(new java.awt.Dimension(20, 24));

        timerColorPanel_Value_customB_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerColorPanel_Value_customB_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerColorPanel_Value_customB_TextField.setMaximumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customB_TextField.setMinimumSize(new java.awt.Dimension(32, 24));
        timerColorPanel_Value_customB_TextField.setPreferredSize(new java.awt.Dimension(32, 24));

        timerColorPanel_ToggleButton9.setBackground(new java.awt.Color(102, 102, 102));
        timerColorPanel_ToggleButton9.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton9.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton9.setPreferredSize(new java.awt.Dimension(24, 24));

        timerColorPanel_ToggleButton10.setBackground(new java.awt.Color(255, 255, 255));
        timerColorPanel_ToggleButton10.setMaximumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton10.setMinimumSize(new java.awt.Dimension(24, 24));
        timerColorPanel_ToggleButton10.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout tabbedPane_TimerColor_PanelLayout = new javax.swing.GroupLayout(tabbedPane_TimerColor_Panel);
        tabbedPane_TimerColor_Panel.setLayout(tabbedPane_TimerColor_PanelLayout);
        tabbedPane_TimerColor_PanelLayout.setHorizontalGroup(
                tabbedPane_TimerColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabbedPane_TimerColor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tabbedPane_TimerColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(tabbedPane_TimerColor_PanelLayout.createSequentialGroup()
                                                .addComponent(timerColorPanel_ToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_ToggleButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabbedPane_TimerColor_PanelLayout.createSequentialGroup()
                                                .addComponent(timerColorPanel_Key_customR_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_Value_customR_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_Key_customG_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_Value_customG_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_Key_customB_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_Value_customB_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerColorPanel_SelectCustom_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        tabbedPane_TimerColor_PanelLayout.setVerticalGroup(
                tabbedPane_TimerColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabbedPane_TimerColor_PanelLayout.createSequentialGroup()
                                .addGroup(tabbedPane_TimerColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(timerColorPanel_ToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_ToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabbedPane_TimerColor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timerColorPanel_Value_customR_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_Key_customR_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_Key_customG_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_Value_customG_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_Key_customB_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_Value_customB_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerColorPanel_SelectCustom_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(7, Short.MAX_VALUE))
        );

        timerWindowEditor_TabbedPane.addTab("Цвет циферблата", tabbedPane_TimerColor_Panel);

        tabbedPane_TimerFont_Panel.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane_TimerFont_Panel.setMaximumSize(new java.awt.Dimension(320, 60));
        tabbedPane_TimerFont_Panel.setMinimumSize(new java.awt.Dimension(320, 60));
        tabbedPane_TimerFont_Panel.setPreferredSize(new java.awt.Dimension(320, 60));

        timerFontPanel_Value_Size_TextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerFontPanel_Value_Size_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timerFontPanel_Value_Size_TextField.setText("60");
        timerFontPanel_Value_Size_TextField.setMaximumSize(new java.awt.Dimension(46, 24));
        timerFontPanel_Value_Size_TextField.setMinimumSize(new java.awt.Dimension(46, 24));
        timerFontPanel_Value_Size_TextField.setPreferredSize(new java.awt.Dimension(46, 24));

        timerFontPanel_Key_Size_Label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerFontPanel_Key_Size_Label.setText("Размер (1-200)");
        timerFontPanel_Key_Size_Label.setMaximumSize(new java.awt.Dimension(95, 24));
        timerFontPanel_Key_Size_Label.setMinimumSize(new java.awt.Dimension(95, 24));
        timerFontPanel_Key_Size_Label.setName(""); // NOI18N
        timerFontPanel_Key_Size_Label.setPreferredSize(new java.awt.Dimension(95, 24));
        timerFontPanel_Key_Size_Label.setVerifyInputWhenFocusTarget(false);

        timerFontPanel_Key_Font_Label.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerFontPanel_Key_Font_Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        timerFontPanel_Key_Font_Label.setText("Параметры циферблата:");
        timerFontPanel_Key_Font_Label.setMaximumSize(new java.awt.Dimension(133, 24));
        timerFontPanel_Key_Font_Label.setMinimumSize(new java.awt.Dimension(133, 24));
        timerFontPanel_Key_Font_Label.setPreferredSize(new java.awt.Dimension(133, 24));
        timerFontPanel_Key_Font_Label.setVerifyInputWhenFocusTarget(false);

        timerFontPanel_IsBold_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timerFontPanel_IsBold_CheckBox.setText("Жирный шрифт");
        timerFontPanel_IsBold_CheckBox.setMaximumSize(new java.awt.Dimension(120, 24));
        timerFontPanel_IsBold_CheckBox.setMinimumSize(new java.awt.Dimension(120, 24));
        timerFontPanel_IsBold_CheckBox.setOpaque(false);
        timerFontPanel_IsBold_CheckBox.setPreferredSize(new java.awt.Dimension(120, 24));
        timerFontPanel_IsBold_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerFontPanel_IsBold_CheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabbedPane_TimerFont_PanelLayout = new javax.swing.GroupLayout(tabbedPane_TimerFont_Panel);
        tabbedPane_TimerFont_Panel.setLayout(tabbedPane_TimerFont_PanelLayout);
        tabbedPane_TimerFont_PanelLayout.setHorizontalGroup(
                tabbedPane_TimerFont_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabbedPane_TimerFont_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tabbedPane_TimerFont_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(tabbedPane_TimerFont_PanelLayout.createSequentialGroup()
                                                .addComponent(timerFontPanel_Key_Size_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerFontPanel_Value_Size_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(timerFontPanel_Key_Font_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(timerFontPanel_IsBold_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, Short.MAX_VALUE))
        );
        tabbedPane_TimerFont_PanelLayout.setVerticalGroup(
                tabbedPane_TimerFont_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabbedPane_TimerFont_PanelLayout.createSequentialGroup()
                                .addComponent(timerFontPanel_Key_Font_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(tabbedPane_TimerFont_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timerFontPanel_Key_Size_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerFontPanel_Value_Size_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerFontPanel_IsBold_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
        );

        timerWindowEditor_TabbedPane.addTab("Шрифт", tabbedPane_TimerFont_Panel);

        timerWindowEditor_BackgroudTransparency_Slider.setEnabled(false);
        timerWindowEditor_BackgroudTransparency_Slider.setInverted(true);
        timerWindowEditor_BackgroudTransparency_Slider.setMaximumSize(new java.awt.Dimension(312, 20));
        timerWindowEditor_BackgroudTransparency_Slider.setMinimumSize(new java.awt.Dimension(312, 20));
        timerWindowEditor_BackgroudTransparency_Slider.setPreferredSize(new java.awt.Dimension(312, 20));
        timerWindowEditor_BackgroudTransparency_Slider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                timerWindowEditor_BackgroudTransparency_SliderMouseEntered(evt);
            }
        });

        timerWindowEditor_BackgroudTransparency_Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timerWindowEditor_BackgroudTransparency_Label.setText("Прозрачность фона");
        timerWindowEditor_BackgroudTransparency_Label.setMaximumSize(new java.awt.Dimension(312, 17));
        timerWindowEditor_BackgroudTransparency_Label.setMinimumSize(new java.awt.Dimension(312, 17));
        timerWindowEditor_BackgroudTransparency_Label.setPreferredSize(new java.awt.Dimension(312, 17));

        javax.swing.GroupLayout rootPanel_TimerWindowEditor_PanelLayout = new javax.swing.GroupLayout(rootPanel_TimerWindowEditor_Panel);
        rootPanel_TimerWindowEditor_Panel.setLayout(rootPanel_TimerWindowEditor_PanelLayout);
        rootPanel_TimerWindowEditor_PanelLayout.setHorizontalGroup(
                rootPanel_TimerWindowEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rootPanel_TimerWindowEditor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rootPanel_TimerWindowEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(timerWindowEditor_TabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerWindowEditor_BackgroudTransparency_Slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(rootPanel_TimerWindowEditor_PanelLayout.createSequentialGroup()
                                                .addComponent(timerWindowEditor_Key_sizeX_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerWindowEditor_Value_sizeX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerWindowEditor_Key_sizeY_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerWindowEditor_Value_sizeY_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(timerWindowEditor_sizeDescription_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(timerWindowEditor_BackgroudTransparency_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rootPanel_TimerWindowEditor_PanelLayout.setVerticalGroup(
                rootPanel_TimerWindowEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rootPanel_TimerWindowEditor_PanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rootPanel_TimerWindowEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timerWindowEditor_Key_sizeY_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerWindowEditor_Value_sizeX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerWindowEditor_Value_sizeY_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerWindowEditor_sizeDescription_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timerWindowEditor_Key_sizeX_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(timerWindowEditor_BackgroudTransparency_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timerWindowEditor_BackgroudTransparency_Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(timerWindowEditor_TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        rootPanel_TimerWindowEditorOn_CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        rootPanel_TimerWindowEditorOn_CheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rootPanel_TimerWindowEditorOn_CheckBox.setText("Настроить окно таймера");
        rootPanel_TimerWindowEditorOn_CheckBox.setMaximumSize(new java.awt.Dimension(208, 24));
        rootPanel_TimerWindowEditorOn_CheckBox.setMinimumSize(new java.awt.Dimension(208, 24));
        rootPanel_TimerWindowEditorOn_CheckBox.setPreferredSize(new java.awt.Dimension(208, 24));
        rootPanel_TimerWindowEditorOn_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootPanel_TimerWindowEditorOn_CheckBoxActionPerformed(evt);
            }
        });

        rootPanel_TimerWindowEditorCheck_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rootPanel_TimerWindowEditorCheck_Button.setText("Проверить");
        rootPanel_TimerWindowEditorCheck_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rootPanel_TimerWindowEditorCheck_Button.setMaximumSize(new java.awt.Dimension(114, 24));
        rootPanel_TimerWindowEditorCheck_Button.setMinimumSize(new java.awt.Dimension(114, 24));
        rootPanel_TimerWindowEditorCheck_Button.setPreferredSize(new java.awt.Dimension(114, 24));
        rootPanel_TimerWindowEditorCheck_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootPanel_TimerWindowEditorCheck_ButtonActionPerformed(evt);
            }
        });

        offsetLabel1.setMaximumSize(new java.awt.Dimension(332, 23));
        offsetLabel1.setMinimumSize(new java.awt.Dimension(332, 23));
        offsetLabel1.setPreferredSize(new java.awt.Dimension(332, 23));

        offsetLabel0.setMaximumSize(new java.awt.Dimension(332, 23));
        offsetLabel0.setMinimumSize(new java.awt.Dimension(332, 23));
        offsetLabel0.setPreferredSize(new java.awt.Dimension(332, 23));

        rootPanel_Save_Button.setBackground(new java.awt.Color(0, 153, 102));
        rootPanel_Save_Button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rootPanel_Save_Button.setText("Сохранить");
        rootPanel_Save_Button.setMaximumSize(new java.awt.Dimension(340, 32));
        rootPanel_Save_Button.setMinimumSize(new java.awt.Dimension(340, 32));
        rootPanel_Save_Button.setPreferredSize(new java.awt.Dimension(340, 32));
        rootPanel_Save_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootPanel_Save_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rootPanel_SoundEditor_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rootPanel_Save_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rootPanel_TimerWindowEditor_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(rootPanel_TimerWindowEditorOn_CheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(rootPanel_TimerWindowEditorCheck_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(rootPanel_SoundEditorOn_CheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(rootPanel_SoundEditorCheck_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(offsetLabel0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rootPanel_TimerEditor_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(offsetLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rootPanel_TimerEditor_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(offsetLabel0, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rootPanel_SoundEditorOn_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rootPanel_SoundEditorCheck_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(rootPanel_SoundEditor_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(offsetLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rootPanel_TimerWindowEditorOn_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rootPanel_TimerWindowEditorCheck_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(rootPanel_TimerWindowEditor_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rootPanel_Save_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }


    private void soundEditor_SecondSoundOn_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) { }

    private void rootPanel_SoundEditorOn_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(rootPanel_SoundEditorOn_CheckBox.isSelected()) {
            for(Component component : rootPanel_SoundEditor_Panel.getComponents()) {
                component.setEnabled(true);
            }
        }
        else {
            for(Component component : rootPanel_SoundEditor_Panel.getComponents()) {
                component.setEnabled(false);
            }
        }
    }

    private void backgroundColorPanel_ToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void backgroundColorPanel_ToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void backgroundColorPanel_SelectCustom_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void timerColorPanel_ToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void timerColorPanel_ToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    private void rootPanel_TimerWindowEditorOn_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(rootPanel_TimerWindowEditorOn_CheckBox.isSelected()) {
            timerWindowEditor_TabbedPane.setSelectedIndex(0);
            for(Component component : rootPanel_TimerWindowEditor_Panel.getComponents()) {
                component.setEnabled(true);
            }
            for(Component component : timerWindowEditor_TabbedPane.getComponents()) {
                component.setEnabled(true);
            }
            for(Component component : tabbedPane_BackgroundColor_Panel.getComponents()) {
                component.setEnabled(true);
            }
        }
        else {
            timerWindowEditor_TabbedPane.setSelectedIndex(0);
            for(Component component : rootPanel_TimerWindowEditor_Panel.getComponents()) {
                component.setEnabled(false);
            }
            for(Component component : timerWindowEditor_TabbedPane.getComponents()) {
                component.setEnabled(false);
            }
            for(Component component : tabbedPane_BackgroundColor_Panel.getComponents()) {
                component.setEnabled(false);
            }
        }
    }

    private void timerColorPanel_SelectCustom_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void timerFontPanel_IsBold_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rootPanel_SoundEditorCheck_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(isSoundCheckButtonBlocked || !rootPanel_SoundEditorOn_CheckBox.isSelected()) { return; }

        doCheckActionForMapComponents();
        currentTimerForm = WindowsCreator.getInstance().getNewTimerForm(timerSetupPropertyFile, true);
        currentTimerForm.setVisible(false);
        currentTimerForm.startSoundSignalTest(rootPanel_SoundEditorOn_CheckBox.isSelected(), soundEditor_SecondSoundOn_CheckBox.isSelected(),
                MAIN_SOUND_PATH, SECOND_SOUND_PATH, rootPanel_SoundEditorCheck_Button);
        currentTimerForm.setSoundVolume(100 - soundEditor_VolumeEditor_Slider.getValue());

        //Таймер для блокировки кнопки
        Thread buttonBlockTimer = new Thread(new Runnable() {

            @Override
            public void run() {
                isSoundCheckButtonBlocked = true;
                int time = soundEditor_SecondSoundOn_CheckBox.isSelected() ?  4 : 1;

                while (time > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                    time--;
                }

                isSoundCheckButtonBlocked = false;
            }
        });
        buttonBlockTimer.start();
    }

    private void rootPanel_TimerWindowEditorCheck_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        doCheckActionForMapComponents();

        if(currentTimerForm != null && currentTimerForm.getJFrame().isDisplayable()) {
            currentTimerForm.getJFrame().dispose();
        }

        currentTimerForm = WindowsCreator.getInstance().getNewTimerForm(timerSetupPropertyFile, true);

        sendCurrentSettingsToTheTimerWindow();

        currentTimerForm.rebuildJFrame(true);
        currentTimerForm.setVisible(true);

        MainForm.setCurrentTimerForm(currentTimerForm);
    }


    private void rootPanel_Save_ButtonActionPerformed(java.awt.event.ActionEvent evt) {

        doCheckActionForMapComponents();

        if(currentTimerForm != null && currentTimerForm.getJFrame().isDisplayable()) {
            currentTimerForm.getJFrame().dispose();
        }

        currentTimerForm = WindowsCreator.getInstance().getNewTimerForm(timerSetupPropertyFile, true);

        this.dispose();

        sendCurrentSettingsToTheTimerWindow();

        currentTimerForm.rebuildJFrame(true);
        currentTimerForm.saveTimerSettingsToMemory();
        currentTimerForm.setVisible(true);

        parentMainForm.enableButtons("Для управления воспользуйтесь кнопками справа", new java.awt.Color(255, 255, 153));

        MainForm.setCurrentTimerForm(currentTimerForm);
    }


    private void soundEditor_DownloadMainSound_ButtonActionPerformed(java.awt.event.ActionEvent evt) {

        File file = null;
        int receiveOptionVal = fileChooser.showOpenDialog(this);

        if(receiveOptionVal == JFileChooser.APPROVE_OPTION) {
          file = fileChooser.getSelectedFile();
        }
        else { return; }

        if(file.canRead() && !file.isHidden()) {
            try {
                String[] nameParts = file.getName().split("\\.");
                String fileExtension = nameParts[nameParts.length - 1];

                if(!fileExtension.toLowerCase(Locale.ROOT).equals("wav")) { return; }
                if(Files.isExecutable(Path.of(MAIN_SOUND_PATH))) { Files.delete(Path.of(MAIN_SOUND_PATH)); }

                FileOutputStream outputStream = new FileOutputStream(MAIN_SOUND_PATH);
                Files.copy(Path.of(file.getAbsolutePath()), outputStream);
            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void soundEditor_DownloadSecondSound_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        File file = null;
        int receiveOptionVal = fileChooser.showOpenDialog(this);

        if(receiveOptionVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        else { return; }

        if(file.canRead() && !file.isHidden()) {
            try {
                String[] nameParts = file.getName().split("\\.");
                String fileExtension = nameParts[nameParts.length - 1];

                if(!fileExtension.toLowerCase(Locale.ROOT).equals("wav")) { return; }
                if(Files.isExecutable(Path.of(SECOND_SOUND_PATH))) { Files.delete(Path.of(SECOND_SOUND_PATH)); }

                FileOutputStream outputStream = new FileOutputStream(SECOND_SOUND_PATH);
                Files.copy(Path.of(file.getAbsolutePath()), outputStream);
            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void soundEditor_VolumeEditor_SliderMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println(soundEditor_VolumeEditor_Slider.getValue());
    }

    private void timerWindowEditor_BackgroudTransparency_SliderMouseEntered(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }


    private void sendCurrentSettingsToTheTimerWindow() {
        currentTimerForm.setHourValue(Integer.parseInt(timerEditorPanel_Value_Hours_TextField.getText()));
        currentTimerForm.setMinutValue(Integer.parseInt(timerEditorPanel_Value_Minuts_TextField.getText()));
        currentTimerForm.setSecondValue(Integer.parseInt(timerEditorPanel_Value_Seconds_TextField.getText()));

        currentTimerForm.setMainSoundOn(rootPanel_SoundEditorOn_CheckBox.isSelected());
        if(rootPanel_SoundEditorOn_CheckBox.isSelected()) {
            currentTimerForm.setSoundVolume(100 - soundEditor_VolumeEditor_Slider.getValue());
            currentTimerForm.setMainSoundFilePath(MAIN_SOUND_PATH);
            currentTimerForm.setSecondSoundOn(soundEditor_SecondSoundOn_CheckBox.isSelected());
            if(soundEditor_SecondSoundOn_CheckBox.isSelected()) {
                currentTimerForm.setSecondSoundFilePath(SECOND_SOUND_PATH);
            }
        }

        currentTimerForm.setWindowSettingsCustom(rootPanel_TimerWindowEditorOn_CheckBox.isSelected());
        if(rootPanel_TimerWindowEditorOn_CheckBox.isSelected()) {

            if(!checkIsJTextFieldEmpty(timerWindowEditor_Value_sizeX_TextField)) {
                currentTimerForm.setWindowWidth(Integer.parseInt(timerWindowEditor_Value_sizeX_TextField.getText()));
            }

            if(!checkIsJTextFieldEmpty(timerWindowEditor_Value_sizeY_TextField)) {
                currentTimerForm.setWindowHeight(Integer.parseInt(timerWindowEditor_Value_sizeY_TextField.getText()));
            }

            currentTimerForm.setBackgroundTransparency((int)(timerWindowEditor_BackgroudTransparency_Slider.getValue() * 2.55));

            if(!checkIsJTextFieldEmpty(backgroundColorPanel_Value_customR_TextField) &&
                    !checkIsJTextFieldEmpty(backgroundColorPanel_Value_customG_TextField) &&
                    !checkIsJTextFieldEmpty(backgroundColorPanel_Value_customB_TextField)) {
                currentTimerForm.setBackgroundColor(new Color(
                        Integer.parseInt(backgroundColorPanel_Value_customR_TextField.getText()),
                        Integer.parseInt(backgroundColorPanel_Value_customG_TextField.getText()),
                        Integer.parseInt(backgroundColorPanel_Value_customB_TextField.getText())
                ));
            }

            if(!checkIsJTextFieldEmpty(timerColorPanel_Value_customR_TextField) &&
                    !checkIsJTextFieldEmpty(timerColorPanel_Value_customG_TextField) &&
                    !checkIsJTextFieldEmpty(timerColorPanel_Value_customB_TextField)) {
                currentTimerForm.setTimerColor(new Color(
                        Integer.parseInt(timerColorPanel_Value_customR_TextField.getText()),
                        Integer.parseInt(timerColorPanel_Value_customG_TextField.getText()),
                        Integer.parseInt(timerColorPanel_Value_customB_TextField.getText())
                ));
            }

            currentTimerForm.setTimerFont(new java.awt.Font("Tahoma",
                    timerFontPanel_IsBold_CheckBox.isSelected() ? 1 : 0,
                    Integer.parseInt(timerFontPanel_Value_Size_TextField.getText())));
        }
    }


    /*
    ==========================================================================================================
    Алгоритм проверки значения полей в текущем JFrame. Используется встроенный абстрактный класс CheckAction
    ==========================================================================================================
     */

    /*
     Инициализация алгоритма. Добавляет MouseListener ко всем компонентам, чтобы после нажатия на компонент проверялись поля,
     добавленные методом setComponentCheckAction() в HashMap с компонентами и классами для их проверки (компонентов)
     */
    private void initTextFieldsCheckAlgorithm(JPanel rootPanel) {

        for(Component component : rootPanel.getComponents()) {
            if(component.getClass().equals(JPanel.class)) {
                initTextFieldsCheckAlgorithm((JPanel) component);
            }
            else if(component.getClass().equals(JTabbedPane.class)) {
                for(Component tabbedComponent : ((JTabbedPane) component).getComponents()) {
                    initTextFieldsCheckAlgorithm((JPanel) tabbedComponent);
                }
            }
            else if(component.getClass().equals(JTextField.class)) {
                component.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        doCheckActionForMapComponents();
                        setTrueToComponentCheckActionBooleanField(e.getComponent());
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
            }
            else {
                component.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        doCheckActionForMapComponents();
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
            }
        }
    }


    /*
    Инициализация классов CheckAction, которые хранят метод проверки для объекта JComponent, которому соответвует этот
    CheckAction. Соответствие объектов JComponent объектам CheckAction хранится в HashMap (componentCheckActionHashMap)
     */
    private void initComponentsCheckAction() {

        setComponentCheckAction(timerEditorPanel_Value_Hours_TextField, generateDefaultJTextFieldCheckAction(timerEditorPanel_Value_Hours_TextField, 0, 0, 23));
        setComponentCheckAction(timerEditorPanel_Value_Minuts_TextField, generateDefaultJTextFieldCheckAction(timerEditorPanel_Value_Minuts_TextField, 0, 0, 59));
        setComponentCheckAction(timerEditorPanel_Value_Seconds_TextField, generateDefaultJTextFieldCheckAction(timerEditorPanel_Value_Seconds_TextField, 10, 1, 59));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setComponentCheckAction(timerWindowEditor_Value_sizeX_TextField, generateDefaultJTextFieldCheckAction(timerWindowEditor_Value_sizeX_TextField, 200, 50, dimension.width));
        setComponentCheckAction(timerWindowEditor_Value_sizeY_TextField, generateDefaultJTextFieldCheckAction(timerWindowEditor_Value_sizeY_TextField, 100, 25, dimension.height));

        setComponentCheckAction(backgroundColorPanel_Value_customR_TextField, generateDefaultJTextFieldCheckAction(backgroundColorPanel_Value_customR_TextField, 240, 0, 255));
        setComponentCheckAction(backgroundColorPanel_Value_customG_TextField, generateDefaultJTextFieldCheckAction(backgroundColorPanel_Value_customG_TextField, 240, 0, 255));
        setComponentCheckAction(backgroundColorPanel_Value_customB_TextField, generateDefaultJTextFieldCheckAction(backgroundColorPanel_Value_customB_TextField, 240, 0, 255));

        setComponentCheckAction(timerColorPanel_Value_customR_TextField, generateDefaultJTextFieldCheckAction(timerColorPanel_Value_customR_TextField, 20, 0, 255));
        setComponentCheckAction(timerColorPanel_Value_customG_TextField, generateDefaultJTextFieldCheckAction(timerColorPanel_Value_customG_TextField, 20, 0, 255));
        setComponentCheckAction(timerColorPanel_Value_customB_TextField, generateDefaultJTextFieldCheckAction(timerColorPanel_Value_customB_TextField, 20, 0, 255));

        setComponentCheckAction(timerFontPanel_Value_Size_TextField, generateDefaultJTextFieldCheckAction(timerFontPanel_Value_Size_TextField, 60, 1, 200));
    }


    private CheckAction generateDefaultJTextFieldCheckAction(final JTextField component, final int defaultFieldValue, final int minInputValue, final int maxInputValue) {

        return new CheckAction() {
            @Override
            void doComponentCheckAction() {
                int inputValue = -1;

                String receivedText = component.getText();
                receivedText = receivedText.replaceAll("[^0-9]","");

                if(receivedText.length() > 4) { inputValue = -1; }
                else if(!receivedText.isEmpty()) { inputValue = Integer.parseInt(receivedText); }

                if(inputValue >= minInputValue && inputValue <= maxInputValue) { component.setText(String.valueOf(inputValue)); }
                else if(inputValue < minInputValue) { component.setText(String.valueOf(defaultFieldValue)); }
                else if(inputValue > maxInputValue) { component.setText(String.valueOf(defaultFieldValue)); }
            }
        };
    }


    /*
    Метод меняет значение проверочного поля в объекте CheckAction для переданного объекта JComponent.
    Значение меняется на isComponentChanged = true. После проверки значение возвращается в "false"
     */
    private void setTrueToComponentCheckActionBooleanField(Component component) {

        CheckAction checkAction;
        if((checkAction = componentCheckActionHashMap.get(component)) == null) {
            throw new NullPointerException("The component was not found:" + component.getName());
        }
        checkAction.isComponentChanged = true;
    }

    /*
    Метод запускает проверку для всех JComponent, находящихся в HashMap. Если значение проверочной
    переменной в соответствующем объекте CheckAction "false", значит объект JComponent уже был проверен
     */
    private void doCheckActionForMapComponents() {
        for(Map.Entry<Component, CheckAction> checkActionEntry : componentCheckActionHashMap.entrySet()) {

            CheckAction checkAction = checkActionEntry.getValue();
            if(checkAction.isComponentChanged) {
                checkAction.isComponentChanged = false;
                checkAction.doComponentCheckAction();
            }
        }
    }

    //Метод записывает пару значений JComponent - CheckAction в соответствующую коллекцию (HashMap)
    private void setComponentCheckAction(java.awt.Component component, CheckAction checkAction) {
        componentCheckActionHashMap.put(component, checkAction);
    }


    private abstract class CheckAction {
        boolean isComponentChanged;
        abstract void doComponentCheckAction();
    }

}
