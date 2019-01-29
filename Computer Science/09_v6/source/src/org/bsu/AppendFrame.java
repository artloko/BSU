package org.bsu;

import javax.swing.*;

public class AppendFrame extends JFrame {

    JLabel jLabelDrugstore;
    JLabel jLabelName;
    JLabel jLabelAmount;
    JLabel jLabelCost;
    JLabel jLabelDateOfReceipt;
    JLabel jLabelShelfDate;

    JTextField jTextFieldDrugstore;
    JTextField jTextFieldName;
    JTextField jTextFieldAmount;
    JTextField jTextFieldCost;

    @Override
    protected void frameInit() {
        super.frameInit();

        jTextFieldDrugstore = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAmount = new JTextField();
        jTextFieldCost = new JTextField();



        jLabelDrugstore = new JLabel("Drugstore:");
        jLabelDrugstore.setLabelFor(jTextFieldDrugstore);

        jLabelName = new JLabel("Name:");
        jLabelName.setLabelFor(jTextFieldName);

        jLabelAmount = new JLabel("Amount:");
        jLabelAmount.setLabelFor(jTextFieldAmount);

        jLabelCost = new JLabel("Cost:");
        jLabelCost.setLabelFor(jTextFieldCost);

        jLabelDateOfReceipt = new JLabel("Date of receipt:");
        jLabelShelfDate = new JLabel("Shelf Date:");

    }
}
