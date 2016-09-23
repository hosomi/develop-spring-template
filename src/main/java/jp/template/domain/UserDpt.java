package jp.template.domain;

import java.sql.Timestamp;

/**
 * UserDpt エンティティ.
 * 
 * @author hosomi.
 */
public class UserDpt {

	/** ログインユーザID*/
	private String loginUserId;
	
	/** 部門コード*/
	private String cddpt;
	
	/** 主務(true)/兼務(false)*/
	private boolean  main;
	
	/** 更新/登録日付*/
	private Timestamp dtupdate;

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

	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(boolean main) {
		this.main = main;
	}

	/**
	 * @return the dtupdate
	 */
	public Timestamp getDtupdate() {
		return dtupdate;
	}

	/**
	 * @param dtupdate the dtupdate to set
	 */
	public void setDtupdate(Timestamp dtupdate) {
		this.dtupdate = dtupdate;
	}
}
