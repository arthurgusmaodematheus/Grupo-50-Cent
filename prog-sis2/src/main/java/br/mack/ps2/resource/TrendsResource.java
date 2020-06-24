package br.mack.ps2.resource;

import br.mack.ps2.api.Result;
import br.mack.ps2.api.Trends;
import br.mack.ps2.dao.EconomiaDAOMySQL;
import br.mack.ps2.dao.TrendsDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("trends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TrendsResource {

    EconomiaDAOMySQL dao1 = new EconomiaDAOMySQL();
    TrendsDao dao = new TrendsDao(dao1);


    @GET
    public Trends getEconomia() {

        return dao.getAllTrends();
    }


    @PUT
    @Path("/update/{data}")
    public void updateResult(@PathParam("data") String data) {
        dao.updateResult(getResultFromString(data));
    }

    public Result getResultFromString(String data) {
        Result result = new Result();
        result.setDate(result.getDate());
        result.setValue(result.getValue());
        return result;
    }
}