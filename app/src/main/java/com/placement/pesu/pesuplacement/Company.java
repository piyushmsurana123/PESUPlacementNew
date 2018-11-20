package com.placement.pesu.pesuplacement;

import org.json.JSONObject;

public class Company {

    private String ctc;
    private String company;
    private String link;
    private JSONObject companyDetailsJson;
    private String eligibility;

    public JSONObject getCompanyDetailsJson() {
        return companyDetailsJson;
    }

    public void setCompanyDetailsJson(JSONObject companyDetailsJson) {
        this.companyDetailsJson = companyDetailsJson;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }
}