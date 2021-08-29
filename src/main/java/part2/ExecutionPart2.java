package part2;

import classes.Student;
import classes.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionPart2 {
    public static void main(String[] args) {

        // OBS: Esse codigo deve executar SEM ERROS com a implementacao JPA que foi definida no "persistence.xml".
        // Se estiver somente com o JPA baixado, o codigo NAO IRA funcionar.

        // O ideal é que nessa parte (Parte 2) o codigo EXECUTE SEM ERROS, pois aqui tera uma implementacao JPA sendo utilizada.

        // 1 - Passos iniciais para criar um gerenciadop de entidades com o banco de dados especificado no arquivo  "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 2.1 - Criar instancias para serem adicionadas no banco de dados
        State stateToAdd = new State("Rio de Janeiro", "RJ");
        Student studentToAdd = new Student("Sandro", 48, stateToAdd);
        //Student studentToAdd2 = new Student("Fabiana", 41, stateToAdd);

        // 2.2 - Iniciar uma trasacao para adiconar as instancias no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(stateToAdd);
        entityManager.persist(studentToAdd);
        //entityManager.persist(studentToAdd2);

        entityManager.getTransaction().commit();

        // 3 - Resgatar instâncias no banco de dados
        State foundState = entityManager.find(State.class, 1);
        Student foundStudent = entityManager.find(Student.class, 1);

        System.out.println(foundState);
        System.out.println(foundStudent);

        // 4 - Alterar uma entidade
//        entityManager.getTransaction().begin();
//
//        foundStudent.setName("Karam");
//        foundStudent.setAge(20);
//
//        entityManager.getTransaction().commit();
//
//        System.out.println(foundStudent);

        // 5 - Remover uma entidade
//        entityManager.getTransaction().begin();
//
//        entityManager.remove(foundStudent);
//
//        entityManager.getTransaction().commit();

        // 6 - Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.
        entityManager.close();
        entityManagerFactory.close();

    }
}
