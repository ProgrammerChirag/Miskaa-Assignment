package com.selflearn.assignment;

public class Languages {
    public  String nativeName;

    public String iso639_2;

    public String name;

    public String iso639_1;

    public String getNativeName ()
    {
        return nativeName;
    }

    public void setNativeName (String nativeName)
    {
        this.nativeName = nativeName;
    }

    public String getIso639_2 ()
    {
        return iso639_2;
    }

    public void setIso639_2 (String iso639_2)
    {
        this.iso639_2 = iso639_2;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIso639_1 ()
    {
        return iso639_1;
    }

    public void setIso639_1 (String iso639_1)
    {
        this.iso639_1 = iso639_1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nativeName = "+nativeName+", iso639_2 = "+iso639_2+", name = "+name+", iso639_1 = "+iso639_1+"]";
    }
}