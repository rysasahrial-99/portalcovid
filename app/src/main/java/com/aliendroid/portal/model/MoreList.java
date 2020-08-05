package com.aliendroid.portal.model;

/**
 * Created by EKENE on 7/23/2017.
 */

public class MoreList {

    public int login;
    public String avatar_url;
    public String html_url;
    public String psl_url;

    public int getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getPsl_url() {
        return psl_url;
    }

    public String getHtml_url() {
        return html_url;
    }



    public MoreList(int id, String jdl, String img, String ps) {
        this.login = id;
        this.html_url = jdl;
        this.avatar_url = img;
        this.psl_url = ps;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + login;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MoreList other = (MoreList) obj;
        if (login != other.login)
            return false;
        return true;
    }


}
