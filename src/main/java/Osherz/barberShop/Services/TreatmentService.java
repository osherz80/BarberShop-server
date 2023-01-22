package Osherz.barberShop.Services;

import Osherz.barberShop.Models.Treatment;
import Osherz.barberShop.Repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    TreatmentRepository treatmentRepository;

    public List<Treatment> getAll() {
        return this.treatmentRepository.findAll();
    }

    public Treatment getTreatmentById(int treatmentId){
        return this.treatmentRepository.findById(treatmentId).get();
    }
}
