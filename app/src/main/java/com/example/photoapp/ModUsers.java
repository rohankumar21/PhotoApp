package com.example.photoapp;
<<<<<<< HEAD

public class ModUsers {
        String name, email, image, uid;
        public ModUsers() {
        }

        String onlineStatus;
        String typingTo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypingTo() {
            return typingTo;
        }

        public void setTypingTo(String typingTo) {
            this.typingTo = typingTo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public ModUsers(String name, String onlineStatus, String typingTo, String email, String image, String uid) {
            this.name = name;
            this.onlineStatus = onlineStatus;
            this.typingTo = typingTo;
            this.email = email;
            this.image = image;
            this.uid = uid;
        }
    }

=======
public class ModUsers {

    private String onlineStatus;
    private String typingTo;
    private String name;
    private String email;
    private String image;
    private String uid;

    public ModUsers(String name, String onlineStatus, String typingTo, String email, String image, String uid) {
        this.name = name;
        this.onlineStatus = onlineStatus;
        this.typingTo = typingTo;
        this.email = email;
        this.image = image;
        this.uid = uid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypingTo() {
        return typingTo;
    }

    public void setTypingTo(String typingTo) {
        this.typingTo = typingTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
>>>>>>> cd4c86e7730c5dd37e4c9c85563ab178cfc837a0
