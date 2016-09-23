package jp.template.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * UserDptExtension エンティティ.
 * 
 * @author hosomi.
 */
public class UserDptExtension extends UserDpt {

	/** 部門コード*/
	@NotBlank
	private String cddpt;

	/** 部門名*/
	private String nmdpt;

	/** 更新/登録日付（表示用）*/
	private String dtupdateDisplay;
	
	/**
	 * @return the nmdpt
	 */
	public String getNmdpt() {
		return nmdpt;
	}

	/**
	 * @param nmdpt the nmdpt to set
	 */
	public void setNmdpt(String nmdpt) {
		this.nmdpt = nmdpt;
	}

	/**
	 * @return the dtupdateDisplay
	 */
	public String getDtupdateDisplay() {
		return dtupdateDisplay;
	}

	/**
	 * @param dtupdateDisplay the dtupdateDisplay to set
	 */
	public void setDtupdateDisplay(String dtupdateDisplay) {
		this.dtupdateDisplay = dtupdateDisplay;
	}

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
