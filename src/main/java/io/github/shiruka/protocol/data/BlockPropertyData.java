package io.github.shiruka.protocol.data;

import io.github.shiruka.api.nbt.CompoundTag;

/**
 * a record class that represents block property data.
 *
 * @param name the name.
 * @param properties the properties.
 */
public record BlockPropertyData(
  String name,
  CompoundTag properties
) {

}
