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
import entity.Funcionario;
import persistence.FuncionarioDAO;

@Path("/funcionario")

public class FuncionarioService extends HttpServlet {
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

	private FuncionarioDAO dao;

	public FuncionarioService() {
		dao = new FuncionarioDAO();
	}

	@Path("/criar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST // Passando o body pelo Postman
	public Response createFuncionario(Funcionario funcionario) {
		try {
			dao.create(funcionario);
		} catch (Exception err) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar funcionario, erro: " + err)
					.build();
		}
		return Response.status(Status.OK).entity("Funcionario criado com sucesso").build();
	}

	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public Response readFuncionario() throws Exception {

		List<Funcionario> funcionarios = new ArrayList<>();
		GenericEntity<List<Funcionario>> entity = null;
		try {
			funcionarios = dao.read();
			entity = new GenericEntity<List<Funcionario>>(funcionarios) {
			};
		} catch (Exception err) {
			err.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar funcionarios, erro: " + err)
					.build();
		}

		return Response.status(Status.OK).entity(entity).build();
	}

	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public Response findFuncionario(@PathParam("id") Integer id) {
		List<Funcionario> funcionarios = new ArrayList<>();
		GenericEntity<List<Funcionario>> entity = null;
		try {
			funcionarios = dao.find(id);
			entity = new GenericEntity<List<Funcionario>>(funcionarios) {
			};

		} catch (Exception err) {

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar funcionario, erro: " + err)
					.build();
		}

		return Response.status(Status.OK).entity(entity).build();
	}

	@Path("/atualizar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response updateFuncionario(@PathParam("id") Integer id, Funcionario funcionario) {
		try {
			dao.update(id, funcionario);
		} catch (Exception err) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Erro ao atualizar um funcionario, erro: " + err).build();
		}
		return Response.status(Status.OK).entity("Funcionario alterado com sucesso").build();
	}

	@Path("excluir/{id}") // Funciona pelo Postman, mas pelo Browser não
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteFuncionario(@PathParam("id") Integer id) {
		try {
			dao.delete(id);
		} catch (Exception err) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao apagar funcionario").build();
		}
		return Response.status(Status.OK).entity("Funcionario de id " + id + " foi excluido com sucesso").build();
	}

}