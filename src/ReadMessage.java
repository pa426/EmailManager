
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ReadMessage extends javax.swing.JFrame {

    DataBaseConfig DB = new DataBaseConfig();
    JTextField idFld = new JTextField();
    int id;
    String emailAddress = "";

    public ReadMessage(int id, String emailAddress) {
        
        this.id = id;
        this.emailAddress = emailAddress;
        add(idFld);
        idFld.setVisible(false);
        idFld.setText("" + id);
        initComponents();
        setLocationRelativeTo(null);
        String icon = getimageUrl();
        if (icon != null) {
            ImageIcon imageUrl = new ImageIcon(getClass().getResource("Images/" + icon));
            avatarLabel.setIcon(imageUrl);
        } else {
            ImageIcon imgThisImg = new ImageIcon(getClass().getResource("Images/noUser.png"));
            avatarLabel.setIcon(imgThisImg);
        }
        

    }

    private void displayMessage() {
        int idInt = Integer.parseInt(idFld.getText());
        Object[] data = DB.readData(idInt);
        textArea.setText("Subject: " + data[3]);
        textArea.append("\nFrom: " + data[4]);
        textArea.append("\nTo: " + data[1]);
        textArea.append("\nDate: " + data[6]);
        textArea.append("\n\nPriority: " + data[0]);
        textArea.append("\nLabel: " + data[2]);
        textArea.append("\n\nMessage: \n" + data[5]);

    }

    private String getimageUrl() {
        int idInt = Integer.parseInt(idFld.getText());
        Object[] data1 = DB.readData(idInt);
        String emailAdress = (String) data1[1];
        Object[] data = DB.getlogInData(emailAdress);
        String imageUrlshort = (String) data[4];
        return imageUrlshort;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTf = new javax.swing.JTextField();
        priorityCombo = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        avatarLabel = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Read Message");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("Set priority:");

        labelTf.setText("Enter a label...");
        labelTf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTfMouseClicked(evt);
            }
        });
        labelTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTfActionPerformed(evt);
            }
        });
        labelTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                labelTfKeyPressed(evt);
            }
        });

        priorityCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "*", "**", "***", "****", "*****" }));
        priorityCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priorityComboMouseClicked(evt);
            }
        });
        priorityCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityComboActionPerformed(evt);
            }
        });

        jButton1.setText("Reset Label");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Close");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/deleteB.png"))); // NOI18N
        jButton4.setText("Delete mesage");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Set Label");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);
        displayMessage();

        avatarLabel.setText("            ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTf)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(priorityCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(priorityCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(labelTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton1))
                        .addGap(28, 28, 28)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void priorityComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityComboActionPerformed

        priorityCombo.addActionListener((ActionEvent e) -> {
            String newPriority = "";
            String s = (String) priorityCombo.getSelectedItem();//get the selected item

            switch (s) {//check for a match
                case " ":
                    newPriority = " ";
                    break;
                case "*":
                    newPriority = "*";
                    break;
                case "**":
                    newPriority = "**";
                    break;
                case "***":
                    newPriority = "***";
                    break;
                case "****":
                    newPriority = "****";
                    break;
                case "*****":
                    newPriority = "*****";
                    break;
            }
            int idInt = Integer.parseInt(idFld.getText());
            DB.updatePriority(newPriority, idInt);
            displayMessage();
        }
        );
    }//GEN-LAST:event_priorityComboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int idInt = Integer.parseInt(idFld.getText());
        String newS = "";
        DB.updateLabel(newS, idInt);
        displayMessage();

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        String newLabel = labelTf.getText();
        if ("Enter a label...".equals(newLabel) || newLabel.isEmpty()) {
        } else {
            int idInt = Integer.parseInt(idFld.getText());
            DB.updateLabel(newLabel, idInt);
            displayMessage();
        }
        new EmailManager(emailAddress).setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3MouseClicked

    private void priorityComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priorityComboMouseClicked

    }//GEN-LAST:event_priorityComboMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void labelTfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labelTfKeyPressed


    }//GEN-LAST:event_labelTfKeyPressed

    private void labelTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelTfActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        String newLabel = labelTf.getText();
        if ("Enter a label...".equals(newLabel) || newLabel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid label !");

        } else {
            int idInt = Integer.parseInt(idFld.getText());
            DB.updateLabel(newLabel, idInt);
            displayMessage();
            labelTf.setText("");

        }

    }//GEN-LAST:event_jButton5MouseClicked

    private void labelTfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTfMouseClicked
        labelTf.setText("");
    }//GEN-LAST:event_labelTfMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete eMail ?", "Delete eMail", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            int idInt = Integer.parseInt(idFld.getText());
            DB.deleteData(idInt);
            new EmailManager(emailAddress).setVisible(true);
            dispose();

        }
    }//GEN-LAST:event_jButton4MouseClicked

    public static int main(int id, String emailAddress) {

        java.awt.EventQueue.invokeLater(() -> {
            new ReadMessage(id, emailAddress).setVisible(true);
        });
        return id;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatarLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField labelTf;
    private javax.swing.JComboBox priorityCombo;
    public javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
