package dev.sadghost.ghostingly.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.sadghost.ghostingly.base.Preconditions;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class used for creating MongoDB connections in a simple way.
 *
 * @deprecated a rewrite for the whole DAO utilities is on the way, prepare for the removal of it.
 * @author SadGhost
 * @since 1.0.0
 */
@Deprecated(forRemoval = true)
@ApiStatus.ScheduledForRemoval(inVersion = "1.1.0")
public final class MongoConnection {
    @NotNull private final MongoClient client;
    @NotNull public static final CodecRegistry POJO_CODEC_REGISTRY;

    private MongoConnection(final @NotNull String connectionString) {
        Preconditions.checkNonNull(connectionString, "connectionString");
        client = MongoClients.create(connectionString);
    }

    /**
     * Creates a MongoConnection instance with the provided
     * connection string.
     *
     * @param connectionString the connection string used to connect to the client.
     * @return The {@code MongoConnection} instance.
     * @since 1.0.0
     */
    @Contract("_ -> new")
    public static @NotNull MongoConnection of(final @NotNull String connectionString) {
        return new MongoConnection(connectionString);
    }

    /**
     * Returns the {@code MongoClient} instance.
     *
     * @return the {@code MongoClient} instance.
     * @since 1.0.0
     */
    @Contract(pure = true)
    public @NotNull MongoClient getClient() {
        return client;
    }

    /**
     * Gets a {@code MongoDatabase} from the
     *
     * @param database the database name.
     * @return the {@code MongoDatabase} with the provided name.
     * @since 1.0.0
     */
    public @NotNull MongoDatabase getDatabase(final @NotNull String database) {
        return client.getDatabase(database);
    }

    /**
     * Gets a collection from the requested database with the class
     * type for the POJO serialization.
     *
     * @param database the database name.
     * @param collection the collection name.
     * @param clazz the POJO class.
     * @param <T> the class type.
     * @return the {@code MongoCollection} instance.
     * @since 1.0.0
     */
    public @NotNull <T> MongoCollection<T> getCollection(final @NotNull String database,
                                                         final @NotNull String collection,
                                                         final @NotNull Class<T> clazz) {
        return getDatabase(database).getCollection(collection, clazz);
    }

    static {
        POJO_CODEC_REGISTRY = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }
}
