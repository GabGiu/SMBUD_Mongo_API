package it.polimi.gabrielegiusti.services.articles;

import it.polimi.gabrielegiusti.classes.models.ScientificArticleModel;
import it.polimi.gabrielegiusti.engines.articlesEngine;
import it.polimi.gabrielegiusti.responses.ScientificArticleResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/")
public class articlesService {

    final String OK = "200";
    final String NOT_FOUND = "404";
    final String SUCCESS = "Success";
    final String NOTFOUND = "Not Found";

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createArticle(ScientificArticleModel scientificArticleModel){

        articlesEngine engine = new articlesEngine();
        ScientificArticleResponse response = new ScientificArticleResponse();

        boolean result = engine.createArticles(scientificArticleModel);

        return getResponse(scientificArticleModel, response, result);

    }

    @POST
    @Path("createMultiple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMultipleArticles(List<ScientificArticleModel> scientificArticleModels){
        articlesEngine engine = new articlesEngine();
        ScientificArticleResponse response = new ScientificArticleResponse();

        boolean result = engine.createMultipleArticles(scientificArticleModels);

        return getResponse(scientificArticleModels.get(0), response, result);
    }

    @GET
    @Path("read/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readArticle(@PathParam("id") String id){

        articlesEngine engine = new articlesEngine();
        ScientificArticleResponse response = new ScientificArticleResponse();

        ScientificArticleModel result = engine.readArticles(id);

        if (result != null){
            response.setTitle(result.getTitle());
            response.setResult(SUCCESS);
            response.setCode(OK);
            return Response.status(Response.Status.OK).entity(response).build();
        }

        response.setTitle("No data detected");
        response.setResult(NOTFOUND);
        response.setCode(NOT_FOUND);

        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArticle(@PathParam("id") String id, ScientificArticleModel newValue){

        articlesEngine engine = new articlesEngine();
        ScientificArticleResponse response = new ScientificArticleResponse();

        boolean result = engine.updateArticles(id, newValue);

        return getResponse(newValue, response, result);
    }

    private Response getResponse(ScientificArticleModel newValue, ScientificArticleResponse response, boolean result) {
        if (result){
            response.setTitle(newValue.getTitle());
            response.setResult(SUCCESS);
            response.setCode(OK);

            return Response.status(Response.Status.OK).entity(response).build();
        }

        response.setTitle(newValue.getTitle());
        response.setResult(NOTFOUND);
        response.setCode(NOT_FOUND);

        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") String id){
        
        articlesEngine engine = new articlesEngine();
        ScientificArticleResponse response = new ScientificArticleResponse();

        ScientificArticleModel deletedItem = engine.readArticles(id);

        boolean result = engine.deleteArticles(id);

        return getResponse(deletedItem, response, result);
    }
}
