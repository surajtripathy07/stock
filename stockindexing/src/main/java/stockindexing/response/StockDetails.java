package stockindexing.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDetails {
	private String t;
	private String e;
	private String l;
	private String ltt;
	private String lt;
	private String lt_dts;
	private String c;
	private String cp;
	private String el;
	private String elt;
	private String div;
	private String yld;

	private String name;
	private String marketCap;
	private String sector;
	private String industry;

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getLtt() {
		return ltt;
	}

	public void setLtt(String ltt) {
		this.ltt = ltt;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getLt_dts() {
		return lt_dts;
	}

	public void setLt_dts(String lt_dts) {
		this.lt_dts = lt_dts;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getEl() {
		return el;
	}

	public void setEl(String el) {
		this.el = el;
	}

	public String getElt() {
		return elt;
	}

	public void setElt(String elt) {
		this.elt = elt;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getYld() {
		return yld;
	}

	public void setYld(String yld) {
		this.yld = yld;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
