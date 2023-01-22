package Osherz.barberShop.Repositories;

import Osherz.barberShop.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDateGreaterThanEqualAndDateLessThanEqual(Date dayStart, Date dayEnd);

    List<Appointment> findByCustomerIdAndDateGreaterThanEqualOrderByDateAsc(int customerId,Date date);
}
