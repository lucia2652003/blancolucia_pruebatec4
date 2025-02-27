package com.example.app.servicies;

import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
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
                .map(this::conversorDTO)
                .toList();
    }

    @Override
    public ResponseEntity verListadoRE(List<HotelDTO> listado) {
        //Si no presentas vuelos disponibles manda un mensaje diciendo que no está de lo contrario los muestra
        if(listado.isEmpty()) return ResponseEntity.status(200).body("No hay hoteles registrados: "+listado.size());
        else return ResponseEntity.status(200).body(listado);
    }

    @Override
    public HotelDTO agregarHotel(HotelDTO hotelDTO) {
        //Si existe el hotel con ese código se envia un constructor vació y no se almacena DB
        Optional<Hotel> existe = repository.findAll().stream()
                .filter(hotel1 -> hotel1.getCod_hotel().equals(hotelDTO.getCodigoHotel()))
                .findFirst();

        if(existe.isPresent()) return new HotelDTO(); //Existe en BD
        else {
            Hotel hotel = this.conversorEntidad(hotelDTO);
            Hotel guardado = repository.save(hotel);
            return this.conversorDTO(guardado);
        }
    }

    @Override
    public HotelDTO buscarHotel(Long id) {
        Optional<Hotel> encontrado = repository.findById(id);
        if(encontrado.isPresent()) return this.conversorDTO(encontrado.get()); //Muestra todos los datos DTO
        else return new HotelDTO(); //No existe
    }

    @Override
    public HotelDTO modificarHotel(Long id, HotelDTO entidad) {
        Optional<Hotel> existe = repository.findById(id);

        if(existe.isPresent()){//Encuentra actualizamos todos los datos y luego lo actualizamos en BD
            Hotel encontrado = existe.get();

            encontrado.setNombre(entidad.getNombreHotel());
            encontrado.setLugar(entidad.getLugar());

            Hotel actualizado = repository.save(encontrado);
            return  this.conversorDTO(actualizado);
        }
        else return new HotelDTO();//No existe
    }

    @Override
    public List<HotelDTO> eliminarHotel(Long id) {
       Optional<Hotel> existe = repository.findById(id);

        // Existe el hotel y no presenta habitaciones lo elimina
        if(existe.isPresent() && existe.get().getHabitaciones().isEmpty()){
            repository.deleteById(id);
            return this.mostrarHoteles();
        }else {
            //No se realizó la eliminación porque no existe el vuelo o tiene reservas
            return this.mostrarHoteles();
        }

    }

    @Override
    public HotelDTO conversorDTO(Hotel hotel) {
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        if(hotel.getHabitaciones() == null){
            habitacionDTO = null;
            return null;//Mostrara que no presenta habitaciones
        }else{
            //Muestra las habitaciones de ese hotel específico
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
