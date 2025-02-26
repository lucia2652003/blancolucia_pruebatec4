package com.example.app.servicies;

import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Empleado;
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

    /*Inyección de dependencias*/
    @Autowired
    private IHotelRepository repository;

    @Override
    public List<HotelDTO> mostrarHoteles() {
        List<Hotel> todosHoteles = repository.findAll();
        return todosHoteles.stream()
                .map(this::conversotDTO)
                .toList();
    }

    @Override
    public ResponseEntity verListadoRE(List<HotelDTO> listado) {
        //Si no presentas vuelos disponibles manda un mensaje diciendo que no esta de lo contrario los muestra
        if(listado.isEmpty()) return ResponseEntity.status(200).body("No hay hoteles registrados: "+listado.size());
        else return ResponseEntity.status(200).body(listado);
    }

    @Override
    public HotelDTO agregarHotel(HotelDTO hotelDTO) {
        //Si existe el hotel con ese codigo de hotel se envia un constructor vació y no se almacena DB

        Optional<Hotel> existe = repository.findAll().stream()
                .filter(hotel1 -> hotel1.getCod_hotel().equals(hotelDTO.getCodigoHotel()))
                .findFirst();

        if(existe.isPresent()) return new HotelDTO(); //Ya existe en BD
        else {
            Hotel hotel = this.conversorEntidad(hotelDTO);
            Hotel guardado = repository.save(hotel);
            return this.conversotDTO(guardado);
        }
    }

    @Override
    public HotelDTO buscarHotel(Long id) {
        Optional<Hotel> encontrado = repository.findById(id);

        if(encontrado.isPresent()){
            return this.conversotDTO(encontrado.get());
        }else return new HotelDTO(); //No existe
    }

    @Override
    public HotelDTO modificarHotel(Long id, HotelDTO entidad) {
        Optional<Hotel> existe = repository.findById(id);

        if(existe.isPresent()){//Encuentra actualizamos todos los datos y luego lo actualizamos en BD
            Hotel encontrado = existe.get();

            encontrado.setNombre(entidad.getNombreHotel());
            encontrado.setLugar(entidad.getLugar());

            Hotel actualizado = repository.save(encontrado);
            return  this.conversotDTO(actualizado);
        }
        else return new HotelDTO();//No existe
    }

    @Override
    public List<HotelDTO> eliminarHotel(Long id) {
        HotelDTO existe = this.buscarHotel(id);

        // Si me recibo un constructor vacio
        // Existe el hotel y tiene habitaciones me manda el listado
        if(existe == null || !existe.getHabitaciones().isEmpty()){
            return this.mostrarHoteles();
        }else {
            repository.deleteById(id);
            return this.mostrarHoteles();
        }

    }

    @Override
    public HotelDTO conversotDTO(Hotel hotel) {
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        if(hotel.getHabitaciones() == null){
            habitacionDTO = null;
            return null;//Nos muestra [] en las habitaciones
        }else{
            List<HabitacionDTO> habitaciones = hotel.getHabitaciones().stream().map(habitacion -> new HabitacionDTO(
                    habitacion.getId_habitacion(),
                    null,null,
                    habitacion.getFecha_inicio(),
                    habitacion.getFecha_fin(),
                    habitacion.getTipoHabit(),
                    habitacion.getPrecioHabit(),
                    habitacion.getHotel().getCod_hotel(),
                    habitacion.getEmpleado().getNombre() + " "+habitacion.getEmpleado().getApellido()
            )).toList();

            return new HotelDTO(hotel.getId_hotel(), hotel.getCod_hotel(), hotel.getNombre(), hotel.getLugar(), habitaciones);
        }
    }

    @Override
    public Hotel conversorEntidad(HotelDTO hotelDTO) {
        return new Hotel(hotelDTO.getIdHotel(),
                hotelDTO.getCodigoHotel(),
                hotelDTO.getNombreHotel(),
                hotelDTO.getLugar(),
                new ArrayList<>()
        );
    }
}
