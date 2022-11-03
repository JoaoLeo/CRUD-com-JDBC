package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entity.Setor;
import persistence.SetorDAO;

@Path("/setor")

public class SetorService extends HttpServlet{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;
    /**
     * 
     */
    
    private SetorDAO dao;

    public SetorService() {
        dao = new SetorDAO();
    }

    @Path("/criar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST // Passando o body pelo Postman
    public Response createSetor(Setor setor) {
        try {
            dao.create(setor);
        } catch (Exception err) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar setor, erro: " + err).build();
        }
        return Response.status(Status.OK).entity("Setor criado!").build();
    }

    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public Response readSetor() throws Exception {

        List<Setor> setores = new ArrayList<>();
        GenericEntity<List<Setor>> entity = null;
        try {
            setores = dao.read();
            entity = new GenericEntity<List<Setor>>(setores) {
            };
        } catch (Exception err) {
            err.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar setores, erro: " + err).build();
        }

        return Response.status(Status.OK).entity(entity).build();
    }

    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public Response findSetor(@PathParam("id") Integer id) {
        List<Setor> setores = new ArrayList<>();
        GenericEntity<List<Setor>> entity = null;
        try {
            setores = dao.findSetor(id);
            entity = new GenericEntity<List<Setor>>(setores) {
            };

        } catch (Exception err) {

            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar setor, erro: " + err).build();
        }

        return Response.status(Status.OK).entity(entity).build();
    }

    @Path("/atualizar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateSetor(@PathParam("id") Integer id, Setor setor) {
        try {
            dao.update(id, setor);

        } catch (Exception err) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar setor, erro: " + err).build();
        }
        return Response.status(Status.OK).entity("Setor alterado!").build();
    }

    @Path("/excluir/{id}") // Funciona pelo Postman, mas pelo Browser não
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response deleteSetor(@PathParam("id") Integer id) {
        try {
            dao.delete(id);
        } catch (Exception err) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao apagar setor").build();
        }
        return Response.status(Status.OK).entity("Setor de id " + id + " foi excluido").build();
    }

}