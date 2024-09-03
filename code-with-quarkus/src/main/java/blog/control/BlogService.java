package blog.control;

import java.util.List;
import java.util.stream.Collectors;

import blog.entity.Blog;
import blog.model.BlogDTO;
import blog.repository.BlogRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BlogService {
    @Inject
    BlogRepository blogRepository;

    public Blog fromDTO(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setTitle(blogDTO.title());
        blog.setContent(blogDTO.content());
        return blog;
    }

    public BlogDTO toDTO(Blog blog) {
        return new BlogDTO(blog.getTitle(), blog.getContent());
    }

    public List<BlogDTO> getAllBlogs() {
        return blogRepository.listAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BlogDTO getBlogById(Long id) {
        return toDTO(blogRepository.findById(id));
    }

    public BlogDTO createBlog(BlogDTO blogDTO) {
        Blog blog = fromDTO(blogDTO);
        blogRepository.persist(blog);
        return toDTO(blog);
    }

    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        Blog blog = blogRepository.findById(id);
        if (blog != null) {
            blog.setTitle(blogDTO.title());
            blog.setContent(blogDTO.content());
            blogRepository.persist(blog);
            return toDTO(blog);
        }
        return null;
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}