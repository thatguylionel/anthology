package de.tgl.anthology;

import de.tgl.anthology.pojos.FilePojo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static de.tgl.anthology.utilities.ImageResizer.listFilesForFolder;

@Path("/")
public class AnthologyController {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("")
    public String serviceUp() {
        return "OK";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/rescaleImages")
    public boolean convertImages(@Context HttpHeaders headers, FilePojo filePojo) {
        listFilesForFolder( filePojo.getReadFromDirectory(), filePojo.getWriteToDirectory(), 1024, 768);
        return true;
    }

}
