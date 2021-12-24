package io.github.shiruka.protocol.codec;

/**
 * a record class that represents packet definitions.
 *
 * @param id the id.
 */
public record PacketDefinition(
  int id
) implements Definition {

}
