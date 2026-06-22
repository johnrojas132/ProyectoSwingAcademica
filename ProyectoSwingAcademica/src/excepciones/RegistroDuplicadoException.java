package excepciones;

//Excepcion para cuando alguien intenta registrar
public class RegistroDuplicadoException extends Exception {
    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
    }
}