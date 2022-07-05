package handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import resources.UserRepository;
import utils.JsonUtils;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Slf4j
@Path("/users")
@AllArgsConstructor
public class UserServlet {

    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserList() {
        log.info("users handler");
        String jsonString = JsonUtils.parseToJson(userRepository.getUserRepo(), List.class);
        log.info(jsonString);
        return jsonString;
    }



}
