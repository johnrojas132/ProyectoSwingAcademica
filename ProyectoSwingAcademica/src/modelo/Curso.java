/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Chess
 */
public class Curso {
    
    // 1. Atributos(propiedades del curso)
    private int id;
    private String codigo;
    private String nombre;
    private int creditos;
    private String profesor;
    private boolean activo;

    // 2. Constructor
    //Se utiliza para crear un objeto Curso con todos sus atributos
    public Curso(int id, String codigo, String nombre,
                 int creditos, String profesor, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.profesor = profesor;
        this.activo = activo;
    }

    // 3. Getters y Setters
    //Permiten acceder y modificar los atributos de forma controlada
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public String getProfesor() { return profesor; }
    public void setProfesor(String profesor) { this.profesor = profesor; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}
