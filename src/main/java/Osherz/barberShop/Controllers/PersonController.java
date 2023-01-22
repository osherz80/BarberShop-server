package Osherz.barberShop.Controllers;

import Osherz.barberShop.Models.Person;
import Osherz.barberShop.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired PersonService personService;

  @GetMapping("/all")
  public List<Person> getAll() {
    return this.personService.getAll();
  }

  @PostMapping("/login")
  public ResponseEntity<Person> login(@RequestBody HashMap<String,String> body) {
    return this.personService.login(body.get("email"), body.get("phoneNum"));
  }

  @PostMapping("/login-token")
  public String sendLoginToken(@RequestBody HashMap<String,Integer> body){
    return this.personService.createTokenFromId(body.get("personId"));
  }

  @PostMapping("/register")
  public String register(@RequestBody Person person) {
    System.out.println("in register " + person.getFirstName());
    return this.personService.register(person);
  }

  @GetMapping("/self")
  public Person getSelf(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    return this.personService.getPersonByToken(token);
  }
}
