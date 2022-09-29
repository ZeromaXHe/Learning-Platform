package org.example.commercial.digibank.contract.ledgerapi;

/**
 * @author fabric
 * @apiNote
 * @implNote
 * @since 2022/9/29 15:14
 */
@FunctionalInterface
public interface StateDeserializer {
    State deserialize(byte[] buffer);
}
