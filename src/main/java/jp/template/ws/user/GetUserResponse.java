package jp.template.ws.user;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import jp.template.domain.User;

@XmlRootElement(namespace = "http://github.com/hosomi/develop-spring-template")
public class GetUserResponse {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
