package dev.sadghost.celeste.database;

/**
 * The {@code IDocumentSerializable} interface defines the contract for objects that can be serialized to and
 * deserialized from a document representation.
 * <p>
 * Implementing classes should provide the ability to convert their state into a document format (serialization),
 * as well as the ability to restore their state from a document (deserialization).
 * <p>
 * The document type is represented by the generic type parameter {@code T}, which can be any suitable document
 * representation, such as a JSON object.
 *
 * @param <T> the type of document representation used for serialization and deserialization.
 * @since 1.0.0
 * @author SadGhost
 */
public interface IDocumentSerializable<T> {

    /**
     * Deserializes data from a document into the object implementing this method.
     * <p>
     * The implementation of this method should extract the relevant information from the document and apply it
     * to the object's state.
     *
     * @param document the document to deserialize from.
     * @since 1.0.0
     */
    void deserialize(T document);

    /**
     * Serializes the object implementing this method into a document representation.
     * <p>
     * The implementation of this method should convert the object's state into a document format and return it.
     *
     * @return the serialized document representation of the object.
     * @since 1.0.0
     */
    T serialize();
}
