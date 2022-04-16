package org.tdh.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Puti
 * @date 2022/4/15 10:24
 */
public class BzdmPK implements Serializable {
    private String kind;
    private String code;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BzdmPK bzdmPK = (BzdmPK) o;
        return Objects.equals(kind, bzdmPK.kind) && Objects.equals(code, bzdmPK.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, code);
    }
}
