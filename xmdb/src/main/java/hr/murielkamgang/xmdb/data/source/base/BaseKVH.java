package hr.murielkamgang.xmdb.data.source.base;

import hr.murielkamgang.xmdb.data.source.KeyValueHolder;

/**
 * Created by muriel on 24/2/18.
 */

public class BaseKVH extends KeyValueHolder {

    private final String fieldName;
    private final Integer fieldValue;

    public BaseKVH(String fieldName, Integer fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Integer getFieldValue() {
        return fieldValue;
    }

    @Override
    public String toString() {
        return "BaseKVH{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldValue=" + fieldValue +
                '}';
    }
}
