package test;

import com.google.auto.value.AutoValue;
import com.kevinmost.auto.value.custom_hashcode_equals.adapter.IgnoreForHashCodeEquals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@AutoValue
public abstract class Test {
  @IgnoreForHashCodeEquals public abstract boolean testBool();
  @SuppressWarnings("mutable") @NotNull public abstract boolean[] testBoolArr();
  public abstract byte testByte();
  @SuppressWarnings("mutable") @NotNull public abstract byte[] testByteArr();
  public abstract char testChar();
  @SuppressWarnings("mutable") @NotNull public abstract char[] testCharArr();
  public abstract short testShort();
  @SuppressWarnings("mutable") @NotNull public abstract short[] testShortArr();
  public abstract int testInt();
  @SuppressWarnings("mutable") @NotNull public abstract int[] testIntArr();
  public abstract long testLong();
  @SuppressWarnings("mutable") @NotNull public abstract long[] testLongArr();
  public abstract float testFloat();
  @SuppressWarnings("mutable") @NotNull public abstract float[] testFloatArr();
  public abstract double testDouble();
  @SuppressWarnings("mutable") @NotNull public abstract double[] testDoubleArr();
  public abstract @Nullable Date testObj();
}
