package com.example.myapplication2;

public class model {
    String name,location,contact,email,blood,purl;
    model()
    {

    }

    public model(String name, String location, String email, String contact,String blood) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.contact = contact;
        this.blood=blood;
    }

    public String getPurl() {

        if(getBlood().equals("a+"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_A-Positive-1.png";
        }
        else if(getBlood().equals("b+"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_B-Positive-1.png";
        }
        else if(getBlood().equals("ab+"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_AB-Positive-1.png";
        }
        else if(getBlood().equals("o+"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_O-Positive-1.png";
        }
        else if(getBlood().equals("a-"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_A-Negative-1.png";
        }
        else if(getBlood().equals("b-"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_B-Negative-1.png";
        }
        else if(getBlood().equals("ab-"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_AB-Negative-1.png";
        }
        else if(getBlood().equals("o-"))   {
            this.purl = "https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_O-Negative-1.png";
        }
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getContact() {
        return contact;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
