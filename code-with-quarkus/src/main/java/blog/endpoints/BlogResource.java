package blog.endpoints;

import blog.entity.Blog;
import blog.repository.BlogRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    BlogRepository blogRepository;

    @GET
    public Response getAllBlogs() {
        return Response.ok(blogRepository.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getBlogById(@PathParam("id") Long id) {
        Blog blog = blogRepository.findById(id);
        return Response.ok(blog).build();
    }

    @POST
    public Response createBlog(Blog blog) {
        blogRepository.persist(blog);
        return Response.status(Response.Status.CREATED).entity(blog).build();
    }

    @PUT
    @Path("{id}")
    public Response updateBlog(@PathParam("id") Long id, Blog updatedBlog) {
        Blog blog = blogRepository.findById(id);
        blog.setTitle(updatedBlog.getTitle());
        blog.setAuthor(updatedBlog.getAuthor());
        blog.setContent(updatedBlog.getContent());
        blogRepository.persist(blog);
        return Response.ok(blog).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBlog(@PathParam("id") Long id) {
        Blog blog = blogRepository.findById(id);
        blogRepository.delete(blog);
        return Response.noContent().build();
    }
}
