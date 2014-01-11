package spring.klasa;

import java.util.List;

import dto.Klasa;

public interface KlasaManager {

    Integer insertKlasa(Klasa klasa, String login);

    Klasa getKlasaById(int klasaId);

    List<Klasa> getAllKlasy(String login);

    /**
     * Returns all logins from studentLogin class.
     * 
     * @param studentLogin
     * @return
     */
    List<String> getStudentsLogins(String studentLogin);

}
