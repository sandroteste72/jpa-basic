package part3;

import classes.Aluno;
import classes.Estado;

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
        // SELECT * FROM Student WHERE nome = 'Daniel' (Sera o equivalente para as outras consultas nas partes 2.3 - 2.4 - 2.5)

        // 2.1 O parametro de busca que será utilizado nas proximas consultas
        String nome = "Daniel";

        // =============================================================================================================

        // 2.2 - Utilizando o método find do entityManager
        // Trazendo somente 1 resultado
        Student studentEntityManager = entityManager.find(Student.class, 1);

        // Trazendo uma lista como resultado
        // Nao eh possivel!!! Deve utilizar um dos métodos utilizados abaixos nas partes 2.3 - 2.4 - 2.5

        // Resultados das consultas acima
        System.out.println("Consulta alunoEntityManager: " + studentEntityManager);

        // =============================================================================================================

        // 2.3 - SQL nativo

//        // Trazendo somente 1 resultado
//        String sql = "SELECT * FROM Student WHERE nome = :nome ";
//        Student alunoSQL = (Student) entityManager
//                .createNativeQuery(sql, Student.class)
//                .setParameter("nome", nome)
//                .getSingleResult();
//
//        // Trazendo uma lista como resultado
//        String sqlList = "SELECT * FROM Student";
//        List<Student> alunoSQLList = entityManager
//                .createNativeQuery(sqlList, Student.class)
//                .getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Consulta alunoSQL: " + alunoSQL);
//        alunoSQLList.forEach(Student -> System.out.println("Consulta alunoSQLList: " + Student));

        // =============================================================================================================

        // 2.4 - JPQL

//        // Trazendo somente 1 resultado
//        String jpql = "select a from Student a where a.nome = :nome";
//        Student alunoJPQL = entityManager
//                .createQuery(jpql, Student.class)
//                .setParameter("nome", nome)
//                .getSingleResult();
//
//        // Trazendo uma lista como resultado
//        String jpqlList = "select a from Student a where a.estado = :estado";
//        List<Student> alunoJPQLList = entityManager
//                .createQuery(jpqlList, Student.class)
//                .setParameter("estado", estadoParaAdicionar)
//                .getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Consulta alunoJPQL: " + alunoJPQL);
//        alunoJPQLList.forEach(Student -> System.out.println("Consulta alunoJPQLList: " + Student));

        // =============================================================================================================

        // 2.5 - JPA Criteria API + JPA Metamodel

//        // Trazendo somente 1 resultado
//        CriteriaQuery<Student> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Student.class);
//        Root<Student> alunoRoot = criteriaQuery.from(Student.class);
//        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
//        inClause.value(nome);
//        criteriaQuery.select(alunoRoot).where(inClause);
//        Student alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();
//
//        // Trazendo uma lista como resultado
//        CriteriaQuery<Student> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Student.class);
//        Root<Student> alunoRootList = criteriaQueryList.from(Student.class);
//        List<Student> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Consulta alunoAPICriteria: " + alunoAPICriteria);
//        alunoAPICriteriaList.forEach(Student -> System.out.println("Consulta alunoAPICriteriaList: " + Student));

    }

}

