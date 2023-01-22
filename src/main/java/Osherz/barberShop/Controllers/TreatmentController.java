package Osherz.barberShop.Controllers;

import Osherz.barberShop.Models.Treatment;
import Osherz.barberShop.Services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    TreatmentService treatmentService;

    @GetMapping("/all")
    public List<Treatment> getAll() {
        return this.treatmentService.getAll();
    }
}
