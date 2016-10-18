package test;

import java.lang.Object;
import java.lang.Override;
import java.util.Date;

final class AutoValue_Test extends $AutoValue_Test {
  AutoValue_Test(boolean testBool, boolean[] testBoolArr, byte testByte, byte[] testByteArr, char testChar, char[] testCharArr, short testShort, short[] testShortArr, int testInt, int[] testIntArr, long testLong, long[] testLongArr, float testFloat, float[] testFloatArr, double testDouble, double[] testDoubleArr, Date testObj) {
    super(testBool, testBoolArr, testByte, testByteArr, testChar, testCharArr, testShort, testShortArr, testInt, testIntArr, testLong, testLongArr, testFloat, testFloatArr, testDouble, testDoubleArr, testObj);
  }

  @Override
  public final boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Test) {
      Test that = (Test) o;
      return
          // testBool (type: boolean) skipped due to @IgnoreForHashCodeEquals annotation
          java.util.Arrays.equals(this.testBoolArr(), that.testBoolArr())
          && this.testByte() == that.testByte()
          && java.util.Arrays.equals(this.testByteArr(), that.testByteArr())
          && this.testChar() == that.testChar()
          && java.util.Arrays.equals(this.testCharArr(), that.testCharArr())
          && this.testShort() == that.testShort()
          && java.util.Arrays.equals(this.testShortArr(), that.testShortArr())
          && this.testInt() == that.testInt()
          && java.util.Arrays.equals(this.testIntArr(), that.testIntArr())
          && this.testLong() == that.testLong()
          && java.util.Arrays.equals(this.testLongArr(), that.testLongArr())
          && this.testFloat() == that.testFloat()
          && java.util.Arrays.equals(this.testFloatArr(), that.testFloatArr())
          && this.testDouble() == that.testDouble()
          && java.util.Arrays.equals(this.testDoubleArr(), that.testDoubleArr())
          && (this.testObj() == null) ? (that.testObj() == null) : this.testObj().equals(that.testObj())
          ;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    int h = 1;
    // testBool (type: boolean) skipped due to @IgnoreForHashCodeEquals annotation
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testBoolArr());
    h *= 1000003;
    h ^= this.testByte();
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testByteArr());
    h *= 1000003;
    h ^= this.testChar();
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testCharArr());
    h *= 1000003;
    h ^= this.testShort();
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testShortArr());
    h *= 1000003;
    h ^= this.testInt();
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testIntArr());
    h *= 1000003;
    h ^= (this.testLong() >>> 32) ^ this.testLong();
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testLongArr());
    h *= 1000003;
    h ^= (Float.floatToIntBits(this.testFloat()));
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testFloatArr());
    h *= 1000003;
    h ^= (Double.doubleToLongBits(this.testDouble()) >>> 32) ^ Double.doubleToLongBits(this.testDouble());
    h *= 1000003;
    h ^= java.util.Arrays.hashCode(this.testDoubleArr());
    h *= 1000003;
    h ^= this.testObj() == null ? 0 : this.testObj().hashCode();
    return h;
  }
}
