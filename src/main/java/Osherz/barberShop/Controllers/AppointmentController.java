package Osherz.barberShop.Controllers;

import Osherz.barberShop.Models.Appointment;
import Osherz.barberShop.Models.AppointmentInfoDTO;
import Osherz.barberShop.Models.AppointmentTimeDTO;
import Osherz.barberShop.Models.RecievedAppointment;
import Osherz.barberShop.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/all")
    public List<Appointment> getAll() {
    System.out.println("in appointment controller/all");
        return this.appointmentService.getAll();
    }

    @PostMapping("/by/date")
    public List<Appointment> getAppointmentsByDate(@RequestBody HashMap<String, Date> body) {
        return this.appointmentService.getAppointmentsByDate(body.get("date"));
    }

    @PostMapping("/in/range")
    public List<Appointment> getAppointmentsInRange(@RequestBody HashMap<String, Date> body) {
        return this.appointmentService.getAppointmentsInRange(body.get("from"),body.get("to"));
    }

    @PostMapping("/time/by/date")
    public List<AppointmentTimeDTO> getAppointmentsTimeDTOsByDate(@RequestBody HashMap<String, Date> body) {
        return this.appointmentService.getAppointmentsTimeDTOByDate(body.get("date"));
    }

    @GetMapping("/by/token")
    public List<AppointmentInfoDTO> getPersonAppointmentsByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.appointmentService.getPersonAppointmentsByToken(token);
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> add(@RequestBody RecievedAppointment body, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
    return this.appointmentService.saveAppointment(token, body);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public HttpStatus delete(@PathVariable int appointmentId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
    System.out.println("appointment id received " + appointmentId);
    return this.appointmentService.deleteAppointment(appointmentId,token);
    }
}
