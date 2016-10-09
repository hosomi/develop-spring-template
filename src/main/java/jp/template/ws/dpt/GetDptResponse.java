package jp.template.ws.dpt;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import jp.template.domain.Dpt;

@XmlRootElement(namespace = "http://github.com/hosomi/develop-spring-template")
public class GetDptResponse {

	/** Dpt 一覧。*/
	private List<Dpt> list;

	/**
	 * @return the list
	 */
	public List<Dpt> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Dpt> list) {
		this.list = list;
	}
}
