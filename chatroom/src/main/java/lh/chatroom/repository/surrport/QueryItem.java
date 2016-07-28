package lh.chatroom.repository.surrport;

/**
 * Created by lh on 2016/4/20.
 */
public class QueryItem {
    private String field;
    private Object value;
    private OperatorType operatorType;

    public QueryItem() {
    }

    public QueryItem(String field, Object value, OperatorType operatorType) {
        this.field = field;
        this.value = value;
        this.operatorType = operatorType;
    }

    public QueryItem(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public static enum OperatorType {
        EQUAL, LIKE, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }
}
