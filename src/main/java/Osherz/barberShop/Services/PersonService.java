package Osherz.barberShop.Services;

import Osherz.barberShop.Models.Person;
import Osherz.barberShop.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {

  @Autowired PersonRepository personRepository;

  @Autowired TokenService tokenService;

  private final int POINTS_FOR_APPOINTMENT = 1;

  public List<Person> getAll() {
    return this.personRepository.findAll();
  }

  public Person getPersonByToken(String personToken) {
    int personId = this.tokenService.parseToken(personToken);
    return this.personRepository.findById(personId).get();
  }

  public String createTokenFromId(int personId) {
    return this.tokenService.createToken(personId);
  }
  ;

  public String register(Person newPerson) {
    newPerson.setJoinDate(new Date());
    int id = this.personRepository.save(newPerson).getId();
    return this.createTokenFromId(id);
  }

  public ResponseEntity<Person> login(String email, String phoneNum) {
    Person personFound = this.personRepository.findByEmailAndPhoneNum(email, phoneNum);
    if (personFound == null) {
      return new ResponseEntity<Person>(personFound, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Person>(personFound, HttpStatus.OK);
  }

  public void addPointsForAppointment(Person person) {
    person.setPoints(person.getPoints() + POINTS_FOR_APPOINTMENT);
    this.personRepository.save(person);
  }

  public void removePointsForAppointment(Person person) {
    if (person.getPoints() >= POINTS_FOR_APPOINTMENT) {
      person.setPoints(person.getPoints() - POINTS_FOR_APPOINTMENT);
      this.personRepository.save(person);
    }
  }
}
