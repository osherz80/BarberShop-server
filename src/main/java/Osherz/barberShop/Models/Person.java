package Osherz.barberShop.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column(name = "person_id")
    private int id;

    @JsonProperty
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty
    @Column(name = "phone_num")
    private String phoneNum;

    @JsonProperty
    @Column(name = "email")
    private String email;

    @JsonProperty
    @Column(name = "points")
    private int points;

    @JsonProperty
    @Column(name = "join_date")
    private Date joinDate;

    public Person() {}

    public Person(int id, String firstName, String lastName, String phoneNum, String email, int points, Date joinDate) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNum(phoneNum);
        this.setEmail(email);
        this.setPoints(points);
        this.setJoinDate(joinDate);
    }

    public Person(Person person) {
        this.setId(person.getId());
        this.setFirstName(person.getFirstName());
        this.setLastName(person.getLastName());
        this.setPhoneNum(person.getPhoneNum());
        this.setEmail(person.getEmail());
        this.setPoints(person.getPoints());
        this.setJoinDate(person.getJoinDate());
    }

    public int getId() {
        return id;
    }

    private void setId (int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
