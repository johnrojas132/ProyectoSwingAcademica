package modelo;

//Clase que guarda la info de un estudiante
public class Estudiante {

    private int id;
    private String nombre;
    private int edad;
    private String carrera;
    private String genero;
    private boolean activo;
    private String correo;
    private String telefono;
    private String observaciones;

    //crear un estudiante con todos sus datos
    public Estudiante(int id, String nombre, int edad, String carrera, String genero,
            boolean activo, String correo, String telefono, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
        this.genero = genero;
        this.activo = activo;
        this.correo = correo;
        this.telefono = telefono;
        this.observaciones = observaciones;
    }

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return nombre + " - " + carrera + " - " + correo;
    }
}
