package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.dtos.ReservaDTO;
import com.example.app.entities.Empleado;
import com.example.app.entities.Habitacion;
import com.example.app.entities.Hotel;
import com.example.app.repositories.IHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitacionService implements IHabitacionService{

    @Autowired
    private IHabitacionRepository repository;


    @Override
    public List<HabitacionDTO> mostrarHabitaciones() {
        return repository.findAll().stream()
                .map(this::conversorDTO)
                .toList();
    }

    @Override
    public HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO) {
        if(this.existeUsuario(habitacionDTO).isPresent()){
            return new HabitacionDTO();
        }else{
            Habitacion nuevo = this.conversorEntidad(habitacionDTO);
            Habitacion creado = repository.save(nuevo);
            return this.conversorDTO(creado);
        }
    }

    @Override
    public Optional<HabitacionDTO> existeUsuario(HabitacionDTO habitacionDTO) {
        return this.mostrarHabitaciones().stream()
                .filter(habitacionDTO1 -> habitacionDTO1.getHotel().getIdHotel().equals(habitacionDTO.getHotel().getIdHotel())
                && habitacionDTO1.getEmpleado().getIdentificadorEmpleado().equals(habitacionDTO.getEmpleado().getIdentificadorEmpleado()))
                .findFirst();
    }

    @Override
    public List<HabitacionDTO> verHabitacionesDispo(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

        //Buscar las habitaciones de dichos hoteles que se encuentran en ese lugar
        List<HabitacionDTO> hotelLugar = this.mostrarHabitaciones().stream().filter(habitacionDTO ->
                        habitacionDTO.getHotel().getLugar().equalsIgnoreCase(lugar))
                .toList();

        //Buscarlos de ese rango de fechas de habitaciones
        return hotelLugar.stream()
                .filter(habitacion -> !(fechaDesde.isAfter(habitacion.getFechaFin()) || fechaHasta.isBefore(habitacion.getFechaInicio())))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity verificacionListado(List<HabitacionDTO> listado) {
        if(listado.isEmpty()) return ResponseEntity.status(500).body("No hay habitaciones disponibles");
        else{
            return ResponseEntity.ok(listado);
        }
    }


    @Override
    public EmpleadoDTO habitacionEmpleados(Habitacion habitacion) {
        if(habitacion.getEmpleado().getHabitaciones() == null){
            return new EmpleadoDTO(habitacion.getEmpleado().getId_empleado(), null, null, null, null);
        }
        else return new EmpleadoDTO(habitacion.getEmpleado().getId_empleado(), habitacion.getEmpleado().getNombre(), habitacion.getEmpleado().getApellido(), null, null);
    }

    @Override
    public HotelDTO habitacionHotel(Habitacion habitacion) {
        if(habitacion.getHotel().getHabitaciones() == null){
            return new HotelDTO(habitacion.getHotel().getId_hotel(), null, null, null, null);
        }else return new HotelDTO(habitacion.getHotel().getId_hotel(), habitacion.getHotel().getCod_hotel(), habitacion.getHotel().getNombre(), habitacion.getHotel().getLugar(), null);
    }

    @Override
    public HabitacionDTO conversorDTO(Habitacion habitacion) {
        EmpleadoDTO empleadoDTO = this.habitacionEmpleados(habitacion);
        HotelDTO hotelDTO = this.habitacionHotel(habitacion);

        if(hotelDTO.getCodigoHotel() == null || empleadoDTO.getNombre() == null){ //Si están vacías los codigos mostramos los ids
            return new HabitacionDTO(habitacion.getId_habitacion(), hotelDTO, empleadoDTO,
                    habitacion.getFecha_inicio(),
                    habitacion.getFecha_fin(),
                    habitacion.getTipoHabit(),
                    habitacion.getPrecioHabit(),
                    habitacion.getHotel().getId_hotel().toString(),
                    habitacion.getEmpleado().getId_empleado().toString());
        }else{ //Los encuentra
            return new HabitacionDTO(habitacion.getId_habitacion(), hotelDTO, empleadoDTO,
                    habitacion.getFecha_inicio(),
                    habitacion.getFecha_fin(),
                    habitacion.getTipoHabit(), habitacion.getPrecioHabit(),
                    habitacion.getHotel().getCod_hotel(),
                    habitacion.getEmpleado().getNombre() + " "+habitacion.getEmpleado().getApellido());
        }
    }

    @Override
    public Habitacion conversorEntidad(HabitacionDTO habitacionDTO) {
        Empleado empleado = new Empleado(habitacionDTO.getEmpleado().getIdentificadorEmpleado(), null, null, null, null);
        Hotel hotel = new Hotel(habitacionDTO.getHotel().getIdHotel(), null,  null, null, null);

        return new Habitacion(habitacionDTO.getId(), habitacionDTO.getFechaInicio(),
              habitacionDTO.getFechaFin(), habitacionDTO.getTipo(),
              habitacionDTO.getPrecio(), empleado, hotel);
    }
}
