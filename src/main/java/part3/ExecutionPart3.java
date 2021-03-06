package part3;

import classes.Student;
import classes.State;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
public class ExecutionPart3 {

    public static void main(String[] args) {

        // 1 - Dados instanciados para serem utilizados como exemplo
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        State stateToAdd = new State("Rio de Janeiro", "RJ");
        entityManager.persist(stateToAdd);
        entityManager.persist(new State("Sao Paulo", "SP"));
        entityManager.persist(new Student("Daniel", 29, stateToAdd));
        entityManager.persist(new Student("Joao", 20, stateToAdd));
        entityManager.persist(new Student("Pedro", 30, stateToAdd));
        entityManager.getTransaction().commit();

        // 2 - Vamos utilizar o método do EntityManager find(), SQL nativo, JPQL e JPA Criteria API para realizar uma
        // consulta que retornarar o mesmo valor equivalente aos seguintes SQL:
        // SELECT * FROM Student WHERE id = 1 (Equivalente ao método find do entityManager na parte 2.2)
        // SELECT * FROM Student WHERE name = 'Daniel' (Sera o equivalente para as outras consultas nas partes 2.3 - 2.4 - 2.5)

        // 2.1 O parametro de busca que será utilizado nas proximas consultas
        String name = "Daniel";

        // =============================================================================================================

        // 2.2 - Utilizando o método find do entityManager
        // Trazendo somente 1 resultado
        Student studentEntityManager = entityManager.find(Student.class, 1);

        // Trazendo uma lista como resultado
        // Nao eh possivel!!! Deve utilizar um dos métodos utilizados abaixos nas partes 2.3 - 2.4 - 2.5

        // Resultados das consultas acima
        System.out.println("Query studentEntityManager: " + studentEntityManager);

        // =============================================================================================================

        // 2.3 - SQL nativo

//        // Trazendo somente 1 resultado
//        String sql = "SELECT * FROM Student WHERE name = :name ";
//        Student studentSQL = (Student) entityManager
//                .createNativeQuery(sql, Student.class)
//                .setParameter("name", name)
//                .getSingleResult();
//
//        // Trazendo uma lista como resultado
//        String sqlList = "SELECT * FROM Student";
//        List<Student> studentSQLList = entityManager
//                .createNativeQuery(sqlList, Student.class)
//                .getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Query studentSQL: " + studentSQL);
//        studentSQLList.forEach(Student -> System.out.println("Query studentSQLList: " + Student));

        // =============================================================================================================

        // 2.4 - JPQL

//        // Trazendo somente 1 resultado
        String jpql = "select a from Student a where a.name = :name";
        Student studentJPQL = entityManager
                .createQuery(jpql, Student.class)
                .setParameter("name", name)
                .getSingleResult();

        // Trazendo uma lista como resultado
        String jpqlList = "select a from Student a where a.state = :state";
        List<Student> studentJPQLList = entityManager
                .createQuery(jpqlList, Student.class)
                .setParameter("state", stateToAdd)
                .getResultList();

        // Resultados das consultas acima
        System.out.println("Query studentJPQL: " + studentJPQL);
        studentJPQLList.forEach(Student -> System.out.println("Query studentJPQLList: " + Student));

        // =============================================================================================================

        // 2.5 - JPA Criteria API + JPA Metamodel

//        // Trazendo somente 1 resultado
//        CriteriaQuery<Student> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Student.class);
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(studentRoot.get(Student_.name));
//        inClause.value(name);
//        criteriaQuery.select(studentRoot).where(inClause);
//        Student studentAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();
//
//        // Trazendo uma lista como resultado
//        CriteriaQuery<Student> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Student.class);
//        Root<Student> studentRootList = criteriaQueryList.from(Student.class);
//        List<Student> studentAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Query studentAPICriteria: " + studentAPICriteria);
//        studentAPICriteriaList.forEach(Student -> System.out.println("Query studentAPICriteriaList: " + Student));

    }

}

