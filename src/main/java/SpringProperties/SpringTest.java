package SpringProperties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringTest {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello";
    }
}
