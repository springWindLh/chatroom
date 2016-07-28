//package lh.chatroom.repository.surrport;
//
//import lh.chatroom.domain.support.BaseDomain;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by lh on 2016/7/19.
// */
//public interface CommonRepository<T extends BaseDomain, ID extends Serializable> {
//    Page<T> findAll(int page, int size);
//
//    Page<T> findAll(int page, int size, Sort.Direction direction, String sortField);
//
//    List<T> findByFieldsAndValues(Object... fieldsAndValues);
//
//    Page<T> findByFieldsAndValues(int page, int size, Object... fieldsAndValues);
//
//    Page<T> findByFieldsAndValues(int page, int size, Sort.Direction direction, String sortField, Object... fieldsAndValues);
//
//    Page<T> findByMap(int page, int size, Map<String, Object> map);
//
//    Page<T> findByMap(int page, int size, Sort.Direction direction, String sortField, Map<String, Object> map);
//
//    Page<T> findByQueryItems(int page, int size, List<QueryItem> queryItems);
//
//    Page<T> findByQueryItems(int page, int size, Sort.Direction direction, String sortField, List<QueryItem> queryItems);
//
//    <S extends T> List<S> save(Iterable<S> entites);
//
//    List<T> findAll();
//
//    List<T> findAll(Sort sort);
//
//    <S extends T> S insert(S entity);
//
//    <S extends T> List<S> insert(Iterable<S> entities);
//
//    Page<T> findAll(Pageable pageable);
//
//    <S extends T> S save(S entity);
//
//    T findOne(ID id);
//
//    boolean exists(ID id);
//
//    Iterable<T> findAll(Iterable<ID> ids);
//
//    long count();
//
//    void delete(ID id);
//
//    void delete(T entity);
//
//    void delete(Iterable<? extends T> entities);
//
//    void deleteAll();
//}
