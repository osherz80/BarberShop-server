package Osherz.barberShop.Repositories;

import Osherz.barberShop.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmailAndPhoneNum(String email, String phoneNum);
}
