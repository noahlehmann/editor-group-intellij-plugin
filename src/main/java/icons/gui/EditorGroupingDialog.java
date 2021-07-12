package icons.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditorGroupingDialog extends DialogWrapper {

    private JPanel centerPanel;
    private JRadioButton radioButton0;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JTextField groupNameTextField;
    private ButtonGroup groupingRadioButtonGroup;

    public EditorGroupingDialog(@Nullable Project project) {
        super(project);
        super.setTitle("Customize Tab Group");
        super.setCancelButtonText("Remove Grouping");
        super.setOKButtonText("Add to Group");
        groupNameTextField.addActionListener(e -> {
            String newText = groupNameTextField.getText();
        });
        super.init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return centerPanel;
    }

    public List<String> getGroupNames(){
        List<String> groupNames = new ArrayList<>();
        while (groupingRadioButtonGroup.getElements().hasMoreElements()){
            groupNames.add(groupingRadioButtonGroup.getElements().nextElement().getText());
        }
        return groupNames;
    }






}
