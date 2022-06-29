package sg.edu.rp.c346.id20007649.fyp;


import java.io.Serializable;

public class Video implements Serializable {

    private int id;
    private String name;
    private String video;
    private String description;


    public Video(int id, String name, String video, String description) {

        this.id = id;
        this.video = video;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;

    }

    public String getName() {
        return name;

    }

    public String getDescription() {
        return description;

    }

    public String getVideo() {
        return video;

    }



}

