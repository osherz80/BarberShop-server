package Osherz.barberShop.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JsonProperty
    @JsonIgnoreProperties
    @JoinColumn(name = "customer_id")
    private Person customer;

    @OneToOne
    @JsonProperty
    @JsonIgnoreProperties
    @JoinColumn(name = "treatment")
    private Treatment treatment;

    @JsonProperty
    @Column(name = "date")
    private Date date;

    public Appointment(){}

    public Appointment(Person customer, Treatment treatment, Date appointment_date) {
        this.customer = customer;
        this.treatment = treatment;
        this.date = appointment_date;
    }

    public int getAppointmentId() {
        return id;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Date getAppointment_date() {
        return date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.date = appointment_date;
    }

}
