package spring.klasa;

import java.util.List;

import dto.Klasa;

public interface KlasaManager {
    
    void insertKlasa(Klasa klasa, String login);

    Klasa getKlasaById(int klasaId);
    
    List<Klasa> getAllKlasy(String login);

}
