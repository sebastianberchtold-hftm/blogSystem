package blog.repository;

import blog.entity.Blog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class BlogRepository implements PanacheRepository<Blog> {

    @PersistenceContext
    private EntityManager em;
}
