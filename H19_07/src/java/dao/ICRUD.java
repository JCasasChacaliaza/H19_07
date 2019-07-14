package dao;

import java.util.List;

public interface ICRUD<Modelo> {

    public void registrar(Modelo modelo) throws Exception;

    public void modificar(Modelo modelo) throws Exception;

    public void eliminar(Modelo modelo) throws Exception;

    List<Modelo> Listar() throws Exception;

}
