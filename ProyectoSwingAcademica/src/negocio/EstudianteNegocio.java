package negocio;

import modelo.Estudiante;
import repositorio.EstudianteRepositorio;
import excepciones.*;

import java.util.List;

public class EstudianteNegocio {

    private EstudianteRepositorio repo;

    public EstudianteNegocio() {
        repo = new EstudianteRepositorio();
    }

    //metodo para agregar un estudiante con validaciones
    public void agregar(Estudiante e) throws DatoInvalidoException, RegistroDuplicadoException {
        if (e.getNombre() == null || e.getNombre().length() < 10) {
            throw new DatoInvalidoException("El nombre es obligatorio y debe tener al menos 10 caracteres ");
        }
        if (e.getEdad() <= 3) {
            throw new DatoInvalidoException("La edad debe ser mayor que 3 ");
        }
        if (e.getCorreo() == null || !e.getCorreo().contains("@")) {
            throw new DatoInvalidoException("El correo no tiene formato valido ");
        }
        if (!e.getTelefono().matches("\\d+")) {
            throw new DatoInvalidoException("El telefono debe ser numero ");
        }
        if (repo.existeCorreo(e.getCorreo())) {
            throw new RegistroDuplicadoException("Ya existe un estudiante con este correo ");
        }
        repo.agregar(e);
    }

    //Validaciones
    public void editar(Estudiante e) throws DatoInvalidoException {
        if (e.getNombre() == null || e.getNombre().length() < 3) {
            throw new DatoInvalidoException("El nombre es obligatorio y debe tener al menos 3 caracteres ");
        }
        if (e.getEdad() <= 3) {
            throw new DatoInvalidoException("La edad debe ser mayor que 3 ");
            
        }
        if (e.getEdad() <= 0) {
            throw new DatoInvalidoException("La edad debe ser mayor que 0 ");
        }
        if (e.getCorreo() == null || !e.getCorreo().contains("@")) {
            throw new DatoInvalidoException("El correo no tiene formato valido ");
        }
        if (!e.getTelefono().matches("\\d+")) {
            throw new DatoInvalidoException("El telefono debe ser numerico ");
        }

        try {
            if (repo.buscarPorCorreo(e.getCorreo()) != null) {
                throw new RegistroDuplicadoException(
                        "Ya existe un estudiante con este correo");
            }
        } catch (RegistroDuplicadoException ex) {
            System.out.println(ex.getMessage());
        }
        repo.agregar(e);
    }

   

    //Getters y setters
    public void eliminar(Estudiante e) {
        repo.eliminar(e);
    }

    public Estudiante buscarPorCorreo(String correo) {
        return repo.buscarPorCorreo(correo);
    }

    public List<Estudiante> buscarPorNombreOCarreraOEstado(String criterio) {
        return repo.buscar(criterio);
    }

    public void ordenarPorNombre() {
        repo.ordenarPorNombre();
    }

    public void ordenarPorEdad() {
        repo.ordenarPorEdad();
    }

    public List<Estudiante> listar() {
        return repo.listar();
    }

}