/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;
import modelo.Curso;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Chess
 */
public class CursoRepositorio {
     // Lista que almacena todos los cursos
    private List<Curso> listaCursos = new ArrayList<>();
    
    // Set para controlar que no haya códigos duplicados
    private Set<String> codigosRegistrados = new HashSet<>();

    // Agregar un curso
    public void agregar(Curso curso) {
        listaCursos.add(curso);
        codigosRegistrados.add(curso.getCodigo());
    }

    // Verificar si un código ya existe
    public boolean existeCodigo(String codigo) {
        return codigosRegistrados.contains(codigo);
    }

    // Obtener todos los cursos
    public List<Curso> obtenerTodos() {
        return listaCursos;
    }

    // Eliminar un curso
    public void eliminar(Curso curso) {
        listaCursos.remove(curso);
        codigosRegistrados.remove(curso.getCodigo());
    }

    // Actualizar un curso
   public void actualizarPorCodigo(String codigoOriginal, Curso cursoNuevo) {
        for (int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getCodigo().equals(codigoOriginal)) {
            listaCursos.set(i, cursoNuevo);
            codigosRegistrados.remove(codigoOriginal);
            codigosRegistrados.add(cursoNuevo.getCodigo());
            break;
            }
        } 
    }
    
}
