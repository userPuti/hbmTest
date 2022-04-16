package org.tdh.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Puti
 * @date 2022/4/15 10:24
 */
public class Bzdm  implements Serializable {
    private BzdmPK id;
    private String kind;
    private String bt;
    private String code;
    private String mc;
    private String sfjy;
    private Integer pxh;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
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

    public BzdmPK getId() {
        return id;
    }

    public void setId(BzdmPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bzdm bzdm = (Bzdm) o;
        return Objects.equals(kind, bzdm.kind) && Objects.equals(bt, bzdm.bt) && Objects.equals(code, bzdm.code) && Objects.equals(mc, bzdm.mc) && Objects.equals(sfjy, bzdm.sfjy) && Objects.equals(pxh, bzdm.pxh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, bt, code, mc, sfjy, pxh);
    }

    @Override
    public String toString() {
        return "Bzdm{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", bt='" + bt + '\'' +
                ", code='" + code + '\'' +
                ", mc='" + mc + '\'' +
                ", sfjy='" + sfjy + '\'' +
                ", pxh=" + pxh +
                '}';
    }
}
