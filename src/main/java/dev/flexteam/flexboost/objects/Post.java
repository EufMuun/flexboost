package dev.flexteam.flexboost.objects;

public class Post {
    private String userEmail;
    private String postID;
    private String postText;
    private String postContent;

    public void setUserID(String userID) {
        this.userEmail = userID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getEmail() {
        return userEmail;
    }

    public String getPostID() {
        return postID;
    }
}
