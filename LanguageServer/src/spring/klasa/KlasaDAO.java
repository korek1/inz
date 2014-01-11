package spring.klasa;

import java.util.List;

import spring.dao.BaseDAO;
import dto.Klasa;

public interface KlasaDAO extends BaseDAO<Klasa> {

    List<Klasa> getAllKlasy(String login);

}
