package blog.repository;

import java.util.ArrayList;
import java.util.List;

import blog.entity.Blog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<Blog> {

    private List<Blog> blogs = new ArrayList<>();

    public BlogRepository() {
        blogs.add(new Blog());
        blogs.add(new Blog());
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void addBlog(Blog blog) {
        blogs.add(blog);
    }
}