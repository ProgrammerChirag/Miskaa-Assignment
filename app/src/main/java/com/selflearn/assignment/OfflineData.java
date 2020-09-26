package com.selflearn.assignment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OfflineData {


    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "capital")
    String capital;

    @ColumnInfo(name = "region")
    String region;

    @ColumnInfo(name = "subregion")
    String subregion;

    @ColumnInfo(name = "flag")
    String flag;

    @PrimaryKey(autoGenerate = true)
    long population;

    @ColumnInfo(name = "borders")
    String borders;

    @ColumnInfo(name = "languages")
    String languages;


    public OfflineData(){}
    public OfflineData(String name, String capital, String region, String subregion, String flag, long population, String borders, String languages) {

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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
