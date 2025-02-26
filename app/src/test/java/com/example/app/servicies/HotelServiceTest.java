package com.example.app.servicies;

import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Empleado;
import com.example.app.entities.Habitacion;
import com.example.app.entities.Hotel;
import com.example.app.repositories.IHotelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    //Simulamos el comportamiento del servicio
    @InjectMocks
    private HotelService service;

    //Inyectamos mocks en repositorio
    @Mock
    private IHotelRepository repository;

    @Test
    @DisplayName("Listado de Hoteles")
    void listarHoteles(){

        //Creamos una lista
        List<Hotel> hoteles = List.of(
                new Hotel( 1L, "AR-002", "Artlon", "Venecia",
                        List.of(
                                new Habitacion(1L, LocalDate.now(),
                                        LocalDate.of(2025, 5, 2),
                                        Habitacion.TipoHabitacion.DOBLE, 1852.0,
                                        new Empleado(1L, "Lucia", "Blanco", null, null),
                                        new Hotel())
                        )),
                new Hotel(2L, "PO-152", "Polton", "Marruecos", List.of())
        );

        //llamar al repositorio
        when(repository.findAll()).thenReturn(hoteles);

        //Aplicar al servicio
        List<HotelDTO> hotelesDTO = service.mostrarHoteles();

        //Verificaciones si el hotel del ID 1 tiene los mismos parámetros
        assertThat(hotelesDTO.get(0).getIdHotel()).isEqualTo(1L);
        assertThat(hotelesDTO.get(0).getNombreHotel()).isEqualTo("Artlon");
        assertThat(hotelesDTO.get(0).getCodigoHotel()).isEqualTo("AR-002");

        //Verificar si ese hotel tiene habitaciones
        assertThat(hotelesDTO.get(0).getHabitaciones()).isNotNull();

        //Comprobacion del tamaño de la lista
        assertThat(hotelesDTO.size()).isEqualTo(2);

    }
}
