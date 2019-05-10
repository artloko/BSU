package org.bsu;

import org.bsu.source.Drugs;
import org.bsu.source.KeyComp;
import org.bsu.source.KeyCompReverse;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class KeyFrame extends JFrame {
    private JPanel jPanel;
    private JLabel jLabel;
    private JButton jButton;
    private JTextField jTextField;

    enum FUNCTION{
        DELETE, FIND
    }

    enum FIELD{
        DS, N, DR, SD
    }

    enum DIRECTION{
        STRAIGHT, REVERSED, NONE
    }

    DIRECTION direction;
    FUNCTION function;
    FIELD field;

    @Override
    protected void frameInit() {
        super.frameInit();

        jPanel = new JPanel();
        jLabel = new JLabel("Enter your key here:");
        jButton = new JButton("Enter");
        jTextField = new JTextField(6);

        this.setContentPane(jPanel);
        this.setMinimumSize(new Dimension(250, 100));
        this.setResizable(false);

        jLabel.setLabelFor(jTextField);

        jPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        jTextField.setMinimumSize(new Dimension(100, 50));

        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String sField;
                if (field == FIELD.DS)
                    sField = "ds";
                else if (field == FIELD.N)
                    sField = "n";
                else if (field == FIELD.DR)
                    sField = "dr";
                else
                    sField = "sd";

                try {
                    if (function == FUNCTION.DELETE){
                        Drugs.deleteFile(new String[] {"DOESN'T MATTER", sField, jTextField.getText()});
                    }else{
                        if (direction == DIRECTION.NONE)
                            Drugs.findByKey(new String[] {"DOESN'T MATTER", sField, jTextField.getText()});
                        else if(direction == DIRECTION.STRAIGHT)
                            Drugs.findByKey(new String[] {"DOESN'T MATTER", sField, jTextField.getText()}, new KeyCompReverse());
                        else
                            Drugs.findByKey(new String[] {"DOESN'T MATTER", sField, jTextField.getText()}, new KeyComp());

                    }
                } catch ( Exception e ) {
                    System.err.println( "Run/time error: " + e );
                    e.printStackTrace();
                    System.exit(1);
                }
                setVisible(false);
            }
        });

        FlowLayout flowLayout = new FlowLayout();

        this.setLayout(flowLayout);

        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jButton);

        jPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    public void setField(FIELD field) {
        this.field = field;
    }

    public void setFunction(FUNCTION function) {
        this.function = function;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }
}
