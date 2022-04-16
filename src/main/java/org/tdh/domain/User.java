package org.tdh.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Puti
 * @date 2022/4/14 17:16
 */
public class User implements Serializable {
    private String yhid;
    private String yhxm;
    private String yhkl;
    private String yhxb;
    private String yhbm;
    private String csrq;
    private String djrq;
    private String sfjy;
    private Integer pxh;


    public User(){
    }

    public User(String yhid) {
        this.yhid = yhid;
    }

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhxb() {
        return yhxb;
    }

    public void setYhxb(String yhxb) {
        this.yhxb = yhxb;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
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
        User user = (User) o;
        return Objects.equals(yhid, user.yhid) && Objects.equals(yhxm, user.yhxm) && Objects.equals(yhkl, user.yhkl) && Objects.equals(yhxb, user.yhxb)  && Objects.equals(csrq, user.csrq) && Objects.equals(djrq, user.djrq) && Objects.equals(sfjy, user.sfjy) && Objects.equals(pxh, user.pxh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yhid, yhxm, yhkl, yhxb, csrq, djrq, sfjy, pxh);
    }

    @Override
    public String toString() {
        return "User{" +
                "yhid='" + yhid + '\'' +
                ", yhxm='" + yhxm + '\'' +
                ", yhkl='" + yhkl + '\'' +
                ", yhxb='" + yhxb + '\'' +
                ", yhbm='" + yhbm + '\'' +
                ", csrq='" + csrq + '\'' +
                ", djrq='" + djrq + '\'' +
                ", sfjy='" + sfjy + '\'' +
                ", pxh=" + pxh +
                '}';
    }
}
