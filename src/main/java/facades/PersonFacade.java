package facades;

import entities.Person;
import entities.PersonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public static void main(String[] args) {
        
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        PersonFacade pf = new PersonFacade();
                pf.deletePerson(1);
    }
    
        public List<PersonDTO> findByID(int id) {
        EntityManager em = emf.createEntityManager(); 
        try {
            Query query = em.createNamedQuery("Persons.FindByID");
            query.setParameter("id", id); 
             List<Person> resultList = query.getResultList(); 
            List<PersonDTO> DTOResult = new ArrayList();
        for (Person person : resultList) {
            DTOResult.add(new PersonDTO(person));
        }
            return DTOResult; 
        }
        finally {
            em.close();
        }
    }
    
    
    public static List<PersonDTO> getAllPerson(){
        EntityManager em = getEntityManager();
        
              try {
            Query query = em.createNamedQuery("Persons.getAll");
            //return new Gson().toJson(query.getResultList());
            List<Person> resultList = query.getResultList(); 
            List<PersonDTO> DTOResult = new ArrayList();
        for (Person person : resultList) {
            DTOResult.add(new PersonDTO(person));
        }
            return DTOResult; 
              }
        finally {
            em.close();
        }
    }
    
    public PersonDTO addPerson(String firstName, String lastName, String phone){
        EntityManager em = emf.createEntityManager();
        
        Person p = new Person(firstName, lastName, phone);
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        }finally {
            em.close();
        }
        return new PersonDTO(p);
    }
    
    public PersonDTO editPerson(PersonDTO p){
            EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Person find = em.find(Person.class, p.getId());
            find.setFirstName(p.getFirstName());
            find.setFirstName(p.getLastName());
            find.setFirstName(p.getPhone());
            find.setLastEdited(new Date());
            em.getTransaction().commit();
            
        }finally {
            em.close();
        }
        
        return p;
        
        
        
    }
    
    public PersonDTO deletePerson(int id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Person find = em.find(Person.class, Long.valueOf(id));
            PersonDTO pdto = new PersonDTO(find);
            em.remove(find);
            em.getTransaction().commit();
        return pdto;
        }finally {
            em.close();
        }
    }
    
    

}
