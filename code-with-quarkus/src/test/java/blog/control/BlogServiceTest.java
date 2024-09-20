package blog.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blog.model.BlogDTO;
import blog.repository.BlogRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blog.entity.Blog;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@QuarkusTest
public class BlogServiceTest {
    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        PanacheQuery<Blog> blogPanacheQuery = Mockito.mock(PanacheQuery.class);
        Blog sampleBlog = new Blog();
        sampleBlog.setTitle("Sample Title");
        sampleBlog.setAuthor("Sample Author");
        sampleBlog.setContent("Sample Content");

        Mockito.when(blogPanacheQuery.list()).thenReturn(List.of(sampleBlog));
        Mockito.when(blogRepository.findAll()).thenReturn(blogPanacheQuery);
    }

  /*  @Test
    void testGetAllBlogs() {
        List<BlogDTO> result = blogService.getAllBlogs();

        assertEquals(0, result.size());
        assertEquals("Sample Title", result.get(0).title());
        assertEquals("Sample Content", result.get(0).content());
    }*/
}