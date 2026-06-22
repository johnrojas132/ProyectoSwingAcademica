package excepciones;

//exepcion para cuando se mete un dato que no es
public class DatoInvalidoException extends Exception {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
