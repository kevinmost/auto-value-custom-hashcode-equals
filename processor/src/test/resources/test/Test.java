package test;

import com.google.auto.value.AutoValue;
import com.kevinmost.auto.value.custom_hashcode_equals.adapter.IgnoreForHashCodeEquals;

import java.util.Date;

@AutoValue
public abstract class Test {
  @IgnoreForHashCodeEquals public abstract boolean testBool();
  @SuppressWarnings("mutable") public abstract boolean[] testBoolArr();
  public abstract byte testByte();
  @SuppressWarnings("mutable") public abstract byte[] testByteArr();
  public abstract char testChar();
  @SuppressWarnings("mutable") public abstract char[] testCharArr();
  public abstract short testShort();
  @SuppressWarnings("mutable") public abstract short[] testShortArr();
  public abstract int testInt();
  @SuppressWarnings("mutable") public abstract int[] testIntArr();
  public abstract long testLong();
  @SuppressWarnings("mutable") public abstract long[] testLongArr();
  public abstract float testFloat();
  @SuppressWarnings("mutable") public abstract float[] testFloatArr();
  public abstract double testDouble();
  @SuppressWarnings("mutable") public abstract double[] testDoubleArr();
  public abstract Date testObj();
}
