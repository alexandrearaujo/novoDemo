package br.com.mv.demo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.demo.model.DetalheTeste;
import br.com.mv.demo.model.DetalheTeste_;
import br.com.mv.demo.model.Teste;
import br.com.mv.demo.model.TesteFilho2;
import br.com.mv.demo.model.TesteWrapper;
import br.com.mv.demo.model.Teste_;
import br.com.mv.demo.model.type.EnumTipoTeste;
import br.com.mv.demo.repository.TesteRepository;

@Service
@Transactional(readOnly = true)
public class TesteBusiness {
	
	@Autowired
	private TesteRepository testeRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Transactional
	public Teste salvar(Teste teste) {
		return testeRepository.save(teste);
	}

	@Transactional
	public void deletarTodos() {
		testeRepository.deleteAll();
	}
	
	public Teste getTesteByNaturalId() {
		Session session = entityManager.unwrap(Session.class);
		session.enableFetchProfile("teste.detalhesProfile");
		return session.bySimpleNaturalId(Teste.class).load("11111111111");
	}
	
	public DetalheTeste getDetalheTesteWithoutData(Long id) {
		return entityManager.getReference(DetalheTeste.class, id);
	}
	
	public Teste findById(Long id) {
		return testeRepository.findOne(id);
	}
	
	public void refresh(Teste teste) {
		entityManager.refresh(teste);
	}

	public Teste joinFetch(Long id) {
		return testeRepository.joinFetch(id);
	}

	public Teste joinFetchCriteria(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teste> criteria = builder.createQuery(Teste.class);
		Root<Teste> root = criteria.from(Teste.class);
		Fetch<Teste, DetalheTeste> detalheTesteFetch = root.fetch(Teste_.detalhes, JoinType.LEFT);
		root.fetch(Teste_.mappedTestes, JoinType.LEFT);
		root.fetch(Teste_.sortedTestes, JoinType.LEFT);
		ParameterExpression<Long> idParameter = builder.parameter(Long.class);
		criteria.select(root).where(
		    builder.and(
		        builder.equal(root.get(Teste_.id), idParameter)
		    )
		);
		
		TypedQuery<Teste> query = entityManager.createQuery(criteria);
		query.setParameter(idParameter, id);
		return query.getSingleResult();
	}
	
	public List<TesteWrapper> fetchWrapper() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<TesteWrapper> criteria = builder.createQuery(TesteWrapper.class);
		Root<Teste> root = criteria.from(Teste.class);

		Path<Long> idPath = root.get(Teste_.id);
		Path<String> descricaoPath = root.get(Teste_.descricao);

		criteria.select(builder.construct(TesteWrapper.class, idPath, descricaoPath));
		criteria.where(builder.equal(root.get(Teste_.descricao), "Teste"));

		return entityManager.createQuery(criteria).getResultList();
	}
	
	public List<TesteWrapper> fetchNamedNativeQueryDTO() {
		List<TesteWrapper> testes = entityManager.createNamedQuery("teste.find_teste_dto").getResultList();
		return testes;
	}
	
	public List<TesteWrapper> fetchDTO() {
		Session session = entityManager.unwrap(Session.class);
		List<TesteWrapper> dtos = session.createSQLQuery(
			    "SELECT t.id as \"id\", t.descricao as \"descricao\" FROM teste t")
			.setResultTransformer(Transformers.aliasToBean(TesteWrapper.class))
			.list();
		return dtos;
	}
	
	public List<TesteFilho2> fetchInheritance() {
		Session session = entityManager.unwrap(Session.class);
		List<TesteFilho2> testeFilhos = session.createSQLQuery(
			    "SELECT * " +
			    "FROM teste t " +
			    "JOIN teste_filho_2 tf on t.id = tf.id")
			.addEntity(TesteFilho2.class)
			.list();
		return testeFilhos;
	}
	
	public List<TesteWrapper> fetchTuple() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		Root<Teste> root = criteria.from(Teste.class);

		Path<Long> idPath = root.get(Teste_.id);
		Path<String> descricaoPath = root.get(Teste_.descricao);

		criteria.multiselect(idPath, descricaoPath);
		criteria.where(builder.equal(root.get(Teste_.descricao), "Teste"));

		List<Tuple> tuples = entityManager.createQuery(criteria).getResultList();

		List<TesteWrapper> listTesteWrapper = new ArrayList<TesteWrapper>();
		for (Tuple tuple : tuples) {
			listTesteWrapper.add(new TesteWrapper(tuple.get(idPath), tuple.get(descricaoPath)));
		}
		return listTesteWrapper;
	}
	
	public List<TesteWrapper> fetchMultipleRoots() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		Root<Teste> testeRoot = criteria.from(Teste.class);
		Root<DetalheTeste> detalheTesteRoot = criteria.from(DetalheTeste.class);
		
		Path<Long> idPath = testeRoot.get(Teste_.id);
		Path<String> descricaoPath = testeRoot.get(Teste_.descricao);
		
		criteria.multiselect(testeRoot, detalheTesteRoot);
		
		Predicate testeRestriction = builder.and(
		    builder.equal(idPath, 1),
		    builder.isNotNull(descricaoPath)
		);
		
		Predicate detalheTesteRestriction = builder.and(
		    builder.like(detalheTesteRoot.get(DetalheTeste_.descricao ), "%TESTE%"),
		    builder.equal(detalheTesteRoot.get(DetalheTeste_.id), 1)
		);
		
		criteria.where(builder.and(testeRestriction, detalheTesteRestriction));
		
		List<Tuple> tuples = entityManager.createQuery(criteria).getResultList();
		
		List<TesteWrapper> listTesteWrapper = new ArrayList<TesteWrapper>();
		for (Tuple tuple : tuples) {
			listTesteWrapper.add(new TesteWrapper(tuple.get(idPath), tuple.get(descricaoPath)));
		}
		return listTesteWrapper;
	}
	
	public List<Object> fetchMultipleNativeEntities() {
		Session session = entityManager.unwrap(Session.class);
		List<Object> entities = session.createSQLQuery(
			    "SELECT {t.*}, {dt.*} " +
			    "FROM teste t, detalhe_teste dt " +
			    "WHERE t.id = dt.cd_teste" )
			.addEntity( "t", Teste.class)
			.addEntity( "dt", DetalheTeste.class)
			.list();
		return entities;
	}
	
	public Teste fetchEntityGraph(Long id) {
		return entityManager.find(Teste.class,
				id,
				Collections.singletonMap("javax.persistence.fetchgraph",
											entityManager.getEntityGraph("teste.detalhes"))
		);
	}
	
	public Teste fetchEntityGraphWithQuery(Long id) {
		EntityGraph<Teste> entityGraph = (EntityGraph<Teste>) entityManager.createEntityGraph("teste.detalhes");
		
		Query query = entityManager.createQuery("select t from Teste t where t.id = :id and type(t) = :type");
		query.setParameter("id", id);
		query.setParameter("type", Teste.class);
		query.setHint("javax.persistence.fetchgraph", entityGraph);
		
		return (Teste) query.getSingleResult();
	}
	
	public Teste fetchEntityGraphWithTypedQuery(Long id) {
		EntityGraph<Teste> entityGraph = (EntityGraph<Teste>) entityManager.createEntityGraph("teste.detalhes");
		
		TypedQuery<Teste> query = entityManager.createQuery("select t from Teste t where t.id = :id", Teste.class);
		query.setParameter("id", id);
		query.setHint("javax.persistence.fetchgraph", entityGraph);
		
		return query.getSingleResult();
	}
	
	public Teste loadEntityGraphWithTypedQuery(Long id) {
		TypedQuery<Teste> query = entityManager.createQuery("select t from Teste t where t.id = :id", Teste.class);
		query.setParameter("id", id);
		query.setHint("javax.persistence.loadgraph", entityManager.createEntityGraph("teste.detalhes"));
		return query.getSingleResult();
	}
	
	public List<String> loadCoalesce(Long id) {
		TypedQuery<String> query = entityManager.createQuery("select coalesce(t.descricao, '<sem descricao>' ) from Teste t where t.id = :id", String.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<String> loadCase(Long id) {
		TypedQuery<String> query = entityManager.createQuery(
				"select " +
			    "    case " +
			    "    when t.nomeCompleto is null " +
			    "    then " +
			    "     '<nome incompleto>' " +
			    "    else p.nomeCompleto " +
			    "    end " +
			    "from Teste t where t.id = :id", String.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public void insertTestes() {
		EntityTransaction txn = null;
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			txn = entityManager.getTransaction();
		    txn.begin();

		    int batchSize = 50;

		    for ( int i = 1; i < 1000; ++i ) {
		        Teste teste = new Teste();
		        teste.setCodigo(i);
		        teste.setCpf(String.valueOf(i));
		        teste.setDescricao("TESTE " + i);
		        teste.setSituacaoTeste(EnumTipoTeste.LIBERADO);
		        
		        entityManager.persist(teste);

		        if ( i % batchSize == 0 ) {
		            //flush a batch of inserts and release memory
		            entityManager.flush();
		            entityManager.clear();
		        }
		    }

		    txn.commit();
		} catch (RuntimeException e) {
		    if ( txn != null && txn.isActive()) txn.rollback();
		        throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}
	}
	
	public void updateTestes() {
		EntityTransaction txn = null;
		EntityManager entityManager = null;
		ScrollableResults scrollableResults = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			txn = entityManager.getTransaction();
		    txn.begin();

		    int batchSize = 50;
		    
		    Session session = entityManager.unwrap(Session.class);

		    scrollableResults = session
		        .createQuery("select t from Teste t")
		        .setCacheMode(CacheMode.IGNORE)
		        .scroll(ScrollMode.FORWARD_ONLY);

		    int count = 0;
		    while (scrollableResults.next()) {
		        Teste teste = (Teste) scrollableResults.get( 0 );
		        
		        teste.getNome().setApelido("ROBERTIM " + teste.getCodigo());
		        
		        if ( ++count % batchSize == 0 ) {
		            //flush a batch of updates and release memory:
		            entityManager.flush();
		            entityManager.clear();
		        }
		    }

		    txn.commit();
		} catch (RuntimeException e) {
		    if ( txn != null && txn.isActive()) {
		    	txn.rollback();
		    }
	        throw e;
		} finally {
		    if (entityManager != null) {
		        entityManager.close();
		    }
		}
	}
}
