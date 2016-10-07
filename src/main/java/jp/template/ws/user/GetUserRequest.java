package jp.template.ws.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://github.com/hosomi/develop-spring-template")
public class GetUserRequest {

	private String loginUserId;
	
	private String screenname;
	
	/**
	 * @return the loginUserId
	 */
	public String getLoginUserId() {
		return loginUserId;
	}

	/**
	 * @param loginUserId the loginUserId to set
	 */
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	/**
	 * @return the screenname
	 */
	public String getScreenname() {
		return screenname;
	}

	/**
	 * @param screenname the screenname to set
	 */
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}
}
