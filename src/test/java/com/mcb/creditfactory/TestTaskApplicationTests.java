package com.mcb.creditfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Raiting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestTaskApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
        assertNotNull(objectMapper);
    }

    @Test
    public void saveGetInfoCar() throws Exception {
        List<Raiting> raitingList = new ArrayList<>();
        raitingList.add(new Raiting(null, new BigDecimal(10000011), LocalDate.now()));
        raitingList.add(new Raiting(null, new BigDecimal(10000012), LocalDate.now()));
        raitingList.add(new Raiting(null, new BigDecimal(10000013), LocalDate.now()));
        CarDto car = new CarDto(null, "bmw", "x5", 250.0, (short) 2005, raitingList);

        MockHttpServletResponse saveResponse = mockMvc.perform(post("/collateral/save")
                .content(objectMapper.writerFor(CarDto.class).writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        Long saveResponseId = objectMapper.readValue(saveResponse.getContentAsByteArray(), Long.class);
        CarDto carInfo = new CarDto();
        carInfo.setId(saveResponseId);

        MockHttpServletResponse infoResponse = mockMvc.perform(post("/collateral/info")
                .content(objectMapper.writerFor(CarDto.class).writeValueAsString(carInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        CarDto infoCar = objectMapper.readValue(infoResponse.getContentAsByteArray(), CarDto.class);
        assertCarDto(car, infoCar);
    }

    private void assertCarDto(CarDto requestCar, CarDto responseCar) {
        assertEquals(requestCar.getBrand(), responseCar.getBrand());
        assertEquals(requestCar.getModel(), responseCar.getModel());
        assertEquals(requestCar.getPower(), responseCar.getPower());
        assertEquals(requestCar.getYear(), responseCar.getYear());

        List<Raiting> requestRaitings = requestCar.getRaitingList();
        List<Raiting> responseRaitings = responseCar.getRaitingList();
        assertEquals(requestRaitings.size(), responseRaitings.size());
        for (int i = 0; i < requestRaitings.size(); i++) {
            assertEquals(requestRaitings.get(i).getValue(), responseRaitings.get(i).getValue());
            assertEquals(requestRaitings.get(i).getDate(), responseRaitings.get(i).getDate());
        }
    }

    @Test
    public void saveGetInfoAirplane() throws Exception {
        List<Raiting> raitingList = new ArrayList<>();
        raitingList.add(new Raiting(null, new BigDecimal(230000001), LocalDate.now()));
        raitingList.add(new Raiting(null, new BigDecimal(230000002), LocalDate.now()));
        raitingList.add(new Raiting(null, new BigDecimal(230000003), LocalDate.now()));
        AirPlaneDto airplane = new AirPlaneDto(null, "S7", "7000", "China", (short) 2005, 10000, 700, "jet", raitingList);


        MockHttpServletResponse saveResponse = mockMvc.perform(post("/collateral/save")
                .content(objectMapper.writerFor(AirPlaneDto.class).writeValueAsString(airplane))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        Long saveResponseId = objectMapper.readValue(saveResponse.getContentAsByteArray(), Long.class);
        AirPlaneDto airplaneInfo = new AirPlaneDto();
        airplaneInfo.setId(saveResponseId);

        MockHttpServletResponse infoResponse = mockMvc.perform(post("/collateral/info")
                .content(objectMapper.writerFor(AirPlaneDto.class).writeValueAsString(airplaneInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        AirPlaneDto infoAirplane = objectMapper.readValue(infoResponse.getContentAsByteArray(), AirPlaneDto.class);
        assertAirplaneDto(airplane, infoAirplane);
    }

    private void assertAirplaneDto(AirPlaneDto requestCar, AirPlaneDto responseCar) {
        assertEquals(requestCar.getBrand(), responseCar.getBrand());
        assertEquals(requestCar.getModel(), responseCar.getModel());
        assertEquals(requestCar.getManufacturer(), responseCar.getManufacturer());
        assertEquals(requestCar.getYear(), responseCar.getYear());
        assertEquals(requestCar.getSize(), responseCar.getSize());
        assertEquals(requestCar.getPassengers(), responseCar.getPassengers());
        assertEquals(requestCar.getType(), responseCar.getType());

        List<Raiting> requestRaitings = requestCar.getRaitingList();
        List<Raiting> responseRaitings = responseCar.getRaitingList();
        assertEquals(requestRaitings.size(), responseRaitings.size());
        for (int i = 0; i < requestRaitings.size(); i++) {
            assertEquals(requestRaitings.get(i).getValue(), responseRaitings.get(i).getValue());
            assertEquals(requestRaitings.get(i).getDate(), responseRaitings.get(i).getDate());
        }
    }
}