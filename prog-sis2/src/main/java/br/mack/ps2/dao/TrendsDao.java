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