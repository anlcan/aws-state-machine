package com.finleap.sm.fields;

import com.finleap.sm.Context;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by anlcan on 25/01/2017.
 *
 * evaluates
 */
public enum ChoiceOperator {

    StringEquals(Type.STRING) {
        @Override
        protected boolean eval(Object one, Object two, Context context) {
            return paramConvertString(one).compareTo(paramConvertString(two)) == 0;
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
        protected boolean eval(Object one, Object two, Context context) {
            return paramConvertNumeric(one).compareTo(paramConvertNumeric(two)) == 0;
        }
    },

    NumericLessThan(Type.NUMERIC){
        @Override
        protected boolean eval(Object one, Object two, Context context) {
            return paramConvertNumeric(one).compareTo(paramConvertNumeric(two)) > 0;
        }
    },
//
//    NumericGreaterThan(Type.NUMERIC),
//
//    NumericLessThanEquals(Type.NUMERIC),
//
    NumericGreaterThanEquals(Type.NUMERIC){
    @Override
    protected boolean eval(Object one, Object two, Context context) {
        return paramConvertNumeric(one).compareTo(paramConvertNumeric(two)) <= 0;
    }
},

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

    And(Type.MULTI){
        @Override
        protected boolean eval(Object one, Object two, Context context) {
            //return paramConvertNumeric(one).compareTo(paramConvertNumeric(two)) >= 0;
            return true;
        }
    },
//
//    Or(Type.MULTI),
    /**
     * he value of a "Not" operator must be a single Choice Rule that must not itself contain "Next" fields.
     */
    Not(Type.CHOICE) {
        @Override
        protected boolean eval(Object one, Object two, Context context) {

            ChoiceRule rule = (ChoiceRule) one;
            return !rule.evaluate(context);
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
        CHOICE,
        MULTI;
    }

    protected abstract boolean eval(Object one, Object two, Context context);

    /** Util methods*/
    protected String paramConvertString(Object param){
        Objects.requireNonNull(param);
        return (String)param;
    }
    protected BigDecimal paramConvertNumeric(Object param) {
        BigDecimal result;
        Objects.requireNonNull(param);
        if (param instanceof BigDecimal)
            result = (BigDecimal)param;
        else if (param instanceof Double)
            result = BigDecimal.valueOf((Double)param);
        else if (param instanceof Integer)
            result =  BigDecimal.valueOf((Integer)param);
        else
            throw new IllegalArgumentException("not a numeric value " + param);

        return result;
    }

    /**
     * Timestamps
     * The Choice and Wait states deal with JSON field values which represent timestamps.
     * These are strings which MUST conform to the RFC3339 profile of ISO 8601, with the further restrictions that an uppercase “T” character MUST be used to separate date and time, and an uppercase “Z” character MUST be present in the absence of a numeric time zone offset, for example “2016-03-14T01:59:00Z”.
     * @param param
     * @return
     */
    protected Object paramConvertTimestamp(Object param) {
        return param;
    }

}
