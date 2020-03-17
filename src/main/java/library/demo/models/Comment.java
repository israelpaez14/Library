package library.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_publication")
    private Publication publication;

    @Column
    private String content;

    @Column(name = "publish_date")
    private String publishDate;

    @Column
    private  int likes;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", publication=" + publication +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", likes=" + likes +
                ", username=" + username.getUserName() +
                '}';
    }
}
