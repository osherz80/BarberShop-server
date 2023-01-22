package Osherz.barberShop.Services;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.stereotype.Service;

@Service
public class AuthyService {

  private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
  private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  private static final String AUTH_SERVICE_SID = System.getenv("AUTH_SERVICE_SID");
  private static final String IL_AREA_CODE = "+972";

  public Verification smsVerificationToken(String phoneNum) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Verification verification =
        Verification.creator(AUTH_SERVICE_SID, IL_AREA_CODE + phoneNum, "sms").create();

    System.out.println(verification.getStatus());
    return verification;
  }

  public VerificationCheck tokenVerification(String phoneNum, String code) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    VerificationCheck verificationCheck =
        VerificationCheck.creator(AUTH_SERVICE_SID)
            .setTo(IL_AREA_CODE + phoneNum)
            .setCode(code)
            .create();

    return verificationCheck;
  }
}
