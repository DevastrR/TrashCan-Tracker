package com.example.tracker.HelperClass.AdapterClass;

public class ComplaintHelperClass {
    String title_complaint , description_complaint ;

    public ComplaintHelperClass(String title_complaint, String description_complaint) {
        this.title_complaint = title_complaint;
        this.description_complaint = description_complaint;
    }

   public String getTitle() {
        return title_complaint;
    }

    public String getDescription() {
        return description_complaint;
    }
}
