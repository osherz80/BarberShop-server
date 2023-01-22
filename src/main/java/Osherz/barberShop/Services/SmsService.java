package Osherz.barberShop.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

  private final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
  private final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  private final String SYSTEM_PHONE_NUM = System.getenv("TWILIO_NUMBER");
  private static final String IL_AREA_CODE = "+972";
  private final String WELCOME_MSG = "ההרשמה בוצעה בהצלחה תודה שבחרת להשתמש בשירותנו😃";

  public ResponseEntity<String> sendSMS(String phoneNum) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(
            new PhoneNumber(IL_AREA_CODE + phoneNum), // TO
            new PhoneNumber(SYSTEM_PHONE_NUM), // FROM
            WELCOME_MSG)
        .create();

    return new ResponseEntity<String>("Welcome message sent successfully", HttpStatus.OK);
  }
}
