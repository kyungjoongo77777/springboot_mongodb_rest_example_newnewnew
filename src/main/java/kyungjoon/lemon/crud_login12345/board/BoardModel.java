package kyungjoon.lemon.crud_login12345.board;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Board")
public class BoardModel {

    @Id
    private String id;
    private String title;
    private String contents;
    private String author;
    private String createdDt;
    private Boolean isAdmin;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
