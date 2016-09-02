package jp.template.domain;

/**
 * Goods エンティティ。
 * 
 * @author hosomi.
 */
public class Goods {

	/** id。 */
	private int id;

	/** 商品コード。 */
	private String code;

	/** 商品名。 */
	private String name;

	/** 商品名・カナ。 */
	private String kana;

	/** 備考。 */
	private String note;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the kana
	 */
	public String getKana() {
		return kana;
	}

	/**
	 * @param kana the kana to set
	 */
	public void setKana(String kana) {
		this.kana = kana;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
