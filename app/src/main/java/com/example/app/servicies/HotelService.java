package com.example.app.servicies;

import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Hotel;
import com.example.app.repositories.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService{

    @Autowired
    IHotelRepository repository;

    @Override
    public ResponseEntity mostrarHoteles() {
        List<Hotel> todosHoteles = repository.findAll();

        if(todosHoteles.isEmpty()) return ResponseEntity.status(200).body("No hay hoteles registrados: "+todosHoteles.size());
        else return ResponseEntity
                .status(200)
                .body(todosHoteles.stream()
                        .map(this::conversotDTO)
                        .toList());
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
            encontrado.setTipoHabit(entidad.getHabitacion());
            encontrado.setPrecioHabit(entidad.getPrecioHabit());
            encontrado.setFecha_inicio(entidad.getFechaInicio());
            encontrado.setFecha_fin(entidad.getFechaFin());
            encontrado.setReservado(entidad.getReservado().equals("SI"));

            Hotel actualizado = repository.save(encontrado);
            return  this.conversotDTO(actualizado);
        }
        else return new HotelDTO();
    }

    @Override
    public ResponseEntity eliminarHotel(Long id) {
        repository.deleteById(id);
        return this.mostrarHoteles();
    }

    @Override
    public HotelDTO conversotDTO(Hotel hotel) {
        /*
         *  true-1 si hay reserva
         *  false-0 no hay reserva
         */

        HotelDTO nuevo = new HotelDTO();

        nuevo.setCodigoHotel(hotel.getCod_hotel());
        nuevo.setNombreHotel(hotel.getNombre());
        nuevo.setLugar(hotel.getLugar());
        nuevo.setHabitacion(hotel.getTipoHabit());
        nuevo.setPrecioHabit(hotel.getPrecioHabit());
        nuevo.setFechaInicio(hotel.getFecha_inicio());
        nuevo.setFechaFin(hotel.getFecha_fin());
        if(hotel.getReservado()) nuevo.setReservado("SI");
        else nuevo.setReservado("NO");

        return nuevo;
    }

    @Override
    public Hotel conversorEntidad(HotelDTO hotelDTO) {

        return new Hotel(hotelDTO.getIdentifiHotel(),
                hotelDTO.getCodigoHotel(),
                hotelDTO.getNombreHotel(),
                hotelDTO.getLugar(),
                hotelDTO.getHabitacion(),
                hotelDTO.getPrecioHabit(),
                hotelDTO.getFechaInicio(),
                hotelDTO.getFechaFin(),
                hotelDTO.getReservado().equals("SI")
        );
    }
}
