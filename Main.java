package sub_string;

import javax.swing.*;


public class Main {
    /**
     * Launch the application.
     */

    public static void main(String[] args) {

        try {
            Controller window = new Controller();
            window.getFrame().setVisible(true);
            window.getFrame().setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eroare: " + e.getMessage());
        }
    }

}
