package library.demo.models;

import com.fasterxml.jackson.annotation.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "file")
@JsonIgnoreProperties("path")
public class File {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "filename")
    private String fileName;

    @JsonIgnore
    @Column(name = "path")
    private String path;

    @Column(name = "extencion")
    private String extencion;

    @Column(name = "size")
    private double size;

    @Column(name = "upload_date")
    private String upload_date;


    @JsonBackReference
    @OneToOne(mappedBy = "file", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)

    private Publication publication;



    @JsonBackReference
    @OneToOne(mappedBy = "cover", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)

    private Publication publicationCover;

    public File() {
    }

    public Publication getPublicationCover() {
        return publicationCover;
    }

    public void setPublicationCover(Publication publicationCover) {
        this.publicationCover = publicationCover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtencion() {
        return extencion;
    }

    public void setExtencion(String extencion) {
        this.extencion = extencion;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String toString() {
        return String.format("{ id=%s, filename=%s, path=%s, extencion=%s, size=%s, " +
                        "upload_date=%s, publication=%s}", this.id + "", this.fileName,
                this.path,
                this.extencion, this.size + "", this.upload_date, this.publication == null ? "" :
                        this.publication.getTitle());
    }

}
