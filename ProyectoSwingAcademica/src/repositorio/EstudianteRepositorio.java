package repositorio;

import modelo.Estudiante;
import java.util.*;

public class EstudianteRepositorio {

    private List<Estudiante> estudiantes;
    private Set<String> correos;
    private int contadorId; //contador para ids 

    public EstudianteRepositorio() {
        estudiantes = new ArrayList<>();
        correos = new HashSet<>();
        contadorId = 1; //el contador
    }
    
    //asignar id 
    public void agregar(Estudiante e) {
        
        e.setId(contadorId++);
        estudiantes.add(e);
        correos.add(e.getCorreo());
    }
    
   // busca por id para poder editar
    public void editar(Estudiante e) {
        
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId() == e.getId()) {
                estudiantes.set(i, e);
                break;
            }
        }
    }

    public void eliminar(Estudiante e) {
        estudiantes.removeIf(est -> est.getId() == e.getId());
        correos.remove(e.getCorreo());
    }

    public List<Estudiante> listar() {
        return estudiantes;
    }

    //Busca estudiante por correo
    public Estudiante buscarPorCorreo(String correo) {
        for (Estudiante e : estudiantes) {
            if (e.getCorreo().equalsIgnoreCase(correo)) {
                return e;
            }
        }
        return null;
    }

    public List<Estudiante> buscar(String criterio) {
        List<Estudiante> resultado = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e.getNombre().toLowerCase().contains(criterio.toLowerCase())
                    || e.getCarrera().toLowerCase().contains(criterio.toLowerCase())
                    || (criterio.equalsIgnoreCase("activo") && e.isActivo())
                    || (criterio.equalsIgnoreCase("inactivo") && !e.isActivo())) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    public void ordenarPorNombre() {
        estudiantes.sort(Comparator.comparing(Estudiante::getNombre));
    }

    public void ordenarPorEdad() {
        estudiantes.sort(Comparator.comparingInt(Estudiante::getEdad));
    }

    public boolean existeCorreo(String correo) {
        return correos.contains(correo);
    }
}