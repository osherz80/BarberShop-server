package Osherz.barberShop.Controllers;

import Osherz.barberShop.Services.AuthyService;
import Osherz.barberShop.Services.SmsService;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/sms")
public class SmsController {

  @Autowired
  AuthyService authyService;

  @Autowired
  SmsService smsService;

  @PostMapping("/send")
  public ResponseEntity<String> sendSMS(@RequestBody String phoneNum) {
    return this.smsService.sendSMS(phoneNum);
  }

  @PostMapping("/code")
  public Verification auth(@RequestBody HashMap<String,String> body) {
    System.out.println("phone num " + body.get("phoneNum"));
    return this.authyService.smsVerificationToken(body.get("phoneNum"));
  }

  @PostMapping("/auth")
  public VerificationCheck verify(@RequestBody HashMap<String,String> body) {
    return this.authyService.tokenVerification(body.get("phoneNum"), body.get("code"));
  }
}
