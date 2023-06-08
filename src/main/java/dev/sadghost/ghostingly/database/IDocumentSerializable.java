package dev.sadghost.ghostingly.database;

public interface IDocumentSerializable<T> {

    /**
     * Deserializes data from the document type, into the
     * object that you're calling this method at.
     *
     * @param document the document to deserialize from.
     * @since 1.0.0
     */
    void deserialize(T document);

    T serialize();
}
