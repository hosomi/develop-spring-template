package jp.template.ws.user;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UserClient extends WebServiceGatewaySupport {

	public GetUserResponse getUserById(String loginUserId) {
		GetUserRequest request = new GetUserRequest();
		request.setLoginUserId(loginUserId);
		GetUserResponse response = (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ws/user/getUserRequest"));
		return response;
	}
}
