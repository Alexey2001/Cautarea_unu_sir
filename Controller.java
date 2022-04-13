package sub_string;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {

    private JFrame frame;
    private JTextField secventa;
    private JTextArea sir_cautat;
    private JTextArea rezultat;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Controller() {
        initialize();
    }

    private void initialize() {

        /* se creeaza o fereastra */
        setFrame(new JFrame("Cautarea unui sub-sir."));
        getFrame().getContentPane().setBackground(new Color(120, 240, 180));
        getFrame().setBounds(600, 250, 750, 750);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);

        /* se creeaza un camp pt introducerea sirului pe care dorim sa-l cautam */
        JLabel labelSecventa = new JLabel("Introduceti sirul pe care doriti sa-l cautati:");
        labelSecventa.setBounds(37, 50, 300, 36);
        getFrame().add(labelSecventa);

        secventa = new JTextField();
        secventa.setBounds(37, 85, 300, 30);
        getFrame().getContentPane().add(secventa);
        secventa.setColumns(10);

        /* se creeaza un camp pt introducerea secventei in cae dorim sa cautam sirul */
       
        JLabel labelSirCautat = new JLabel("Introduceti secventa in care doriti sa cautati sirul:");
        labelSirCautat.setBounds(37, 120, 300, 36);
        getFrame().add(labelSirCautat);

        sir_cautat = new JTextArea();
        sir_cautat.setBounds(37, 155, 300, 65);
        getFrame().getContentPane().add(sir_cautat);
        sir_cautat.setColumns(10);

        /* se creeaza un camp pt afisarea rezultatului */
        JLabel labelRezultat = new JLabel("Rezultat:");
        labelRezultat.setBounds(37, 170, 300, 148);
        getFrame().add(labelRezultat);

        rezultat = new JTextArea();
        rezultat.setEditable(false);
        JScrollPane scroll = new JScrollPane(rezultat);
        rezultat.setBounds(350, 85, 353, 300);
        scroll.setBounds(37, 260, 356, 200);
        getFrame().getContentPane().add(scroll);

        JButton btnSubmit = new JButton("Cauta"); //se creeaza butonul de cautare
        btnSubmit.addActionListener(this::actionButtonSubmit);

        btnSubmit.setBounds(50, 550, 150, 29);
        getFrame().getContentPane().add(btnSubmit);

        JButton btnClear = new JButton("Sterge"); //se creeaza butonul de stergere
        btnClear.addActionListener(this::actionButtonClear);

        btnClear.setBounds(200, 550, 106, 29);
        getFrame().getContentPane().add(btnClear);
    }

    private void actionButtonSubmit(ActionEvent arg0) {

        String regex = secventa.getText();
        String str = sir_cautat.getText();
        try {
            if (!regex.isEmpty() && !str.isEmpty()) {
                final Pattern pattern;
                /*
                  am creeat o instanta Pattern folosid o metoda Pattern.compile
                */

                pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                /*
                  metoda matcher() este folosita pt a cauta sirul in secventa
                  metoda find() returneaza true daca modelul a fost gasit in sir si false daca nu a fost gasit
                  */
                final Matcher matcher = pattern.matcher(str);
                rezultat.setText(null);

                if (!matcher.find()) {
                	rezultat.setForeground(Color.RED);
                	rezultat.append("\n\nSirul introdus nu a fost gasit in secventa.\n\n");
                } else {
                	rezultat.setForeground(Color.GREEN);
                        /*
                          in cazul in care in secventa pot fi gasite mai multe potriviri, metoda find() o va gasi pe prima,
                           iar apoi pentru fiecare apel ulterioar la find() va trece la urmatoarea potrivire
                          Metodele start() si end() vor oferi pozitii(indecsi) in text unde sncepe si se termina
                          potrivirea gasita
                          */
                	rezultat.append("\nPotrivirea 1 de la pozitia " + matcher.start() + " pana la pozitia " + matcher.end()  +"." + "\n");
                    int value = 2;
                    if (matcher.find()) {
                        do {
                        	rezultat.append("\nPotrivirea " + value + " de la pozitia" + matcher.start() + " pana la pozitia " + matcher.end() +"." + "\n");
                            value = value + 1;
                        } while (matcher.find());
                    }
                    switch (value--) {
                        case 1 -> rezultat.append("\n " + value + " potrivire. ");
                        default -> rezultat.append("\n " + value + " potriviri. ");
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Eroare: " + exception.getMessage());
        }
    }

    private void actionButtonClear(ActionEvent arg0) {
    	rezultat.setText(null);
        secventa.setText(null);
        sir_cautat.setText(null);
        
    }
}
