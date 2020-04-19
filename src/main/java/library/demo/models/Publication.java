package library.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publication")
public class Publication {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @JsonManagedReference
    User user;

    @Id
    private int id;

    @Column
    private String type;

    @Column(name = "share_date")
    private String shareDate;

    @OneToOne
    @JoinColumn(name = "file")
    @JsonManagedReference
    private File file;

    @Column
    private String title;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover")
    @JsonManagedReference
    private File cover;


    @Column(name = "page_count")
    private int pageCount;


    @Column
    private String genere;

    @Column
    private String author;

    @Column(name = "overview")
    private String overview;

    @Column(name = "stars")
    private double stars;


    @Column(name = "visits")
    private int visits;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>(0);

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Like> likes = new ArrayList<>(0);

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getCover() {
        return cover;
    }

    public void setCover(File cover) {
        this.cover = cover;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "user=" + user.getUserName() +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", shareDate='" + shareDate + '\'' +
                ", file=" + file.getFileName() +
                ", title='" + title + '\'' +
                ", cover=" + cover +
                ", pageCount=" + pageCount +
                ", genere='" + genere + '\'' +
                ", author='" + author + '\'' +
                ", overview='" + overview + '\'' +
                ", stars=" + stars +
                ", visits=" + visits +
                ", comments=" + comments +
                '}';
    }
}
