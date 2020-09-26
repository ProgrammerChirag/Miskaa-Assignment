package com.selflearn.assignment;


public class RecyclerViewData  {

    String name;

    String capital;

    String region;

    String subregion;

    String flag;

    String population;

    String[] borders;

    Languages[] languages;


    public RecyclerViewData(){}
    public RecyclerViewData(String name, String capital, String region, String subregion, String flag, String population, String[] borders, Languages[] languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.flag = flag;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public Languages[] getLanguages() {
        return languages;
    }

    public void setLanguages(Languages[] languages) {
        this.languages = languages;
    }
}
