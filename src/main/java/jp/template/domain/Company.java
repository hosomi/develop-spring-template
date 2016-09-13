package jp.template.domain;

/**
 * 会社。
 * 
 * @author hosomi.
 */
public class Company {

	/** id。 */
	private int id;

	/** 会社名。 */
	private String name;

	/** 会社名・カナ。 */
	private String kana;

	/** 郵便番号。 */
	private String postal;

	/** 住所。 */
	private String address;

	/** 電話番号。 */
	private String tel;

	/** FAX。 */
	private String fax;

	/** 有効 */
	private boolean enable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
