package br.mack.ps2.dao;

import br.mack.ps2.api.Economia;
import br.mack.ps2.api.Result;
import br.mack.ps2.api.Trends;
import java.text.SimpleDateFormat;
import java.util.List;

public class TrendsDao {
    Trends dataBase;
    EconomiaDao economiaDAO;

    public TrendsDao(EconomiaDao economiaDAO) {
        this.economiaDAO = economiaDAO;
        this.dataBase = new Trends();
        read();
    }


    public void read() {

        List<Economia> economias = economiaDAO.read();
        List<Economia> economias2 = economias;
        for (int i = 0; i < economias.size(); i++) {

            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
            Economia economia = economias.get(i);


            Result result = new Result();
            result.setDate(in.format(economia.getDate()));
            result.setValue(economia.getValue());
            this.dataBase.getResults().add(result);
        }


    }

    public Trends getAllTrends() {
        return this.dataBase;
    }


    public void updateResult(Result result) {

        for (int i = 0; i < this.dataBase.getResults().size(); i++) {
            if (this.dataBase.getResults().get(i).getDate().equals(result.getDate())) {
                this.dataBase.getResults().set(i, result); //fixme: no delete é só trocar esse set por remove
            }
        }


    }
}