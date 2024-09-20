package blog.control;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import blog.model.BlogDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import blog.entity.Blog;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class BlogServiceTest {
    @Inject
    BlogService blogService;

    @Test
    @Transactional
    public void listingAndAddingBlogs() {
      /*
        blogService.createBlog(new BlogDTO("Title", "Content"));
        List<BlogDTO> blogs = blogService.getAllBlogs();
        assertEquals(1, blogs.size());*/
    }
}