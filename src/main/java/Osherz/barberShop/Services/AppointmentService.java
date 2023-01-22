package Osherz.barberShop.Services;

import Osherz.barberShop.Models.*;
import Osherz.barberShop.Repositories.AppointmentRepository;
import com.twilio.rest.microvisor.v1.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

  private final int ISRAEL_TIMEZONE_HOURS_OFFSET = 3;

  @Autowired AppointmentRepository appointmentRepository;

  @Autowired TreatmentService treatmentService;

  @Autowired PersonService personService;

  public List<Appointment> getAll() {
    System.out.println("in appointment service getAll");
    return this.appointmentRepository.findAll();
  }

  public Date createDayStartDate(Date date) {
    Instant inst = date.toInstant();
    LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
    Instant dayInst = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    return Date.from(dayInst);
  }

  public Date createDayEndingDate(Date date) {
    Instant inst = date.toInstant();
    LocalDate localDate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
    Instant endInstant =
        localDate.atStartOfDay(ZoneId.systemDefault()).plusHours(23).plusMinutes(59).toInstant();
    return Date.from(endInstant);
  }

  public List<Appointment> getAppointmentsByDate(Date date) {
    Date from = createDayStartDate(date);
    Date to = createDayEndingDate(date);
    return this.appointmentRepository.findByDateGreaterThanEqualAndDateLessThanEqual(from, to);
  }

  public List<AppointmentTimeDTO> getAppointmentsTimeDTOByDate(Date date) {
    Date from = createDayStartDate(date);
    Date to = createDayEndingDate(date);
    List<Appointment> appointments =
        this.appointmentRepository.findByDateGreaterThanEqualAndDateLessThanEqual(from, to);
    ArrayList<AppointmentTimeDTO> appointmentTimeDTOS = new ArrayList<>();
    for (Appointment appointmet : appointments) {
      Instant instant = appointmet.getAppointment_date().toInstant();
      LocalTime time = instant.atZone(ZoneOffset.UTC).toLocalTime();
      System.out.println("overall time " + time);
      // get hour
      int hour = instant.atZone(ZoneOffset.UTC).getHour();
      System.out.println("hour " + hour);
      // get minute
      int minute = instant.atZone(ZoneOffset.UTC).getMinute();
      System.out.println("minutes" + minute);
      appointmentTimeDTOS.add(new AppointmentTimeDTO(hour, minute));
    }
    return appointmentTimeDTOS;
  }

  public List<Appointment> getAppointmentsInRange(Date from, Date to) {
    from = createDayStartDate(from);
    to = createDayEndingDate(to);
    System.out.println("from " + from);
    System.out.println("to " + to);
    return this.appointmentRepository.findByDateGreaterThanEqualAndDateLessThanEqual(from, to);
  }

  public Appointment createAppointmentToSave(
      String token, RecievedAppointment recievedAppointment) {
    Person person = this.personService.getPersonByToken(token);
    this.personService.addPointsForAppointment(person);
    Treatment treatment =
        this.treatmentService.getTreatmentById(recievedAppointment.getTreatmentId());
    System.out.println(recievedAppointment.getDate());
    return new Appointment(person, treatment, recievedAppointment.getDate());
  }

  public ResponseEntity<Appointment> saveAppointment(
      String token, RecievedAppointment recievedAppointment) {
    Appointment appointment = this.createAppointmentToSave(token, recievedAppointment);
    return new ResponseEntity<Appointment>(
        this.appointmentRepository.save(appointment), HttpStatus.OK);
  }

  public List<AppointmentInfoDTO> getPersonAppointmentsByToken(String token) {
    int customerId = this.personService.getPersonByToken(token).getId();
    System.out.println("person id " + customerId);
    Date todayStart = this.createDayStartDate(new Date());
    System.out.println("todayStart " + todayStart);
    List<Appointment> appointments =
        this.appointmentRepository.findByCustomerIdAndDateGreaterThanEqualOrderByDateAsc(
            customerId, todayStart);
    List<AppointmentInfoDTO> appointmentInfoDTOS = new ArrayList<>() {};
    for (Appointment appointment : appointments) {
      appointmentInfoDTOS.add(
          new AppointmentInfoDTO(
              appointment.getAppointment_date(),
              appointment.getTreatment().getName(),
              appointment.getAppointmentId()));
    }
    return appointmentInfoDTOS;
  }

  public HttpStatus deleteAppointment(int appointmentId, String token) throws Error {
    if (token != null) {
      try {
        this.personService.removePointsForAppointment(this.personService.getPersonByToken(token));
        this.appointmentRepository.deleteById(appointmentId);
        return HttpStatus.OK;
      } catch (Error err) {
        return HttpStatus.NOT_FOUND;
      }
    }
    return HttpStatus.NOT_FOUND;
  }
}
