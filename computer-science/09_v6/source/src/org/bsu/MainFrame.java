package org.bsu;


import org.bsu.source.Drugs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel dataPanel;
    private JTextArea dataTextArea;
    private JMenuBar jMenuBar;

    private JScrollPane jScrollPane;

    private JToolBar jToolBar;
    private JLabel statusLabel;

    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenu5;

    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;

    private JMenuItem jMenuItem21;

    private JMenu jMenu22;
    private JMenuItem jMenuItem221;
    private JMenuItem jMenuItem222;
    private JMenuItem jMenuItem223;
    private JMenuItem jMenuItem224;


    private JMenuItem jMenuItem31;

    private JMenuItem jMenu32;
    private JMenuItem jMenuItem321;
    private JMenuItem jMenuItem322;
    private JMenuItem jMenuItem323;
    private JMenuItem jMenuItem324;
    private JMenuItem jMenu33;

    private JMenuItem jMenuItem331;
    private JMenuItem jMenuItem332;
    private JMenuItem jMenuItem333;
    private JMenuItem jMenuItem334;

    private JMenu jMenu41;
    private JMenuItem jMenuItem411;
    private JMenuItem jMenuItem412;
    private JMenuItem jMenuItem413;
    private JMenuItem jMenuItem414;

    private JMenu jMenu42;
    private JMenuItem jMenuItem421;
    private JMenuItem jMenuItem422;
    private JMenuItem jMenuItem423;
    private JMenuItem jMenuItem424;

    private JMenu jMenu43;
    private JMenuItem jMenuItem431;
    private JMenuItem jMenuItem432;
    private JMenuItem jMenuItem433;
    private JMenuItem jMenuItem434;

    private JMenuItem jMenuItem51;

    MainFrame(){
        frameInit();
    }

    @Override
    protected void frameInit() {
        super.frameInit();

        this.setMinimumSize(new Dimension(450,500));
        dataPanel = new JPanel();
        dataTextArea = new JTextArea();

        jScrollPane = new JScrollPane(dataTextArea);

        Console.redirectOutput(dataTextArea);

        jMenuBar = new JMenuBar();

        jToolBar = new JToolBar();
        statusLabel = new JLabel();

        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jMenu3 = new JMenu();
        jMenu4 = new JMenu();
        jMenu5 = new JMenu();

        jMenuItem11 = new JMenuItem();
        jMenuItem12 = new JMenuItem();

        jMenuItem21 = new JMenuItem();

        jMenu22 = new JMenu();
        jMenuItem221 = new JMenuItem();
        jMenuItem222 = new JMenuItem();
        jMenuItem223 = new JMenuItem();
        jMenuItem224 = new JMenuItem();

        jMenuItem31 = new JMenuItem();

        jMenu32 = new JMenu();
        jMenuItem321 = new JMenuItem();
        jMenuItem322 = new JMenuItem();
        jMenuItem323 = new JMenuItem();
        jMenuItem324 = new JMenuItem();

        jMenu33 = new JMenu();
        jMenuItem331 = new JMenuItem();
        jMenuItem332 = new JMenuItem();
        jMenuItem333 = new JMenuItem();
        jMenuItem334 = new JMenuItem();

        jMenu41 = new JMenu();
        jMenuItem411 = new JMenuItem();
        jMenuItem412 = new JMenuItem();
        jMenuItem413 = new JMenuItem();
        jMenuItem414 = new JMenuItem();

        jMenu42 = new JMenu();
        jMenuItem421 = new JMenuItem();
        jMenuItem422 = new JMenuItem();
        jMenuItem423 = new JMenuItem();
        jMenuItem424 = new JMenuItem();

        jMenu43 = new JMenu();
        jMenuItem431 = new JMenuItem();
        jMenuItem432 = new JMenuItem();
        jMenuItem433 = new JMenuItem();
        jMenuItem434 = new JMenuItem();

        jMenuItem51 = new JMenuItem();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drugs");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jToolBar.setRollover(true);
        jToolBar.add(statusLabel);

        jMenu1.setText("File");

        jMenuItem11.setText("Append data");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem12.setText("Append zipped data");
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar.add(jMenu1);


        jMenu2.setText("Delete");

        jMenuItem21.setText("Delete all");
        jMenuItem21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem21);

        jMenu22.setText("Delete by key");

        jMenuItem221.setText("DS");
        jMenuItem221.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem221MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem221.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem221ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem221);

        jMenuItem222.setText("N");
        jMenuItem222.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem222MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem222.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem222ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem222);

        jMenuItem223.setText("DR");
        jMenuItem223.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem223MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem223.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem223ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem223);

        jMenuItem224.setText("SD");
        jMenuItem224.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem224MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem224.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem224ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem224);

        jMenu2.add(jMenu22);

        jMenuBar.add(jMenu2);

        jMenu3.setText("Print");

        jMenuItem31.setText("Print all");
        jMenuItem31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenu32.setText("Print by key");

        jMenuItem321.setText("DS");
        jMenuItem321.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem321MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem321.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem321ActionPerformed(evt);
            }
        });
        jMenu32.add(jMenuItem321);

        jMenuItem322.setText("N");
        jMenuItem322.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem322MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem322.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem322ActionPerformed(evt);
            }
        });
        jMenu32.add(jMenuItem322);

        jMenuItem323.setText("DR");
        jMenuItem323.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem323MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem323.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem323ActionPerformed(evt);
            }
        });
        jMenu32.add(jMenuItem323);

        jMenuItem324.setText("SD");
        jMenuItem324.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem324MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem324.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem324ActionPerformed(evt);
            }
        });
        jMenu32.add(jMenuItem324);

        jMenu3.add(jMenu32);

        jMenu33.setText("Reversed print by key");

        jMenuItem331.setText("DS");
        jMenuItem331.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem331MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem331.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem331ActionPerformed(evt);
            }
        });
        jMenu33.add(jMenuItem331);

        jMenuItem332.setText("N");
        jMenuItem332.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem332MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem332.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem332ActionPerformed(evt);
            }
        });
        jMenu33.add(jMenuItem332);

        jMenuItem333.setText("DR");
        jMenuItem333.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem333MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem333.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem333ActionPerformed(evt);
            }
        });
        jMenu33.add(jMenuItem333);

        jMenuItem334.setText("SD");
        jMenuItem334.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem334MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem334.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem334ActionPerformed(evt);
            }
        });
        jMenu33.add(jMenuItem334);

        jMenu3.add(jMenu33);

        jMenuBar.add(jMenu3);

        jMenu4.setText("Find");

        jMenu41.setText("Find by key");

        jMenuItem411.setText("DS");
        jMenuItem411.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem411MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem411.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem411ActionPerformed(evt);
            }
        });
        jMenu41.add(jMenuItem411);

        jMenuItem412.setText("N");
        jMenuItem412.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem412MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem412.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem412ActionPerformed(evt);
            }
        });
        jMenu41.add(jMenuItem412);

        jMenuItem413.setText("DR");
        jMenuItem413.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem413MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem413.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem413ActionPerformed(evt);
            }
        });
        jMenu41.add(jMenuItem413);

        jMenuItem414.setText("SD");
        jMenuItem414.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem414MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem414.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem414ActionPerformed(evt);
            }
        });
        jMenu41.add(jMenuItem414);

        jMenu4.add(jMenu41);

        jMenu42.setText("Find more than key");

        jMenuItem421.setText("DS");
        jMenuItem421.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem421MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem421.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem421ActionPerformed(evt);
            }
        });
        jMenu42.add(jMenuItem421);

        jMenuItem422.setText("N");
        jMenuItem422.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem422MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem422.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem422ActionPerformed(evt);
            }
        });
        jMenu42.add(jMenuItem422);

        jMenuItem423.setText("DR");
        jMenuItem423.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem423MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem423.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem423ActionPerformed(evt);
            }
        });
        jMenu42.add(jMenuItem423);

        jMenuItem424.setText("SD");
        jMenuItem424.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem424MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem424.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem424ActionPerformed(evt);
            }
        });
        jMenu42.add(jMenuItem424);

        jMenu4.add(jMenu42);

        jMenu43.setText("Find less than key");

        jMenuItem431.setText("DS");
        jMenuItem431.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem431MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem431.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem431ActionPerformed(evt);
            }
        });
        jMenu43.add(jMenuItem431);

        jMenuItem432.setText("N");
        jMenuItem432.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem432MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem432.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem432ActionPerformed(evt);
            }
        });
        jMenu43.add(jMenuItem432);

        jMenuItem433.setText("DR");
        jMenuItem433.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem433MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem433.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem433ActionPerformed(evt);
            }
        });
        jMenu43.add(jMenuItem433);

        jMenuItem434.setText("SD");
        jMenuItem434.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem434MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem434.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTextArea.setText("");
                jMenuItem434ActionPerformed(evt);
            }
        });
        jMenu43.add(jMenuItem434);

        jMenu4.add(jMenu43);

        jMenuBar.add(jMenu4);

        jMenu5.setText("Help");
        jMenuItem51.setText("About");
        jMenuItem51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuItem51MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuItemMouseExited(evt);
            }
        });
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem51);

        jMenuBar.add(jMenu5);

        setJMenuBar(jMenuBar);


        dataTextArea.setText("Your results will be printed here...");
        dataTextArea.setEditable(false);
        dataTextArea.setMinimumSize(new Dimension(400, 450));
        dataPanel.add(dataTextArea);
        dataTextArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        dataPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jToolBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        setContentPane(dataPanel);
        getContentPane().setLayout(new BorderLayout());

        dataPanel.add(dataTextArea, BorderLayout.CENTER);
        dataPanel.add(new JPanel(), BorderLayout.NORTH);
        dataPanel.add(new JPanel(), BorderLayout.WEST);
        dataPanel.add(new JPanel(), BorderLayout.EAST);
        dataPanel.add(jToolBar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.appendFile(new String[]{"Doesn't matter", "drugs.txt"}, false);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Append data");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered

    private void jMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseExited
        // TODO add your handling code here:
        this.statusLabel.setText("");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseExited


    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.appendFile(new String[]{"Doesn't matter", "drugs.txt"}, true);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Append data, compress every record");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Drugs.deleteFile();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Clear all data");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem221ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // CREATE NEW FRAME
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.DELETE);
                keyFrame.setField(KeyFrame.FIELD.DS);
                keyFrame.setVisible(true);
            }
        });
    }
    private void jMenuItem221MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Clear data by Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem222ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // CREATE NEW FRAME
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.DELETE);
                keyFrame.setField(KeyFrame.FIELD.N);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem222MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Clear data by Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem223ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // CREATE NEW FRAME
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.DELETE);
                keyFrame.setField(KeyFrame.FIELD.DR);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem223MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Clear data by Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem224ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // CREATE NEW FRAME
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.DELETE);
                keyFrame.setField(KeyFrame.FIELD.SD);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem224MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Clear data by Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile();
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data unsorted");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem321ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // CREATE NEW FRAME
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "ds"}, false);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem321MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data sorted by Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem322ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "n"}, false);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem322MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data sorted by Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem323ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "dr"}, false);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem323MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data sorted by Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem324ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "sd"}, false);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem324MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data sorted by Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem331ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "ds"}, true);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem331MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data reverse sorted by Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem332ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "n"}, true);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem332MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data reverse sorted by Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem333ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "dr"}, true);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem333MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data reverse sorted by Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem334ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            Drugs.printFile(new String[]{"Doesn't matter", "sd"}, true);
        } catch ( Exception e ) {
            System.err.println( "Run/time error: " + e );
            e.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem334MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Print data reverse sorted by Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem411ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DS);
                keyFrame.setDirection(KeyFrame.DIRECTION.NONE);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem411MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find record by Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem412ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.N);
                keyFrame.setDirection(KeyFrame.DIRECTION.NONE);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem412MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find record by Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem413ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DR);
                keyFrame.setDirection(KeyFrame.DIRECTION.NONE);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem413MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find record by Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem414ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.SD);
                keyFrame.setDirection(KeyFrame.DIRECTION.NONE);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem414MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find record by Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered



    private void jMenuItem421ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DS);
                keyFrame.setDirection(KeyFrame.DIRECTION.STRAIGHT);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem421MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records > Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem422ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.N);
                keyFrame.setDirection(KeyFrame.DIRECTION.STRAIGHT);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem422MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records > Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem423ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DR);
                keyFrame.setDirection(KeyFrame.DIRECTION.STRAIGHT);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem423MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records > Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem424ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.SD);
                keyFrame.setDirection(KeyFrame.DIRECTION.STRAIGHT);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem424MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records > Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered



    private void jMenuItem431ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DS);
                keyFrame.setDirection(KeyFrame.DIRECTION.REVERSED);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem431MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records < Drugstore");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem432ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.N);
                keyFrame.setDirection(KeyFrame.DIRECTION.REVERSED);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem432MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records < Name");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem433ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.DR);
                keyFrame.setDirection(KeyFrame.DIRECTION.REVERSED);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem433MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records < Date of receipt");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem434ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KeyFrame keyFrame = new KeyFrame();
                keyFrame.setFunction(KeyFrame.FUNCTION.FIND);
                keyFrame.setField(KeyFrame.FIELD.SD);
                keyFrame.setDirection(KeyFrame.DIRECTION.REVERSED);
                keyFrame.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem434MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Find records < by Shelf Date");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered


    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Designed by @artloko");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem51MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseEntered
        // TODO add your handling code here:
        this.statusLabel.setText("Designer");
        this.statusLabel.repaint();
    }//GEN-LAST:event_jMenuItem1MouseEntered



    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
