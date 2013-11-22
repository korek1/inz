package spring.klasa.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.klasa.KlasaDAO;
import spring.klasa.KlasaManager;
import dto.Klasa;
import dto.Teacher;

@Service
public class KlasaManagerImpl implements KlasaManager {
    
    @Autowired
    private KlasaDAO klasaDAO;

    @Override
    @Transactional
    public void insertKlasa(Klasa klasa, String login)
    {
        @SuppressWarnings("unchecked")
        List<Teacher> list = klasaDAO.getSession()
        .createCriteria(Teacher.class)
        .add(Restrictions.eq("login", login))
        .list();
        Teacher teacher = list.get(0);

        klasa.setTeacher(teacher);
        teacher.getKlasy().add(klasa);
        
        klasaDAO.save(klasa);        
    }

    @Override
    @Transactional
    public Klasa getKlasaById(int klasaId)
    {
        Klasa klasa = klasaDAO.get(klasaId);
        klasa.getStudents().isEmpty();
        
        return klasa;
    }

    @Override
    @Transactional
    public List<Klasa> getAllKlasy(String login)
    {

        return klasaDAO.getAllKlasy(login);
    
    }

}
