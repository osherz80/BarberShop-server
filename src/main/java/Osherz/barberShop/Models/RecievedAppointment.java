package Osherz.barberShop.Models;

import java.util.Date;

public class RecievedAppointment {

    private int treatmentId;
    private Date date;

    RecievedAppointment(){}

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
