package lh.chatroom.repository.surrport;

import com.google.common.base.Strings;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by lh on 2016/7/25.
 */
public class RepositoryUtil {
    private static final String PAGE_PARAMS_ERRORINFO = "分页参数错误";

    public static Pageable buildPage(int page, int size, String sortField, Sort.Direction direction) {
        if (page < 0 || size < 0 || Strings.isNullOrEmpty(sortField)) {
            throw new IllegalArgumentException(PAGE_PARAMS_ERRORINFO);
        }
        PageRequest request = new PageRequest(page, size, direction, sortField);
        return request;
    }

    public static PagedListHolder buildPagedListHolder(List sources, int page, int size, String sortField, Sort.Direction direction) {
        if (page < 0 || size < 0 || Strings.isNullOrEmpty(sortField)) {
            throw new IllegalArgumentException(PAGE_PARAMS_ERRORINFO);
        }
        PagedListHolder holder = new PagedListHolder();
        holder.setSource(sources);
        holder.setPage(page);
        holder.setPageSize(size);
        boolean isAscending = direction.name().equals(Sort.Direction.ASC) ? true : false;
        holder.setSort(new MutableSortDefinition(sortField, true, isAscending));
        return holder;
    }

    public static Query buildQuery(final List<QueryItem> queryItems) {
        Query query = new Query();
        for (QueryItem item : queryItems) {
            Criteria tempCriteria = null;
            if (item.getOperatorType() != null) {
                switch (item.getOperatorType()) {
                    case LIKE:
                        tempCriteria = Criteria.where(item.getField()).regex("*" + item.getValue() + "*");
                        break;
                    case GREATER_THAN:
                        tempCriteria = Criteria.where(item.getField()).gt(item.getValue());
                        break;
                    case LESS_THAN:
                        tempCriteria = Criteria.where(item.getField()).lt(item.getValue());
                        break;
                    case GREATER_THAN_OR_EQUAL:
                        tempCriteria = Criteria.where(item.getField()).gte(item.getValue());
                        break;
                    case LESS_THAN_OR_EQUAL:
                        tempCriteria = Criteria.where(item.getField()).lte(item.getValue());
                        break;
                    default:
                        tempCriteria = Criteria.where(item.getField()).is(item.getValue());
                        break;
                }
            } else {
                tempCriteria = Criteria.where(item.getField()).is(item.getValue());
            }
            query.addCriteria(tempCriteria);
        }
        return query;
    }
}
