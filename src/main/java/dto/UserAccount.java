package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

@Data
@NoArgsConstructor
public class UserAccount {
    private String login;
    private String password;
    private String repository;

    public static UserAccount getUserAccount() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File("gitHubUser/gitHubUser.yaml"), dto.UserAccount.class);
    }
}



