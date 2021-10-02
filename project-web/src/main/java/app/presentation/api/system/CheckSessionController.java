package app.presentation.api.system;

import com.google.common.flogger.FluentLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system/check-session")
public class CheckSessionController {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @PostMapping
    //ToDo APIDocs
    public ResponseEntity<Object> check(){
        logger.atInfo().log("Check Session Status");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Object> response = new ResponseEntity<>(null, headers, HttpStatus.OK);
        return response;
    }
}
