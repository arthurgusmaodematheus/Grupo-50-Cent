package br.mack.ps2.dao;

import br.mack.ps2.api.Economia;

import java.util.List;

public interface EconomiaDao {
    boolean create(Economia economia);
    List<Economia> read();
    boolean update(Economia economia);
    boolean delete(Economia economia);


}
