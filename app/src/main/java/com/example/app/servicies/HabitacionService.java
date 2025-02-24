package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Empleado;
import com.example.app.entities.Habitacion;
import com.example.app.entities.Hotel;
import com.example.app.repositories.IHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Habitacion nuevo = this.conversorEntidad(habitacionDTO);
        Habitacion creado = repository.save(nuevo);
        return this.conversorDTO(creado);
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
            return new HotelDTO(habitacion.getHotel().getId_hotel(), null, null, null, null, null, null);
        }else return new HotelDTO(habitacion.getHotel().getId_hotel(), habitacion.getHotel().getCod_hotel(), habitacion.getHotel().getNombre(), habitacion.getHotel().getLugar(), habitacion.getHotel().getFecha_inicio(), habitacion.getHotel().getFecha_fin(), null);
    }

    @Override
    public HabitacionDTO conversorDTO(Habitacion habitacion) {
        EmpleadoDTO empleadoDTO = this.habitacionEmpleados(habitacion);
        HotelDTO hotelDTO = this.habitacionHotel(habitacion);

        return new HabitacionDTO(habitacion.getId_habitacion(), hotelDTO, empleadoDTO,
                habitacion.getFecha_inicio(), habitacion.getFecha_fin(),
                habitacion.getTipoHabit(), habitacion.getPrecioHabit(), 0,
                habitacion.getHotel().getId_hotel().toString(),
                habitacion.getEmpleado().getId_empleado().toString());
    }
    @Override
    public Habitacion conversorEntidad(HabitacionDTO habitacionDTO) {
        Empleado empleado = new Empleado(habitacionDTO.getEmpleado().getIdentificadorEmpleado(), null, null, null, null);
        Hotel hotel = new Hotel(habitacionDTO.getHotel().getIdHotel(), null, null, null, null, null, null);

      return new Habitacion(habitacionDTO.getId(), habitacionDTO.getFechaInicio(),
              habitacionDTO.getFechaFin(), habitacionDTO.getTipo(),
              habitacionDTO.getPrecio(), empleado, hotel);
    }
}
