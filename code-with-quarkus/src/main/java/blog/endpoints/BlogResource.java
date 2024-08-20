package blog.endpoints;

import java.util.List;

import blog.control.BlogService;
import blog.model.BlogDTO;
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
public class BlogResource {

    @Inject
    BlogService blogService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BlogDTO> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBlog(BlogDTO blogDTO) {
        BlogDTO createdBlog = blogService.createBlog(blogDTO);
        return Response.status(Response.Status.CREATED).entity(createdBlog).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBlog(@PathParam("id") Long id, BlogDTO blogDTO) {
        BlogDTO updatedBlog = blogService.updateBlog(id, blogDTO);
        if (updatedBlog != null) {
            return Response.ok(updatedBlog).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBlog(@PathParam("id") Long id) {
        blogService.deleteBlog(id);
        return Response.noContent().build();
    }
}
