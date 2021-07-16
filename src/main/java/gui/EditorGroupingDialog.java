package gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class EditorGroupingDialog extends DialogWrapper {

    private final String[] colors = {"-4115167","-5611482","-4018651","-15232196"};
    private JPanel centerPanel;
    private JTextField groupNameTextField;
    private ButtonGroup radioButtonGroup;
    private ActionListener buttonListener;

    public EditorGroupingDialog(@Nullable Project project) {
        super(project);
        super.setTitle("Customize Tab Group");
        super.setCancelButtonText("Remove Grouping");
        super.setOKButtonText("Add to Group");
        radioButtonGroup = new ButtonGroup();
        buttonListener = createButtonListener();
        this.addContent(centerPanel);
        super.init();
    }

    @Override
    public @Nullable JComponent createCenterPanel() {
        return centerPanel;
    }

    private void addTextBox(JPanel panel) {
        groupNameTextField = new JTextField("Group name...");
        groupNameTextField.getDocument().addDocumentListener(createDocumentListener());
        panel.add(groupNameTextField, createConstraints(panel.getComponentCount()));
    }

    private void addContent(JPanel panel){
        panel.setLayout(new GridLayoutManager(colors.length+1, 1));
        addButtons(panel);
        addTextBox(panel);
    }

    private void addButtons(JPanel panel) {
        for (int i = 0; i < colors.length; i++) {
            JRadioButton button = new JRadioButton("Group-"+(i+1));
            button.setBackground(Color.decode(colors[i]));
            button.addActionListener(buttonListener);
            radioButtonGroup.add(button);
            panel.add(button, createConstraints(i));
        }
    }

    private GridConstraints createConstraints(int yPosition){
        GridConstraints c = new GridConstraints();
        c.setFill(GridConstraints.FILL_HORIZONTAL);
        c.setColumn(0);
        c.setRow(yPosition);
        return c;
    }

    private ActionListener createButtonListener(){
        return e -> {
            AbstractButton button = (AbstractButton) e.getSource();
            groupNameTextField.setText(button.getText());
        };
    }

    private DocumentListener createDocumentListener(){
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSelectedGroupName(groupNameTextField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSelectedGroupName(groupNameTextField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSelectedGroupName(groupNameTextField.getText());
            }
        };
    }

    private void changeSelectedGroupName(String text) {
        Enumeration<AbstractButton> enumeration = radioButtonGroup.getElements();
        while (enumeration.hasMoreElements()){
            AbstractButton button = enumeration.nextElement();
            if (button.isSelected()){
                button.setText(text);
                break;
            }
        }
    }

}
