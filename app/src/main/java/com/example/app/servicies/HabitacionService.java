package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
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
        if(this.existeHabitacion(habitacionDTO).isPresent()){
            return new HabitacionDTO();//Ya existe BD
        }else{//Crea la reserva del hotel
            Habitacion nuevo = this.conversorEntidad(habitacionDTO);
            Habitacion creado = repository.save(nuevo);
            return this.conversorDTO(creado);
        }
    }

    @Override
    public Optional<HabitacionDTO> existeHabitacion(HabitacionDTO habitacionDTO) {
        return this.mostrarHabitaciones().stream()
                .filter(habitacionDTO1 ->
                        habitacionDTO1.getHotel().getIdHotel().equals(habitacionDTO.getHotel().getIdHotel()) &&
                                habitacionDTO1.getEmpleado().getIdentEmpleado().equals(habitacionDTO.getEmpleado().getIdentEmpleado()))
                .findFirst();
    }

    @Override
    public List<HabitacionDTO> verHabitacionesDisp(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

      /*  //Buscar las habitaciones de dichos hoteles que se encuentran en ese lugar
        List<HabitacionDTO> hotelLugar = this.mostrarHabitaciones().stream().filter(habitacionDTO ->
                        habitacionDTO.getHotel().getLugar().equalsIgnoreCase(lugar))
                .toList();*/

        //Buscarlos de ese rango de fechas de habitaciones
        // Desde Hoteles de ese destino con fechas posteriores a fecha de inicio es posterior a la fecha de quedada
        // y anterior a la fecha de salida
        return this.mostrarHabitaciones().stream()
                .filter(habitDestino-> habitDestino.getHotel().getLugar().equalsIgnoreCase(lugar))
                .filter(habitacion ->
                        (fechaDesde.isBefore(habitacion.getFechaInicio()) && fechaHasta.isAfter(habitacion.getFechaFin())))
                .collect(Collectors.toList());
    }

    /*Para evitar que en Postman nos muestre [] le mandamos un ResponseEntity
     tanto en mostrar como en eliminar muestra los disponibles*/
    @Override
    public ResponseEntity verificacionListado(List<HabitacionDTO> listado) {
        if(listado.isEmpty()) return ResponseEntity.status(200).body("No hay habitaciones disponibles: "+listado.size());
        else return ResponseEntity.ok(listado);//Nos muestra como OK
    }

    @Override
    public EmpleadoDTO habitacionEmpleados(Habitacion habitacion) {
        if(habitacion.getEmpleado().getHabitaciones() == null){//Si no lo encuentra
            return new EmpleadoDTO(habitacion.getEmpleado().getId_empleado(), null, null, null, null);
        } else {//Lo busca
            return new EmpleadoDTO(habitacion.getEmpleado().getId_empleado(), habitacion.getEmpleado().getNombre(),
                    habitacion.getEmpleado().getApellido(), null, null);
        }
    }

    @Override
    public HotelDTO habitacionHotel(Habitacion habitacion) {
        if(habitacion.getHotel().getHabitaciones() == null){//Si no lo encuentra lo crea
            return new HotelDTO(habitacion.getHotel().getId_hotel(), null, null, null, null);
        }else{//Lo busca
            return new HotelDTO(habitacion.getHotel().getId_hotel(), habitacion.getHotel().getCod_hotel(),
                    habitacion.getHotel().getNombre(), habitacion.getHotel().getLugar(), null);
        }
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
        //OPERACIONES CRUD
        Empleado empleado = new Empleado(habitacionDTO.getEmpleado().getIdentEmpleado(), null, null, null, null);
        Hotel hotel = new Hotel(habitacionDTO.getHotel().getIdHotel(), null,  null, null, null);

        return new Habitacion(habitacionDTO.getId(), habitacionDTO.getFechaInicio(),
              habitacionDTO.getFechaFin(), habitacionDTO.getTipo(),
              habitacionDTO.getPrecio(), empleado, hotel);
    }
}
