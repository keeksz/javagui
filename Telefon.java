package Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefon implements Comparable<Telefon> {
	private String nev;
	private String szam;
	private String varos;
	
	public Telefon(String nev, String szam, String varos) {
		super();
		this.nev = nev;
		this.szam = szam;
		this.varos = varos;
	}
	
	public Telefon(String line) {
		String data[] = line.split(",");
		this.nev = data[0];
		this.szam = data[1];
		this.varos = data[2];
	}
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getSzam() {
		return szam;
	}

	public void setSzam(String szam) {
		this.szam = szam;
	}

	public String getVaros() {
		return varos;
	}

	public void setVaros(String varos) {
		this.varos = varos;
	}

	@Override
	public String toString() {
		return "Telefon [nev=" + nev + ", szam=" + szam + ", varos=" + varos + "]";
	}
	
	public String format() {
		return this.szam.substring(0, 2)
				+ "-" + this.szam.substring(2, 4)
				+ "-" + this.szam.substring(4, 6)
				+ "-" + this.szam.substring(6, 8)
				+ "-" + this.szam.substring(8, 11)
				;
	}
	
	public boolean isValid() {
		Pattern p = Pattern.compile("06[237]0[0-9]{7}$");
		Matcher m = p.matcher(this.szam);
		return m.matches();
	}

	@Override
	public int compareTo(Telefon o) {		
		int i = this.nev.compareTo(o.nev);
		if (i != 0) {
			return i;
		}
		return this.varos.compareTo(o.varos);
	}	
}
