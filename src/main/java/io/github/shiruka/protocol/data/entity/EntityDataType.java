package io.github.shiruka.protocol.data.entity;

import io.github.shiruka.api.math.vectors.Vector3f;
import io.github.shiruka.api.math.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains entity data types.
 */
public enum EntityDataType {
  /**
   * the flags.
   */
  FLAGS,
  /**
   * the byte.
   */
  BYTE,
  /**
   * the short.
   */
  SHORT,
  /**
   * the int.
   */
  INT,
  /**
   * the float.
   */
  FLOAT,
  /**
   * the string.
   */
  STRING,
  /**
   * the nbt.
   */
  NBT,
  /**
   * the vector 3i.
   */
  VECTOR3I,
  /**
   * the long.
   */
  LONG,
  /**
   * the vector 3f.
   */
  VECTOR3F;

  /**
   * gets the type by object type.
   *
   * @param obj the object to get.
   *
   * @return type.
   */
  @NotNull
  public static EntityDataType byObject(@NotNull final Object obj) {
    if (obj instanceof EntityFlags) {
      return EntityDataType.FLAGS;
    } else if (obj instanceof Byte) {
      return EntityDataType.BYTE;
    } else if (obj instanceof Short) {
      return EntityDataType.SHORT;
    } else if (obj instanceof Integer) {
      return EntityDataType.INT;
    } else if (obj instanceof Float) {
      return EntityDataType.FLOAT;
    } else if (obj instanceof String) {
      return EntityDataType.STRING;
    } else if (obj instanceof ItemData || obj instanceof CompoundTag) {
      return EntityDataType.NBT;
    } else if (obj instanceof Vector3i) {
      return EntityDataType.VECTOR3I;
    } else if (obj instanceof Long) {
      return EntityDataType.LONG;
    } else if (obj instanceof Vector3f) {
      return EntityDataType.VECTOR3F;
    }
    throw new IllegalArgumentException("Invalid type");
  }
}
