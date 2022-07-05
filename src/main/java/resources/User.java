package resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

    private String userName;
    private String loginName;
    private boolean licensed;
    private boolean online;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", licensed=" + licensed +
                ", online=" + online +
                '}';
    }
}
