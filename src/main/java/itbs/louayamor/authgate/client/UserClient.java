package itbs.louayamor.authgate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import itbs.louayamor.authgate.dto.AuthRequest;
import itbs.louayamor.authgate.dto.UserDTO;

@FeignClient(name = "user-service", url = "${USER_SERVICE_URL:http://localhost:8081}")
public interface UserClient {
    @PostMapping("/api/users/verify")
    UserDTO verifyUser(@RequestBody AuthRequest request);
}