package jp.template.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 部門エンティティ。
 * 
 * @author hosomi.
 */
public class Dpt {

	/** 部門コード*/
	private String cddpt;
	
	/** 上位部門コード*/
	private String cdupperdpt;
	
	/** 部門名*/
	private String nmdpt;
	
	/** 部門名略称*/
	private String nmshortdpt;
	
	/** 表示順序*/
	private BigDecimal showodr;
	
	/** 適用開始日*/
	private String dtavlst;
	
	/** 適用終了日*/
	private String dtavled;
	
	/** 更新/登録日付*/
	private Timestamp dtupdate;

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
	 * @return the cdupperdpt
	 */
	public String getCdupperdpt() {
		return cdupperdpt;
	}

	/**
	 * @param cdupperdpt the cdupperdpt to set
	 */
	public void setCdupperdpt(String cdupperdpt) {
		this.cdupperdpt = cdupperdpt;
	}

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
	 * @return the nmshortdpt
	 */
	public String getNmshortdpt() {
		return nmshortdpt;
	}

	/**
	 * @param nmshortdpt the nmshortdpt to set
	 */
	public void setNmshortdpt(String nmshortdpt) {
		this.nmshortdpt = nmshortdpt;
	}

	/**
	 * @return the showodr
	 */
	public BigDecimal getShowodr() {
		return showodr;
	}

	/**
	 * @param showodr the showodr to set
	 */
	public void setShowodr(BigDecimal showodr) {
		this.showodr = showodr;
	}

	/**
	 * @return the dtavlst
	 */
	public String getDtavlst() {
		return dtavlst;
	}

	/**
	 * @param dtavlst the dtavlst to set
	 */
	public void setDtavlst(String dtavlst) {
		this.dtavlst = dtavlst;
	}

	/**
	 * @return the dtavled
	 */
	public String getDtavled() {
		return dtavled;
	}

	/**
	 * @param dtavled the dtavled to set
	 */
	public void setDtavled(String dtavled) {
		this.dtavled = dtavled;
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
