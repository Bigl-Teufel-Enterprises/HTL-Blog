package at.biglmatthias.WeLift.article;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "articles")
public class Article {
    @Id
   @SequenceGenerator(
           name = "article_sequence",
           sequenceName = "article_sequence",
           allocationSize = 1
   )
    @GeneratedValue(
              strategy = GenerationType.SEQUENCE,
              generator = "article_sequence"
    )


    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate date;
    private String image;
    private String category;

    public Article() {
    }
    public Article(Long id, String title, String content, String author, LocalDate date, String image, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.image = image;
        this.category = category;
    }
    public Article(Long id, String title, String content, String author, LocalDate date,  String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
    }
    public Article(String title, String content, String author, LocalDate date, String image, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.image = image;
        this.category = category;
    }
    public Article(String title, String content, String author, LocalDate date, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
