package proyectoswingacademica;


import presentacion.MainFrame;
import javax.swing.SwingUtilities;

public class ProyectoSwingAcademica {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}