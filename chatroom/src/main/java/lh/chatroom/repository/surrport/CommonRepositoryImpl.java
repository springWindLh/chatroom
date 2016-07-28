//package lh.chatroom.repository.surrport;
//
//import lh.chatroom.domain.support.BaseDomain;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by lh on 2016/7/19.
// */
//@NoRepositoryBean
//public abstract class CommonRepositoryImpl<T extends BaseDomain, ID extends Serializable> implements CommonRepository<T, ID> {
//    private static final String PARAMS_NUMBERS_ERROR = "参数个数错误";
//    private Class<T> entityClass;
//
//    public CommonRepositoryImpl() {
//        Type returnType = this.getClass().getGenericSuperclass();
//        ParameterizedType type = (ParameterizedType) returnType;
//        this.entityClass = (Class) type.getActualTypeArguments()[0];
//    }
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Autowired
//    private BaseRepository<T, ID> baseRepository;
//
//    @Override
//    public Page<T> findAll(int page, int size) {
//        PageRequest pageRequest = new PageRequest(page, size);
//        return baseRepository.findAll(pageRequest);
//    }
//
//    @Override
//    public Page<T> findAll(int page, int size, Sort.Direction direction, String sortField) {
//        Sort sort = new Sort(direction, sortField);
//        PageRequest pageRequest = new PageRequest(page, size, sort);
//        return baseRepository.findAll(pageRequest);
//    }
//
//    @Override
//    public List<T> findByFieldsAndValues(Object... fieldsAndValues) {
//        return mongoTemplate.find(createQuery(fieldsAndValues), entityClass);
//    }
//
//    @Override
//    public Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, fieldsAndValues), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, direction, sortField, fieldsAndValues), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size, direction, sortField), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public Page<T> findByMap(int page, int size, Map<String, Object> map) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, map), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public Page<T> findByMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> map) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, direction, sortField, map), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size, direction, sortField), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, queryItems), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems) {
//        List<T> list = mongoTemplate.find(createQuery(page, size, direction, sortField, queryItems), entityClass);
//        Page<T> resultPage = new PageImpl<>(list, new PageRequest(page, size, direction, sortField), list.size());
//        return resultPage;
//    }
//
//    @Override
//    public <S extends T> List<S> save(Iterable<S> entites) {
//        return baseRepository.save(entites);
//    }
//
//    @Override
//    public List<T> findAll() {
//        return baseRepository.findAll();
//    }
//
//    @Override
//    public List<T> findAll(Sort sort) {
//        return baseRepository.findAll(sort);
//    }
//
//    @Override
//    public <S extends T> S insert(S entity) {
//        return baseRepository.insert(entity);
//    }
//
//    @Override
//    public <S extends T> List<S> insert(Iterable<S> entities) {
//        return baseRepository.insert(entities);
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        return baseRepository.findAll(pageable);
//    }
//
//    @Override
//    public <S extends T> S save(S entity) {
//        return baseRepository.save(entity);
//    }
//
//    @Override
//    public T findOne(ID id) {
//        return baseRepository.findOne(id);
//    }
//
//    @Override
//    public boolean exists(ID id) {
//        return baseRepository.exists(id);
//    }
//
//    @Override
//    public Iterable<T> findAll(Iterable<ID> ids) {
//        return baseRepository.findAll(ids);
//    }
//
//    @Override
//    public long count() {
//        return baseRepository.count();
//    }
//
//    @Override
//    public void delete(ID id) {
//        baseRepository.delete(id);
//    }
//
//    @Override
//    public void delete(T entity) {
//        baseRepository.delete(entity);
//    }
//
//    @Override
//    public void delete(Iterable<? extends T> entities) {
//        baseRepository.delete(entities);
//    }
//
//    @Override
//    public void deleteAll() {
//        baseRepository.deleteAll();
//    }
//
//    private Query createQuery(final Object... fieldsAndValues) {
//        this.CheckFieldsAndValues(fieldsAndValues);
//        Query query = new Query();
//        for (int i = 0; i < fieldsAndValues.length; i += 2) {
//            Criteria tempCriteria = Criteria.where(fieldsAndValues[i].toString()).is(fieldsAndValues[i + 1]);
//            query.addCriteria(tempCriteria);
//        }
//        return query;
//    }
//
//    private Query createQuery(int page, int size, final Object... fieldsAndValues) {
//        this.CheckFieldsAndValues(fieldsAndValues);
//        PageRequest pageRequest = new PageRequest(page, size);
//        Query query = createQuery(fieldsAndValues);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private Query createQuery(int page, int size, Sort.Direction direction, String sortField, final Object... fieldsAndValues) {
//        this.CheckFieldsAndValues(fieldsAndValues);
//        PageRequest pageRequest = new PageRequest(page, size, direction, sortField);
//        Query query = createQuery(fieldsAndValues);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private Query createQuery(final Map<String, Object> paramsMap) {
//        Query query = new Query();
//        for (Map.Entry entry : paramsMap.entrySet()) {
//            Criteria tempCriteria = Criteria.where(entry.getKey().toString()).is(entry.getValue());
//            query.addCriteria(tempCriteria);
//        }
//        return query;
//    }
//
//    private Query createQuery(int page, int size, final Map<String, Object> paramsMap) {
//        PageRequest pageRequest = new PageRequest(page, size);
//        Query query = createQuery(paramsMap);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private Query createQuery(int page, int size, Sort.Direction direction, String sortField, final Map<String, Object> paramsMap) {
//        PageRequest pageRequest = new PageRequest(page, size, direction, sortField);
//        Query query = createQuery(paramsMap);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private Query createQuery(final List<QueryItem> queryItems) {
//        Query query = new Query();
//        for (QueryItem item : queryItems) {
//            Criteria tempCriteria = null;
//            if (item.getOperatorType() != null) {
//                switch (item.getOperatorType()) {
//                    case LIKE:
//                        tempCriteria = Criteria.where(item.getField()).regex("*" + item.getValue() + "*");
//                        break;
//                    case GREATER_THAN:
//                        tempCriteria = Criteria.where(item.getField()).gt(item.getValue());
//                        break;
//                    case LESS_THAN:
//                        tempCriteria = Criteria.where(item.getField()).lt(item.getValue());
//                        break;
//                    case GREATER_THAN_OR_EQUAL:
//                        tempCriteria = Criteria.where(item.getField()).gte(item.getValue());
//                        break;
//                    case LESS_THAN_OR_EQUAL:
//                        tempCriteria = Criteria.where(item.getField()).lte(item.getValue());
//                        break;
//                    default:
//                        tempCriteria = Criteria.where(item.getField()).is(item.getValue());
//                        break;
//                }
//            } else {
//                tempCriteria = Criteria.where(item.getField()).is(item.getValue());
//            }
//            query.addCriteria(tempCriteria);
//        }
//        return query;
//    }
//
//    private Query createQuery(int page, int size, List<QueryItem> queryItems) {
//        PageRequest pageRequest = new PageRequest(page, size);
//        Query query = createQuery(queryItems);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private Query createQuery(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems) {
//        PageRequest pageRequest = new PageRequest(page, size, direction, sortField);
//        Query query = createQuery(queryItems);
//        query.with(pageRequest);
//        return query;
//    }
//
//    private boolean CheckFieldsAndValues(Object... fieldsAndValues) {
//        if (fieldsAndValues.length % 2 != 0) {
//            throw new IllegalArgumentException(PARAMS_NUMBERS_ERROR);
//        } else {
//            return true;
//        }
//    }
//
//}
