package Osherz.barberShop.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentInfoDTO {

    private int id;
    private String day;
    private String time;
    private String name;

    public AppointmentInfoDTO( Date date, String name, int id) {
        this.id = id;
        this.setDay(date);
        this.setTime(date);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.day = dateFormat.format(date);
    }

    public String getTime() {
        return time;
    }

    public void setTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        this.time = dateFormat.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
