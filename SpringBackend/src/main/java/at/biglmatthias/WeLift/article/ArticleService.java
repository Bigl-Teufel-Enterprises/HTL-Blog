package at.biglmatthias.WeLift.article;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ArticleService {

    public List<Article> getArticles() {
       return List.of(
               new Article("Title 1", "Content 1", "Author 1", LocalDate.now(), "image1.jpg", "Category 1"),
                new Article("Title 2", "Content 2", "Author 2", LocalDate.now(), "image2.jpg", "Category 2"),
                new Article("Title 3", "Content 3", "Author 3", LocalDate.now(), "image3.jpg", "Category 3")
       );

    }
}
