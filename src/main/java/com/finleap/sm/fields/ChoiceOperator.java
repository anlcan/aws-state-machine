package com.finleap.sm.fields;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by anlcan on 25/01/2017.
 *
 * evaluates
 */
public enum ChoiceOperator {

    StringEquals(Type.STRING) {
        @Override
        protected boolean eval(Object context) {
            String inputParam = (String) context;
            return stringValue.compareTo(inputParam) == 0;
        }
    },

//    StringLessThan(Type.STRING),
//
//    StringGreaterThan(Type.STRING),
//
//    StringLessThanEquals(Type.STRING),
//
//    StringGreaterThanEquals(Type.STRING),

    NumericEquals(Type.NUMERIC) {
        @Override
        protected boolean eval(Object context) {
            return numericValue.compareTo(paramConvertNumeric(context)) == 0;
        }
    },

//    NumericLessThan(Type.NUMERIC),
//
//    NumericGreaterThan(Type.NUMERIC),
//
//    NumericLessThanEquals(Type.NUMERIC),
//
//    NumericGreaterThanEquals(Type.NUMERIC),

//    BooleanEquals(Type.BOOLEAN),
//
//    TimestampEquals(Type.TIMESTAMP),
//
//    TimestampLessThan(Type.TIMESTAMP),
//
//    TimestampGreaterThan(Type.TIMESTAMP),
//
//    TimestampLessThanEquals(Type.TIMESTAMP),
//
//    TimestampGreaterThanEquals(Type.TIMESTAMP),

//    And(Type.MULTI),
//
//    Or(Type.MULTI),

    Not(Type.MULTI) {
        @Override
        protected boolean eval(Object context) {
            return false;
        }
    };

    private final Type type;

    ChoiceOperator(Type type){
        this.type =type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        BOOLEAN,
        STRING,
        NUMERIC,
        TIMESTAMP,
        MULTI;
    }

    public String stringValue;

    public BigDecimal numericValue;

    public Boolean booleanValue;

    // HERE BE DRAGONS
    public List<ChoiceRule> multiRuleValue;

    protected abstract boolean eval(Object context);

    /** Util methods*/
    public BigDecimal paramConvertNumeric(Object param) {
        BigDecimal result;
        Objects.requireNonNull(param);
        if (param instanceof Double)
            result = BigDecimal.valueOf((Double)param);
        else if (param instanceof Integer)
            result =  BigDecimal.valueOf((Integer)param);
        else
            throw new IllegalArgumentException("not a numeric value " + param);

        return result;
    }

}
