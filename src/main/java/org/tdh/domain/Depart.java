package org.tdh.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Puti
 * @date 2022/4/15 10:24
 */
public class Depart implements Serializable {
    private String bmdm;
    private String dwdm;
    private String bmid;
    private String bmmc;
    private String sfjy;
    private Integer pxh;

    public Depart() {

    }


    public Depart(String bmdm) {
        this.bmdm = bmdm;
    }

    public String getBmdm() {
        return bmdm;
    }

    public void setBmdm(String bmdm) {
        this.bmdm = bmdm;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public Integer getPxh() {
        return pxh;
    }

    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depart depart = (Depart) o;
        return Objects.equals(bmdm, depart.bmdm) && Objects.equals(dwdm, depart.dwdm) && Objects.equals(bmid, depart.bmid) && Objects.equals(bmmc, depart.bmmc) && Objects.equals(sfjy, depart.sfjy) && Objects.equals(pxh, depart.pxh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bmdm, dwdm, bmid, bmmc, sfjy, pxh);
    }
}
