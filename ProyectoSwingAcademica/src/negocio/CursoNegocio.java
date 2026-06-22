/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;
import modelo.Curso;
import repositorio.CursoRepositorio;
import excepciones.DatoInvalidoException;
import excepciones.RegistroDuplicadoException;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Chess
 */
public class CursoNegocio {
    //Simula una base de datos
        private CursoRepositorio cursoRepositorio = new CursoRepositorio();

    public void agregar(Curso curso) throws DatoInvalidoException, RegistroDuplicadoException {
        
        // Validar código
        if (curso.getCodigo() == null || curso.getCodigo().trim().isEmpty()) {
            throw new DatoInvalidoException("El código es obligatorio.");
        }
        
        // Validar nombre
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre es obligatorio.");
        }
        
        // Validar créditos
        if (curso.getCreditos() <= 0) {
            throw new DatoInvalidoException("Los créditos deben ser mayores que 0.");
        }
        
        // Validar profesor
        if (curso.getProfesor() == null || curso.getProfesor().trim().isEmpty()) {
            throw new DatoInvalidoException("El profesor es obligatorio.");
        }
        
        // Validar duplicados
        if (cursoRepositorio.existeCodigo(curso.getCodigo())) {
            throw new RegistroDuplicadoException("Ya existe un curso con ese código.");
        }
        //si todo esta correcto, se agrega curso
        cursoRepositorio.agregar(curso);
    }
    //Metodo para eliminar un curso
    public void eliminar(Curso curso) {
    cursoRepositorio.eliminar(curso);
    }
    //Lista donde se guardan los resultados
    public List<Curso> buscar(String texto) {
    List<Curso> resultado = new java.util.ArrayList<>();
    
    //Recorrer todos los cursos
    for (Curso curso : cursoRepositorio.obtenerTodos()) {
        if (curso.getCodigo().toLowerCase().contains(texto.toLowerCase()) ||
            curso.getNombre().toLowerCase().contains(texto.toLowerCase())) {
            resultado.add(curso);
        }
    }
    
    return resultado;
    }
    //Metodo para actualizar un curso existente
    public void actualizar(String codigoOriginal, Curso curso) throws DatoInvalidoException {
    
    //validaciones para que sea obligatorio escribir cosas
    if (curso.getCodigo() == null || curso.getCodigo().trim().isEmpty()) {
        throw new DatoInvalidoException("El código es obligatorio.");
    }
    
    if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
        throw new DatoInvalidoException("El nombre es obligatorio.");
    }
    if (curso.getCreditos() <= 0) {
        throw new DatoInvalidoException("Los créditos deben ser mayores que 0.");
    }
    if (curso.getProfesor() == null || curso.getProfesor().trim().isEmpty()) {
        throw new DatoInvalidoException("El profesor es obligatorio.");
    }
    //Actualizar el curso usando su codigo original
    cursoRepositorio.actualizarPorCodigo(codigoOriginal, curso);
    }
    //Metodo para ordenar cursos por codigo
    public List<Curso> ordenarPorCodigo() {
    List<Curso> lista = cursoRepositorio.obtenerTodos();
    Collections.sort(lista, new Comparator<Curso>() {
        @Override
        public int compare(Curso c1, Curso c2) {
            return c1.getCodigo().compareTo(c2.getCodigo());
        }
    });
    return lista;
    }

    public List<Curso> ordenarPorNombre() {
    List<Curso> lista = cursoRepositorio.obtenerTodos();
    Collections.sort(lista, new Comparator<Curso>() {
        @Override
        public int compare(Curso c1, Curso c2) {
            return c1.getNombre().compareTo(c2.getNombre());
        }
    });
    return lista;
    }
    

    public List<Curso> obtenerTodos() {
        return cursoRepositorio.obtenerTodos();
    }
    
}
