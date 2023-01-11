package swingForms.timerWindow;

import lombok.Getter;
import swingForms.CreateNewTimerForm;
import swingForms.MainForm;

public class WindowsCreator {

    @Getter
    private MainForm currentMainForm;
    @Getter
    private CreateNewTimerForm currentCreateNewTimerForm;
    @Getter
    private TimerForm currentTimerForm;

    private static final WindowsCreator windowsCreator = new WindowsCreator();

    private WindowsCreator() {}

    public static WindowsCreator getInstance() { return windowsCreator; }

    public MainForm getNewMainForm() {
        return currentMainForm = new MainForm(this);
    }

    public CreateNewTimerForm getNewCreateNewTimerForm(String timerSetupPropertyFile, MainForm parentMainForm, TimerForm currentTimerForm) {
        return currentCreateNewTimerForm = new CreateNewTimerForm(timerSetupPropertyFile, parentMainForm, currentTimerForm, this);
    }

    public TimerForm getNewTimerForm(String timerSetupPropertyFile, boolean isNewTimerForm) {
        return currentTimerForm = new TimerForm(timerSetupPropertyFile, isNewTimerForm, this);
    }
}
