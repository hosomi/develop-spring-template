package jp.template.ws.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import jp.template.domain.User;
import jp.template.mapper.UserMapper;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://github.com/hosomi/develop-spring-template";
    
    @Autowired
    private UserMapper userMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUsers(@RequestPayload GetUserRequest userRequest) {
        final GetUserResponse getUserResponse = new GetUserResponse();
        final String loginUserId = userRequest.getLoginUserId();
        
        User u = userMapper.select(loginUserId);
        
        final List<User> list = new ArrayList<User>();
        list.add(u);
        getUserResponse.setUsers(list);
        return getUserResponse;
    }
}

