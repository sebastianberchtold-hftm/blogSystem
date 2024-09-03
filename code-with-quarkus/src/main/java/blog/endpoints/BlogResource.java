package blog.endpoints;

import java.util.List;

import blog.control.BlogService;
import blog.model.BlogDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    BlogService blogService;

    @GET
    public List<BlogDTO> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GET
    @Path("{id}")
    public BlogDTO getBlog(@PathParam("id") Long id) {
        return blogService.getBlogById(id);
    }

    @POST
    @Operation(summary = "Create a new blog", description = "Creates a new blog entry")
    @APIResponse(responseCode = "201", description = "Blog created successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BlogDTO.class)))
    @APIResponse(responseCode = "400", description = "Invalid input")
    @Transactional
    public Response createBlog(@RequestBody(description = "Blog to create", required = true, content = @Content(schema = @Schema(implementation = BlogDTO.class))) BlogDTO blogDTO) {
        BlogDTO createdBlog = blogService.createBlog(blogDTO);
        return Response.status(Response.Status.CREATED).entity(createdBlog).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
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
    @Transactional
    public Response deleteBlog(@PathParam("id") Long id) {
        blogService.deleteBlog(id);
        return Response.noContent().build();
    }
}
