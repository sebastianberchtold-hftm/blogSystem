package blog.control;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import blog.entity.Blog;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class BlogServiceTest {
    @Inject
    BlogService blogService;

    @Test
    void listingAndAddingBlogs() {
        // Arrange
        Blog blog = new Blog();
        int blogsBefore;
        List<Blog> blogs;

        // Act
        blogsBefore = blogService.getAllBlogs().size();
        blogService.addBlog(blog);
        blogs = blogService.getAllBlogs();

        // Assert
        assertEquals(blogsBefore + 1, blogs.size());
        assertEquals(blog, blogs.get(blogs.size() - 1));
    }
}