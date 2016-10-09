package jp.template.ws.dpt;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://github.com/hosomi/develop-spring-template")
public class GetDptRequest {

	/** 部門コード*/
	private String cddpt;
	
	/**
	 * @return the cddpt
	 */
	public String getCddpt() {
		return cddpt;
	}

	/**
	 * @param cddpt the cddpt to set
	 */
	public void setCddpt(String cddpt) {
		this.cddpt = cddpt;
	}
}
