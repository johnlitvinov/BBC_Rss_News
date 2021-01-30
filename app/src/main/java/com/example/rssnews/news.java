package com.example.rssnews;

public class news {
    // Variable to store data corresponding
    // to title keyword in database
    private String title;

    // Variable to store data corresponding
    // to description keyword in database
    private String description;

    // Variable to store data corresponding
    // to link keyword in database
    private String link;

    // Variable to store data corresponding
    // to pubDate keyword in database
    private String pubDate;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public news() {}

   //Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
} //end class