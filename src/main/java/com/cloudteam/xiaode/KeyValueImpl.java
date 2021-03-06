package com.cloudteam.xiaode;

import com.alibaba.middleware.race.OrderSystem;
import com.alibaba.middleware.race.OrderSystem.KeyValue;
import com.alibaba.middleware.race.OrderSystem.TypeException;

public class KeyValueImpl implements Comparable<KeyValueImpl>, KeyValue {
	private static  final String booleanTrueValue = "true";
	private static  final String booleanFalseValue = "false";
	String key;
	String rawValue;

	boolean isComparableLong = false;
	long longValue;

	public KeyValueImpl(String key, String rawValue) {
	     this.key = key;
	     this.rawValue = rawValue;
	     if (key.equals("createtime") || key.equals("orderid")) {
	        isComparableLong = true;
	        longValue = Long.parseLong(rawValue);
	      }
	    }

	public String key() {
	      return key;
	}

	public String valueAsString() {
	  return rawValue;
	}

	public long valueAsLong() throws TypeException {
	   try {
	      return Long.parseLong(rawValue);
	    } catch (NumberFormatException e) {
	      throw new TypeException();
	    }
	}

	public double valueAsDouble() throws TypeException {
	    try {
	      return Double.parseDouble(rawValue);
	    } catch (NumberFormatException e) {
	      throw new TypeException();
	    }
	}

	public boolean valueAsBoolean() throws TypeException {
	    if (this.rawValue.equals(booleanTrueValue)) {
	        return true;
	    }
	      if (this.rawValue.equals(booleanFalseValue)) {
	        return false;
	      }
	      throw new TypeException();
	}

	    public int compareTo(KeyValueImpl o) {
	      if (!this.key().equals(o.key())) {
	        throw new RuntimeException("Cannot compare from different key");
	      }
	      if (isComparableLong) {
	        return Long.compare(this.longValue, o.longValue);
	      }
	      return this.rawValue.compareTo(o.rawValue);
	    }

	    @Override
	    public String toString() {
	      return "[" + this.key + "]:" + this.rawValue;
	    }
}
