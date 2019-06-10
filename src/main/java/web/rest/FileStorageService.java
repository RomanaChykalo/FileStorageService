package web.rest;

import model.Type;
import model.UserFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/storageREST")
public interface FileStorageService {
    @GET
    @Path("/files")
    @Produces("application/json; charset=UTF-8")
    Response getAllFiles();

    @GET
    @Path("/files/{name}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response findByName(@PathParam("name") String name);

    @POST
    @Path("/files")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response addFile(@RequestBody UserFile userFile);

    @DELETE
    @Path("/files/{name}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response removeFile(@PathParam("name") String name);

    @GET
    @Path("/files/type/{type}")
    @Produces("application/json; charset=UTF-8")
    Response getOneTypeFilesList(@PathParam("type") Type type);

}
