package com.example.app.servicies;

import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Habitacion;
import com.example.app.entities.Hotel;
import com.example.app.repositories.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService{

    @Autowired
    IHotelRepository repository;

    @Override
    public List<HotelDTO> mostrarHoteles() {
        List<Hotel> todosHoteles = repository.findAll();
        return todosHoteles.stream()
                .map(this::conversotDTO)
                .toList();
    }

    @Override
    public ResponseEntity verListadoRE(List<HotelDTO> listado) {
        if(listado.isEmpty()) return ResponseEntity.status(200).body("No hay hoteles registrados: "+listado.size());
        else return ResponseEntity
                .status(200)
                .body(listado);
    }

    @Override
    public HotelDTO agregarHotel(HotelDTO hotelDTO) {
        //Si existe el hotel con ese codigo de hotel se envia un constructor vació y no se almacena DB

        Optional<Hotel> existe = repository.findAll().stream()
                .filter(hotel1 -> hotel1.getCod_hotel().equals(hotelDTO.getCodigoHotel()))
                .findFirst();

        if(existe.isPresent()) return new HotelDTO();
        else {
            Hotel hotel = this.conversorEntidad(hotelDTO);
            Hotel guardado = repository.save(hotel);
            return this.conversotDTO(guardado);
        }
    }

    @Override
    public HotelDTO buscarHotel(Long id) {
        Optional<Hotel> encontrado = repository.findById(id);

        if(encontrado.isPresent()) return this.conversotDTO(encontrado.get());
        else return new HotelDTO();
    }

    @Override
    public HotelDTO modificarHotel(Long id, HotelDTO entidad) {
        Optional<Hotel> existe = repository.findById(id);

        if(existe.isPresent()){
            Hotel encontrado = existe.get();

            encontrado.setNombre(entidad.getNombreHotel());
            encontrado.setLugar(entidad.getLugar());
            encontrado.setFecha_inicio(entidad.getFechaInicio());
            encontrado.setFecha_fin(entidad.getFechaFin());

            Hotel actualizado = repository.save(encontrado);
            return  this.conversotDTO(actualizado);
        }
        else return new HotelDTO();
    }

    @Override
    public List<HotelDTO> eliminarHotel(Long id) {
        repository.deleteById(id);
        return this.mostrarHoteles();
    }

    @Override
    public HotelDTO conversotDTO(Hotel hotel) {
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        if(hotel.getHabitaciones() == null){
            habitacionDTO = null;
            return null;
        }else{
            List<HabitacionDTO> habitaciones = hotel.getHabitaciones().stream().map(habitacion -> new HabitacionDTO(
                    habitacion.getId_habitacion(),
                    null,null,
                    habitacion.getFecha_inicio(),
                    habitacion.getFecha_fin(),
                    habitacion.getTipoHabit(),
                    habitacion.getPrecioHabit(),
                    0,
                    habitacion.getHotel().getCod_hotel(),
                    habitacion.getEmpleado().getNombre() + " "+habitacion.getEmpleado().getApellido()
            )).toList();

            return new HotelDTO(hotel.getId_hotel(), hotel.getCod_hotel(), hotel.getNombre(), hotel.getLugar(), hotel.getFecha_inicio(), hotel.getFecha_fin(), habitaciones);
        }

    }

    @Override
    public Hotel conversorEntidad(HotelDTO hotelDTO) {
        return new Hotel(hotelDTO.getIdHotel(),
                hotelDTO.getCodigoHotel(),
                hotelDTO.getNombreHotel(),
                hotelDTO.getLugar(),
                hotelDTO.getFechaInicio(),
                hotelDTO.getFechaFin(),
                new ArrayList<>()
        );
    }
}
